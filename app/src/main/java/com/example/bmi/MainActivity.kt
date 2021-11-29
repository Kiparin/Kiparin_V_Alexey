package com.example.bodymassindex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bodymassindex.model.MainActivityModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var model : MainActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = MainActivityModel(this);
    }

    override  fun onStart() {
        super.onStart()
        runBlocking{ model.onInit()};
    }
}