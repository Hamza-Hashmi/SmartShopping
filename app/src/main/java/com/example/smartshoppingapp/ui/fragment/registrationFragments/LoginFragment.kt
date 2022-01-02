package com.example.smartshoppingapp.ui.fragment.registrationFragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartshoppingapp.AppUtils.Constants.Companion.authToken
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.MainActivity
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentLoginBinding
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel


class LoginFragment : Fragment() {

    var _binding:FragmentLoginBinding? =null
    val binding get()= _binding!!
    lateinit var viewModel: RegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater)


        viewModel = (activity as MainActivity).viewModel

        binding.apply {

            this.btnSignup.setOnClickListener{
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
            this.btnLogin.setOnClickListener {

                /*Intent(requireContext(),DashBoardActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }*/

                binding.progressbar.visibility= View.VISIBLE
                allowLoginUser()

            }
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun allowLoginUser() {
        binding.progressbar.visibility = View.VISIBLE
        val email = binding.loginUserEmail.text.toString()
        val password = binding.loginUserPassword.text.toString()
        if(TextUtils.isEmpty(email)){
            binding.loginUserEmail.error = "required"
            binding.loginUserEmail.requestFocus()
            return
        }
        else if (TextUtils.isEmpty(password)){
            binding.loginUserPassword.error = "required"
            binding.loginUserPassword.requestFocus()
            return
        }

        else{
            viewModel.login(email,password)

            viewModel._loginResponse.observe(viewLifecycleOwner,{

                  if (it.body()?.status.equals("fail")){
                      Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
                      binding.progressbar.visibility = View.GONE
                  }
                else{
                      SharedHelper.putKey(requireContext(),authToken,it.body()?.token)
                      //Log.e("TAG", "allowLoginUser: ${it.body()?.token}" )
                      binding.progressbar.visibility = View.GONE
                      startActivity(Intent(requireContext(),DashBoardActivity::class.java))
                      activity?.finish()
                  }

            })
        }

    }

}