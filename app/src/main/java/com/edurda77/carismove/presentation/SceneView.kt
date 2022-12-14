package com.edurda77.carismove.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.edurda77.carismove.R
import com.edurda77.carismove.entity.setPaint

const val MAX_STEP = 100

class SceneView @JvmOverloads constructor (context: Context): View(context) {
    private lateinit var bmpCar : Bitmap
    private var rSrc = Rect()
    private lateinit var rDest : Rect
    private lateinit var paint: Paint
    private lateinit var regionTouch: RectF
    private lateinit var pm: PathMeasure
    private var fSegment: Float = 0f
    private val ptLine = Path()
    private var currentStep = 0
    private val points = mutableListOf<PointF>()
    private var isTouch = false


    override fun onDraw(canvas: Canvas) {
        initBmp(canvas)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        setPaint(paint)
        var point = points.first()
        ptLine.moveTo(point.x, point.y)

        points.forEach {
            ptLine.lineTo(it.x, it.y)
            canvas.drawLine(point.x, point.y, it.x, it.y, paint)
            point = it
        }
        if (isTouch) {
            pm = PathMeasure(ptLine, false)
            fSegment = pm.length/MAX_STEP
            val mxTransform = Matrix()
            if (currentStep < MAX_STEP) {
                pm.getMatrix(
                    fSegment * currentStep,
                    mxTransform,
                    PathMeasure.POSITION_MATRIX_FLAG + PathMeasure.TANGENT_MATRIX_FLAG
                )
                mxTransform.preTranslate(
                    -bmpCar.width.toFloat() + 50,
                    -bmpCar.height.toFloat() + 25
                )
                canvas.drawBitmap(bmpCar, mxTransform, null)
                ++currentStep
                invalidate()
            } else {
                currentStep = 0
                isTouch = false
                initStart(canvas)

            }
        } else {
            initStart(canvas)
        }
        super.onDraw(canvas)
    }

    private fun initBmp(canvas:Canvas) {
        val bmpRoad = BitmapFactory.decodeResource(resources, R.drawable.ic_road)
        rSrc = Rect(0, 0, bmpRoad.width, bmpRoad.height)
        val options = BitmapFactory.Options()
        options.inSampleSize = 30
        bmpCar = BitmapFactory.decodeResource(resources, R.drawable.ic_car, options)
        rDest = Rect(0, 0, width, height)
        canvas.drawBitmap(bmpRoad, rSrc, rDest, null)
        addToList()
    }

    private fun initStart(canvas:Canvas) {
        val start = points.first()
        canvas.drawBitmap(bmpCar, start.x, start.y - 20, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        setPaint(paint)
        regionTouch = RectF(
            start.x,
            (start.y) - bmpCar.height / 2,
            bmpCar.width.toFloat(),
            (start.y + 10) + bmpCar.height / 2
        )
    }

    private fun addToList() {
        points.add(PointF(0.99907494F, 205.79361F))
        points.add(PointF(269.75024F, 217.78806F))
        points.add(PointF(264.75485F, 840.49976F))
        points.add(PointF(351.67438F, 861.49F))
        points.add(PointF(409.62073F, 961.4437F))
        points.add(PointF(430.6013F, 1386.2471F))
        points.add(PointF(774.2831F, 1377.2512F))
        points.add(PointF(801.2581F, 1498.1952F))
        points.add(PointF(1039.038F, 1468.2091F))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.actionMasked
        if (action == MotionEvent.ACTION_DOWN && regionTouch.contains(event.x, event.y)) {
            isTouch = true
            invalidate()
            return true
        }
        return false
    }
}