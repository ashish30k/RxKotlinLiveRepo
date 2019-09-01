package com.ashish.android.rxkotlinliverepo

import android.app.Application
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    companion object {
        private lateinit var appDatabase: AppDataBase
        private lateinit var userDAO: UserDAO
        private lateinit var retrofit: Retrofit
        private lateinit var userAPI: UserAPI
        private lateinit var userRepo: UserRepo
        private lateinit var userListViewModel: UserListViewModel

        fun injectUserApi() = userAPI

        fun injectUserListViewModel() = userListViewModel

        fun injectUserDao() = appDatabase.userDAO()

    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "my-database"
        ).build()

        userDAO = appDatabase.userDAO()

        retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://randomapi.com/api/")
            .build();

        userAPI = retrofit.create(UserAPI::class.java)

        userRepo = UserRepo(userAPI, userDAO)

        userListViewModel = UserListViewModel(userRepo)

    }

}