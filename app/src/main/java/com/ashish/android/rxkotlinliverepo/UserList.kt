package com.ashish.android.rxkotlinliverepo

data class UserList (val users:List<User>, val message:String, val error:Throwable? = null)