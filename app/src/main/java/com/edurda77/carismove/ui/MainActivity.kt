package com.edurda77.carismove.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.carismove.databinding.ActivityMainBinding
import com.edurda77.carismove.presentation.SceneView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    /*private val drawView: SceneView by lazy {
        layoutInflater.inflate(R.layout.view_draw, null) as SceneView
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val sceneView = SceneView(this)
        setContentView(sceneView)

        //drawView.sceneView()
    }


}

