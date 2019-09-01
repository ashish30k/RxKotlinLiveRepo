package com.ashish.android.rxkotlinliverepo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import java.net.ConnectException
import java.net.UnknownHostException

class UserListFragment : MVVMFragment() {
    val userListViewModel = MyApplication.injectUserListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        userListViewModel.getUserList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showUsers(it)
            }, {
                showError()
            })
    }

    private fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }

    private fun showUsers(data: UserList) {
        if (data.error == null) {
            usersList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, data.users)
        } else if (data.error is ConnectException || data.error is UnknownHostException) {
            Log.d("UserListFragment", "No connection, maybe inform user that data loaded from DB.")
        } else {
            showError()
        }
    }
}
