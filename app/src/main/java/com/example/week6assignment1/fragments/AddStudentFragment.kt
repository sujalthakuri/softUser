package com.example.week6assignment1.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.week6assignment1.R
import com.example.week6assignment1.Storage
import com.example.week6assignment1.models.User
import com.google.android.material.button.MaterialButton

class AddStudentFragment : Fragment() {
    private lateinit var etFullName: EditText
    private lateinit var etAge: EditText
    private lateinit var etAddress: EditText
    private lateinit var etImageUrl: EditText
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rbOthers: RadioButton
    private lateinit var btnSave: MaterialButton
    private var gender: Char? = null
    private var storage = Storage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add_student, container, false)
        etFullName = view.findViewById(R.id.etFullName)
        etImageUrl = view.findViewById(R.id.etImageUrl)
        etAge = view.findViewById(R.id.etAge)
        etAddress = view.findViewById(R.id.etAddress)
        rbMale = view.findViewById(R.id.rbMale)
        rbFemale = view.findViewById(R.id.rbFemale)
        rbOthers = view.findViewById(R.id.rbOthers)
        btnSave = view.findViewById(R.id.btnSave)

        rbFemale.setOnClickListener {
            gender = 'F'
        }
        rbMale.setOnClickListener {
            gender = 'M'
        }
        rbOthers.setOnClickListener {
            gender = 'O'
        }

        btnSave.setOnClickListener {
            if(validateForm()){
                storage.appendUsers(User(null,
                    etFullName.text.toString(),
                    etAge.text.toString().toInt(),
                    gender,
                    etAddress.text.toString(),
                    etImageUrl.text.toString()
                ))
                resetForm()
                Toast.makeText(context, "User Saved", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    private fun resetForm() {
        etFullName.setText("")
        etAge.setText("")
        etAddress.setText("")
        etImageUrl.setText("")
        gender = null
        rbMale.isChecked = false
        rbFemale.isChecked = false
        rbOthers.isChecked = false
    }

    private fun validateForm(): Boolean {
        when {
            TextUtils.isEmpty(etFullName.text) -> {
                etFullName.requestFocus()
                etFullName.error = "Please insert a name"
                return false
            }
            TextUtils.isEmpty(etAge.text) -> {
                etAge.requestFocus()
                etAge.error = "Please insert a age"
                return false
            }
            !TextUtils.isDigitsOnly(etAge.text) -> {
                etAge.requestFocus()
                etAge.error = "Age is digit only"
                return false
            }
            TextUtils.isEmpty(etAddress.text) -> {
                etAddress.requestFocus()
                etAddress.error = "Please insert an address"
                return false
            }
            gender == null -> {
                Toast.makeText(context, "Please select a gender", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
}