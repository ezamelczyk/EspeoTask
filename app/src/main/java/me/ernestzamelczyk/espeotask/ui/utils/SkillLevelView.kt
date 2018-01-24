package me.ernestzamelczyk.espeotask.ui.utils

import android.content.Context
import android.util.AttributeSet
import me.ernestzamelczyk.espeotask.R
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorInt
import android.view.View

class SkillLevelView: View {

    var currentLevel: Int = 0
    var maxLevel: Int = 5
    var itemsMargin: Float = 0f
    @ColorInt private var activeColor: Int = 0
    @ColorInt private var inactiveColor: Int = 0

    private var radius: Float = 2f
    private var middle: Float = 0f

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        if(attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.SkillLevelView)
            currentLevel = a.getInt(R.styleable.SkillLevelView_currentLevel, 0)
            maxLevel = a.getInt(R.styleable.SkillLevelView_maxLevel, 5)
            activeColor = a.getColor(R.styleable.SkillLevelView_activeColor, 0)
            inactiveColor = a.getColor(R.styleable.SkillLevelView_inactiveColor, 0)
            itemsMargin = a.getDimension(R.styleable.SkillLevelView_itemsMargin, 0f)
            a.recycle()
        }
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = Math.min((w/maxLevel/2).toFloat() - itemsMargin/2, h.toFloat()/2)
        middle = h.toFloat()/2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until maxLevel) {
            if (i < currentLevel) {
                paint.color = activeColor
                canvas.drawCircle( radius + i * (radius * 2 + itemsMargin), middle, radius, paint)
            } else {
                paint.color = inactiveColor
                canvas.drawCircle(radius + i * (radius * 2 + itemsMargin), middle, radius, paint)
            }
        }
    }

}