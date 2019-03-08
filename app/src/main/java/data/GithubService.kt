package data

import Entities.Items
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/repositories")
    fun getUsers(@Query("q") search: String, @Query("per_page") perPage: Int): Single<List<Items>>

    companion object {
        fun getService(): GithubService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/search/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(GithubService::class.java)
        }
    }

}