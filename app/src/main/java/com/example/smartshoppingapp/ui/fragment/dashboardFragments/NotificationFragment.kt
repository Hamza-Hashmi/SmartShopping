package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartshoppingapp.Adapter.NotificationAdapter
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.FragmentNotificationBinding
import com.example.smartshoppingapp.model.DataX
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationFragment : Fragment() {
    lateinit var viewModel: RegistrationViewModel

     var binding:FragmentNotificationBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        val userId = SharedHelper.getKey(requireActivity(), "userID")

        Log.e("TAG", "onCreateView: $userId" )
        binding?.progressbar?.visibility = View.VISIBLE
        viewModel = (activity as DashBoardActivity).viewModel
        if (userId != null) {
            /*try{
                viewModel.getNotification(userId.toInt())
            }catch (e:Exception){

            }*/

                CoroutineScope(Dispatchers.IO).launch {
                    delay(2000)
                }.invokeOnCompletion {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(requireContext(), "An internal error occured", Toast.LENGTH_LONG).show()
                        binding?.progressbar?.visibility = View.GONE
                    }
                }

            viewModel._notifications.observe(viewLifecycleOwner,{

                if (it.body()?.data?.size!! > 0){
                    binding?.progressbar?.visibility = View.GONE

                    val adapter = NotificationAdapter(it.body()?.data as ArrayList<DataX>)

                    binding?.notificationRv?.adapter = adapter
                    Log.e("TAG", "onCreateView notification: ${it.body()?.data}" )
                }
                else{
                    Toast.makeText(requireContext(), "No Notification.... ", Toast.LENGTH_LONG).show()
                }

            })
        }


            return binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}