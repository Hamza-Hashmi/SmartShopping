package com.example.smartshoppingapp.AppUtils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedHelper {
    companion object{
        var sharedPreferences: SharedPreferences? = null
        var editor: SharedPreferences.Editor? = null
//        var isUser = "isUser"

        @SuppressLint("CommitPrefEdits")
        fun putKey(context: Context, Key: String?, Value: String?): String? {
            sharedPreferences = context.getSharedPreferences("Cache", Context.MODE_PRIVATE)
            editor = sharedPreferences!!.edit()
            editor!!.putString(Key, Value)
            editor!!.commit()
            return Key
        }

        fun getKey(contextGetKey: Context, Key: String?): String? {
            sharedPreferences =
                contextGetKey.getSharedPreferences("Cache", Context.MODE_PRIVATE)
            return sharedPreferences!!.getString(Key, "")
        }


        fun deleteAllSharedPrefs() {
            sharedPreferences!!.edit().clear().commit()
        }

    }
}