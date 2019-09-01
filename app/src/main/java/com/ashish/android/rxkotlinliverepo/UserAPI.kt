package com.ashish.android.rxkotlinliverepo

import io.reactivex.Observable
import retrofit2.http.GET

interface UserAPI {
    @GET("6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
    fun getUsers(): Observable<List<User>>

}