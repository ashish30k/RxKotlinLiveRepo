package com.ashish.android.rxkotlinliverepo

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class UserRepo(val userAPI: UserAPI, val userDAO: UserDAO) {

    fun getUsers(): Observable<List<User>> {
        return Observable.concatArray(getUsersFromDb(), getUsersFromApi())
    }

    fun getUsersFromDb(): Observable<List<User>> {
        return userDAO.getUsers().toObservable().doOnNext {
            Log.d("UserRepo", "Fetching from DB ${it.size}")
        }
    }

    fun getUsersFromApi(): Observable<List<User>> {
        return userAPI.getUsers().doOnNext {
            Log.d("UserRepo", "Fetching from API ${it.size}")
            saveToDB(it)
        }
    }

    private fun saveToDB(users: List<User>) {
        Observable.fromCallable { userDAO.insertAllUsers(users) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                println("Saving API data into DB $it.size")
            }


    }
}