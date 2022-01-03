package com.example.android.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.android.coroutinesdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.increaseButton.setOnClickListener{
            countButton()
        }
        binding.countButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                downloadInfo()
            }
        }
    }

    private fun countButton(){
        binding.textView.text = count++.toString()
    }

    private suspend fun downloadInfo(){
        for (i in 1..200000){
            withContext(Dispatchers.Main){
                binding.textView2.text = i.toString()
            }
        }
    }
}