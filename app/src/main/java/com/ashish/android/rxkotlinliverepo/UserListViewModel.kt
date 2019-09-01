package com.ashish.android.rxkotlinliverepo

import android.util.Log
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class UserListViewModel(val userRepo: UserRepo) {
    fun getUserList(): Observable<UserList> {
        return userRepo.getUsers()
            .debounce(400, TimeUnit.MILLISECONDS)
            .map {
                Log.d("UserListViewModel", "Mapping user list to UI Model")
                UserList(it.take(10), "Top 10 users")
            }

    }
}