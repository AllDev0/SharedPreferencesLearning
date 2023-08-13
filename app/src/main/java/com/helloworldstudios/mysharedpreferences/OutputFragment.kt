package com.helloworldstudios.mysharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.helloworldstudios.mysharedpreferences.databinding.FragmentOutputBinding
import java.util.UUID

class OutputFragment : Fragment() {

    private lateinit var binding: FragmentOutputBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var uuid: UUID
    private lateinit var name: String
    private lateinit var surname: String
    private var age: Int? = null
    private lateinit var occupation: String
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOutputBinding.inflate(inflater, container, false)
        sharedPreferences = this.requireActivity().getSharedPreferences("com.helloworldstudios.mysharedpreferences", Context.MODE_PRIVATE)
        getSharedPreferencesDatas()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etUUID.apply {
            inputType = InputType.TYPE_NULL
            isFocusable = false
            isClickable = false
        }
        setTexts(binding, user)
        binding.btnEdit.setOnClickListener {
            getInputs()
            if (!isValidText(name)){
                Log.i("OutputSharedPreferences", "Name is invalid")
                binding.etName.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                binding.etName.requestFocus()
            } else{
                if (!isValidText(surname)){
                    Log.i("OutputSharedPreferences", "Surname is invalid")
                    binding.etSurname.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                    binding.etSurname.requestFocus()
                } else{
                    if(!isValidAge(age)){
                        Log.i("OutputSharedPreferences", "Age is invalid")
                        binding.etAge.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                        binding.etAge.requestFocus()
                    } else{
                        if (!isValidText(occupation)){
                            Log.i("OutputSharedPreferences", "Occupation is invalid")
                            binding.etOccupation.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                            binding.etOccupation.requestFocus()
                        } else{
                            Log.i("InfoSharedPreferences","Everything is OK.")
                            saveDatas(sharedPreferences, User(uuid, name, surname, age!!, occupation))
                            Toast.makeText(this.requireContext(), "Changes saved!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun getInputs(){
        name = binding.etName.text.toString().trimStart().trimEnd()
        surname = binding.etSurname.text.toString().trimStart().trimEnd()
        age = binding.etAge.text.toString().toIntOrNull()
        occupation = binding.etOccupation.text.toString().trimStart().trimEnd()
    }

    private fun getSharedPreferencesDatas(){
        uuid = UUID.fromString(sharedPreferences.getString("uuid", ""))
        name = sharedPreferences.getString("name", "").toString()
        surname = sharedPreferences.getString("surname", "").toString()
        age = sharedPreferences.getInt("age", -1)
        occupation = sharedPreferences.getString("occupation", "").toString()
        user = User(uuid, name, surname, age!!, occupation)
    }
}