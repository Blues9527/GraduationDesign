package com.blues.framework.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.blues.myapplication.R


class DrawableEditText : androidx.appcompat.widget.AppCompatEditText {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    private var drawableRight: Int = 0
    private var drawableLeft: Int = 0
    private var drawableWidth: Float = 0f
    private var drawableHeight: Float = 0f
    private var rect: Rect? = null

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableEditText)
        drawableWidth = ta.getDimension(R.styleable.DrawableEditText_drawableWidth, 0f)
        drawableHeight = ta.getDimension(R.styleable.DrawableEditText_drawableHeight, 0f)
        drawableRight = ta.getResourceId(R.styleable.DrawableEditText_drawableRight, 0)
        drawableLeft = ta.getResourceId(R.styleable.DrawableEditText_drawableLeft, 0)

        rect = Rect(0, 0, drawableWidth.toInt(), drawableHeight.toInt())
        ta.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setCompoundDrawables(
                if (drawableLeft == 0) null else resources.getDrawable(drawableRight).apply { bounds = rect!! },
                null,
                if (drawableRight == 0) null else resources.getDrawable(drawableRight).apply { bounds = rect!! },
                null
        )
    }

    private var mListener: OnDrawableListener? = null

    fun setDrawableListener(listener: OnDrawableListener) {
        mListener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_UP -> {
                val drawableRight = compoundDrawables[2]
                if (event.rawX >= right - drawableRight!!.bounds.width()) {
                    mListener!!.onRightListener(this)
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }


    interface OnDrawableListener {
        fun onRightListener(v: View)
    }

}