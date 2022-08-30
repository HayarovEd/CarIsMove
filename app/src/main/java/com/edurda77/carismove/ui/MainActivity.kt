package com.edurda77.carismove.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.carismove.databinding.ActivityMainBinding
import com.edurda77.carismove.presentation.SceneView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val sceneView = SceneView(this)
        setContentView(sceneView)
    }


}

