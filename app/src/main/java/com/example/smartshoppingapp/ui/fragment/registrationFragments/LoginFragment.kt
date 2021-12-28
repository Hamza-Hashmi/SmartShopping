package com.example.smartshoppingapp.ui.fragment.registrationFragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartshoppingapp.databinding.FragmentLoginBinding
import com.example.smartshoppingapp.isEmailValid
import com.example.smartshoppingapp.ui.activites.DashBoardActivity


class LoginFragment : Fragment() {

    var _binding:FragmentLoginBinding? =null
    val binding get()= _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater)


        binding.apply {
            this.btnLogin.setOnClickListener {

                Intent(requireContext(),DashBoardActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }

            }
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun allowLoginUser() {
        val email = binding.loginUserEmail.text.toString()
        val password = binding.loginUserPassword.text.toString()
        if(TextUtils.isEmpty(email)){
            binding.loginUserEmail.error = "required"
            binding.loginUserEmail.requestFocus()
            return
        }
        if (TextUtils.isEmpty(password)){
            binding.loginUserPassword.error = "required"
            binding.loginUserPassword.requestFocus()
            return
        }
        if (isEmailValid(email)){
/*
            viewModel.login(email,password)

            login_observer = Observer<Resources<LoginResponse>> {response ->
                when(response){
                    is Resources.Success -> {

                        SharedHelper.putKey(requireContext(),authToken,response.data?.token?.token)
                        SharedHelper.putKey(requireContext(),userId, response.data?.userId.toString())

                        Log.e("TAG", "onCreateView: " + response.data?.token?.token)
                        Log.e("TAG", "onCreateView: " + response.message)

                        viewModel.loginResponse.removeObserver(login_observer)

                        moveToDashboard()
                    }
                    is Resources.Faliour -> {
                        hideProgressBar()
                        response.message?.let {
                            Log.e("TAG", "onCreateView: " + it  )
                            Toast.makeText(requireContext(), "Login Fail", Toast.LENGTH_LONG).show()
                        }
                    }
                    is Resources.Loading -> {
                        fragmentLoginScreenBinding.progressbar.visibility = View.VISIBLE
                    }
                }
            }*/
          //  viewModel.loginResponse.observe(viewLifecycleOwner, login_observer)

        }else{
            binding.loginUserEmail.error = "invalid email"
            binding.loginUserEmail.requestFocus()
            return
        }
    }

}