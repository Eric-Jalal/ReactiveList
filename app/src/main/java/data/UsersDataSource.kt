package data

import Entities.Items
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import doman.NetworkState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Action
import timber.log.Timber

class UsersDataSource(
        private val githubService: GithubService,
        private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<Long, Items>() {

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { throwable -> Timber.e(throwable.message) }))
        }
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Items>) {

        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(githubService.getUsers("tetris", params.requestedLoadSize).subscribe({ items ->
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items)
        }, { throwable ->
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error(throwable.message)
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Items>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(githubService.getUsers("tetris", params.requestedLoadSize).subscribe({ items ->
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(items)
        }, { throwable ->
            setRetry(Action { loadAfter(params, callback) })
            networkState.postValue(NetworkState.error(throwable.message))
        }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Items>) {
    }

    override fun getKey(items: Items): Long {
        return items.id
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

}