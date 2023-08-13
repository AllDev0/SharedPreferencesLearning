package com.helloworldstudios.mysharedpreferences

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.helloworldstudios.mysharedpreferences.databinding.FragmentInputBinding
import java.util.UUID

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var uuid: UUID
    private lateinit var name: String
    private lateinit var surname: String
    private var age: Int? = null
    private lateinit var occupation: String
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        sharedPreferences = this.requireActivity().getSharedPreferences("com.helloworldstudios.mysharedpreferences", MODE_PRIVATE)
        getSharedPreferencesDatas()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTexts(binding, user)
        binding.btnSave.setOnClickListener {
            getInputs()
            if (!isValidText(name)){
                Log.i("InputSharedPreferences", "Name is invalid")
                binding.etName.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                binding.etName.requestFocus()
            } else{
                if (!isValidText(surname)){
                    Log.i("InputSharedPreferences", "Surname is invalid")
                    binding.etSurname.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                    binding.etSurname.requestFocus()
                } else{
                    if(!isValidAge(age)){
                        Log.i("InputSharedPreferences", "Age is invalid")
                        binding.etAge.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                        binding.etAge.requestFocus()
                    } else{
                        if (!isValidText(occupation)){
                            Log.i("InputSharedPreferences", "Occupation is invalid")
                            binding.etOccupation.setError("Invalid Input", ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_error))
                            binding.etOccupation.requestFocus()
                        } else{
                            uuid = UUID.randomUUID()
                            Log.i("InfoSharedPreferences","Everything is OK.")
                            saveDatas(sharedPreferences, User(uuid, name, surname, age!!, occupation))
                            Navigation.findNavController(it).navigate(R.id.action_inputFragment_to_outputFragment)
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
        //uuid = UUID.fromString(sharedPreferences.getString("uuid", ""))
        name = sharedPreferences.getString("name", "").toString()
        surname = sharedPreferences.getString("surname", "").toString()
        age = sharedPreferences.getInt("age", 0)
        occupation = sharedPreferences.getString("occupation", "").toString()
        user = User(name, surname, age!!, occupation)
    }
}