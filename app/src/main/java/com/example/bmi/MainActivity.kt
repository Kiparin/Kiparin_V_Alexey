package com.example.bmi

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil.setContentView
import com.example.bmi.viewModel.LoginViewModel
import com.example.bmi.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.databinding.Observable


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private final val login_viewmodel_key :String = "LOGIN_VIEW_MODEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = LoginViewModel()
        setLayout()
        supportActionBar?.hide();
    }

    private fun setLayout() {
        binding = setContentView(this, R.layout.activity_main)
        if(binding != null) {
            binding.login = loginViewModel
        }
        binding.lifecycleOwner = this
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(login_viewmodel_key, loginViewModel);
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        loginViewModel = (savedInstanceState.getSerializable(login_viewmodel_key) as LoginViewModel?)!!
        if(binding != null) {
            binding.login = loginViewModel
        }
        binding.invalidateAll()
    }


    fun onMyButtonClick(view: View?) {
        loginViewModel.onClicableClick()
        val result  = String.format(getString(R.string.result_text), loginViewModel.clicable.toString(),loginViewModel.getCountAll().toString())
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}
