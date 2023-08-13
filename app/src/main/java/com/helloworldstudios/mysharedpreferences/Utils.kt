package com.helloworldstudios.mysharedpreferences

import android.content.SharedPreferences
import com.helloworldstudios.mysharedpreferences.databinding.FragmentInputBinding
import com.helloworldstudios.mysharedpreferences.databinding.FragmentOutputBinding

fun isValidText(text: String): Boolean = Regex("^[a-zA-Z ]+\$").containsMatchIn(text)
fun isValidAge(age: Int?): Boolean = (age ?: -1) >= 0
fun saveDatas(sharedPreferences: SharedPreferences, user: User){
    sharedPreferences.edit().apply {
        this.putString("uuid", user.uuid.toString())
        this.putString("name", user.name)
        this.putString("surname", user.surname)
        this.putInt("age", user.age)
        this.putString("occupation", user.occupation)
    }.apply()
}

fun setTexts(binding: FragmentOutputBinding, user: User){
    binding.etUUID.setText(user.uuid.toString())
    binding.etName.setText(user.name)
    binding.etSurname.setText(user.surname)
    binding.etAge.setText(user.age.toString())
    binding.etOccupation.setText(user.occupation)
}

fun setTexts(binding: FragmentInputBinding, user: User){
    //binding.etUUID.setText(user.uuid.toString())
    binding.etName.setText(user.name)
    binding.etSurname.setText(user.surname)
    binding.etAge.setText(user.age.toString())
    binding.etOccupation.setText(user.occupation)
}