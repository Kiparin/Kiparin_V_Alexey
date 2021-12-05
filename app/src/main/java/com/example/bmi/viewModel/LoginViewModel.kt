package com.example.bmi.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.bmi.BR



public class LoginViewModel: BaseObservable(),java.io.Serializable{

    var clicable : Int = 0

    @get:Bindable
    var setStringLogin: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.setStringLogin)
        }

    @get:Bindable
    var setStringPassword:  String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.setStringPassword)
        }

    fun getCountAll():Int{
        return setStringLogin.count() + setStringPassword.count()
    }

    fun onClicableClick(){
        clicable ++
    }
}



