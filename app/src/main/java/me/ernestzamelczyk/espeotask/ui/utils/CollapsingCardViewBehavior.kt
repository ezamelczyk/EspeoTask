package me.ernestzamelczyk.espeotask.ui.utils

import android.content.Context
import android.support.design.widget.*
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import me.ernestzamelczyk.espeotask.R
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewCompat

class CollapsingCardViewBehavior : CoordinatorLayout.Behavior<CardView> {

    private var heightHasBeenSet: Boolean = false

    private var targetViewId: Int = View.NO_ID
    private var elevationTargetViewId: Int = View.NO_ID

    private var targetView: View? = null
    private var elevationTargetView: View? = null

    private var initialElevation: Float = 0f
    private var initialCardCornerRadius: Float = 0f

    private var initialX: Float = -1f
    private var initialY: Float = -1f
    private var initialHeight: Int = 0
    private var initialWidth: Int = 0
    private var targetY: Float = 0f
    private var targetX: Float = 0f
    private var targetHeight: Int = 0
    private var targetWidth: Int = 0

    @Suppress("unused")
    constructor() : super()

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CollapsingCardViewBehavior)
            targetViewId = a.getResourceId(R.styleable.CollapsingCardViewBehavior_targetView, View.NO_ID)
            elevationTargetViewId = a.getResourceId(R.styleable.CollapsingCardViewBehavior_elevationTarget, View.NO_ID)
            a.recycle()
        }
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: CardView?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: CardView?, dependency: View?): Boolean {
        if(dependency is AppBarLayout) {
            maybeInit(parent, child, dependency)
            val target = targetView
            if(target != null) {
                val factor = getScrollFactor(dependency)
                val y = initialY + (factor * (targetY - initialY))
                val x = initialX + (factor * (targetX - initialX))
                val height = initialHeight + (factor * (targetHeight - initialHeight)).toInt()
                val width = initialWidth + (factor * (targetWidth - initialWidth)).toInt()
                child?.apply {
                    val lp = layoutParams
                    lp.height = height
                    lp.width = width
                    layoutParams = lp
                    this.y = y
                    this.x = x
                    if(factor == 1f) {
                        ViewCompat.setElevation(elevationTargetView, cardElevation)
                        radius = 0f
                    } else {
                        ViewCompat.setElevation(elevationTargetView, 0f)
                        radius = initialCardCornerRadius
                    }
                }
            }
        }
        return false
    }

    private fun maybeInit(parent: CoordinatorLayout?, child: CardView?, dependency: AppBarLayout) {
        if(parent != null) {
            if(elevationTargetView == null && elevationTargetViewId != View.NO_ID) {
                elevationTargetView = parent.findViewById(elevationTargetViewId)
            }
            if (targetView == null && targetViewId != View.NO_ID) {
                val target = parent.findViewById<View>(targetViewId)
                if(target != null) {
                    if(targetY == 0f) {
                        targetY = target.y - child!!.paddingTop
                    }
                    if(targetX == 0f) {
                        targetX = target.x - child!!.paddingLeft
                    }
                    if(targetHeight == 0) {
                        targetHeight = target.height
                    }
                    if(targetWidth == 0) {
                        targetWidth = target.width + child!!.paddingLeft + child.paddingRight
                    }
                    targetView = target
                }
            }
            if(child != null) {
                if (initialHeight == 0) {
                    initialHeight = child.height
                }
                if(initialWidth == 0) {
                    initialWidth = child.width
                }
                if(initialX == -1f) {
                    initialX = child.x
                }
                if(initialY == -1f) {
                    initialY = child.y
                }
                if(initialElevation == 0f) {
                    initialElevation = child.cardElevation
                }
                if(initialCardCornerRadius == 0f) {
                    initialCardCornerRadius = child.radius
                }

                val factor = getScrollFactor(dependency)
                if(factor == 0f && !heightHasBeenSet) {
                    val lp = dependency.layoutParams
                    lp.height = child.bottom
                    dependency.layoutParams = lp
                    heightHasBeenSet = true
                }
            }
        }
    }

    private fun getScrollFactor(dependency: AppBarLayout): Float {
        val scrollRange = dependency.totalScrollRange
        return - dependency.y / scrollRange
    }

}