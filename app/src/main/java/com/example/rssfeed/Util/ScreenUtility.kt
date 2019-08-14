package com.example.rssfeed.Util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

//im using this to determine the width/height of the screen and its pixel density
class ScreenUtility(activity: Activity) {

    private val display = activity.windowManager.defaultDisplay
    private val outMetrics = DisplayMetrics()
    private val density = activity.resources.displayMetrics.density

    fun getDpWidth(): Float{
        display.getMetrics(outMetrics)
        val dpWidth = outMetrics.widthPixels / density
        return dpWidth
    }

    fun getDpHeight(): Float{
        display.getMetrics(outMetrics)
        val dpHeight = outMetrics.heightPixels / density
        return dpHeight
    }
}