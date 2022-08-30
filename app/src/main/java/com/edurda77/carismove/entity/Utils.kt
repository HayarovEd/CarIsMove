package com.edurda77.carismove.entity

import android.graphics.Color
import android.graphics.Paint


fun setPaint(paint: Paint) {
    paint.color = Color.RED
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 3F
}