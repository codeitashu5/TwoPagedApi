package com.ashupandey.twopageapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //main starting point

        if(savedInstanceState==null){

            btn1.setOnClickListener {
                if(savedInstanceState==null){
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.containerFragment, Fragment1())
                       
                    }
                }
            }

            btn2.setOnClickListener {
                if(savedInstanceState==null){
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.containerFragment, Fragment2())
                    }
                }
            }
        }




    }
}