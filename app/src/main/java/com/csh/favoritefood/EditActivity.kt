package com.csh.favoritefood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.csh.favoritefood.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityEditBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}