package com.example.smartshoppingapp.ui.fragment.registrationFragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartshoppingapp.MainActivity
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentSignupBinding
import com.example.smartshoppingapp.model.SignupModel
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignupFragment : Fragment() {

    var _binding : FragmentSignupBinding ? = null
    val binding get() = _binding!!
    lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignupBinding.inflate(layoutInflater)

        viewModel =(activity as MainActivity).viewModel


        binding.apply {

            this.btnLogin.setOnClickListener{

                this.progressbar.visibility = View.VISIBLE

                val email = this.signupUserEmail.text.toString()
                val password = this.signupUserPassword.text.toString()
                val name = this.signupUserName.text.toString()
                val phone = this.signupUserPhone.text.toString()

                if (TextUtils.isEmpty(email)){
                    this.signupUserEmail.error = "Required"
                    return@setOnClickListener
                }
                else if (TextUtils.isEmpty(password)){
                    this.signupUserPassword.error = "Required"
                    return@setOnClickListener
                }
                else if (TextUtils.isEmpty(name)){
                    this.signupUserName.error = "Required"
                    return@setOnClickListener
                }
                else if (TextUtils.isEmpty(phone)){
                    this.signupUserPhone.error = "Required"
                    return@setOnClickListener
                }
                else{

                    val signupModel = SignupModel(name, phone, email, password)


                    viewModel.signUp(signupModel)

                    viewModel._signUpResposne.observe(viewLifecycleOwner, Observer {
                        if (it.body()?.status.equals("fail")){
                            Log.e("TAG", "error onCreateView: ${it.body()}", )

                            binding.progressbar.visibility = View.GONE
                         }else{
                            Log.e("TAG", "sucess onCreateView: ${it.body()}", )
                            binding.progressbar.visibility = View.GONE

                            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                        }
                    })
                }



            }

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}