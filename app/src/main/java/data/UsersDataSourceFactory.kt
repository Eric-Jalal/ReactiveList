package data

import Entities.Items
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import android.arch.paging.DataSource


class UsersDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                             private val githubService: GithubService)
    : DataSource.Factory<Long, Items>() {

    val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()
    override fun create(): DataSource<Long, Items> {
        val usersDataSource = UsersDataSource(githubService, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}