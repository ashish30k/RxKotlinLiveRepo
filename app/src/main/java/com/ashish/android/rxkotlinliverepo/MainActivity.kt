package com.ashish.android.rxkotlinliverepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val transcation: FragmentTransaction = supportFragmentManager.beginTransaction()
            transcation.replace(R.id.container, UserListFragment()).commit()
        }
    }
}
