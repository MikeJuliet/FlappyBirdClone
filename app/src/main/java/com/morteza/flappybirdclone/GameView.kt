package com.morteza.flappybirdclone

import android.content.*
import android.graphics.*
import android.view.*

/**
 * Created by MJ on 2020/10/24 at 13:53
 */
class GameView(context : Context) : View(context) {

    //Bird
    private var bird : Bitmap? = null

    //Background
    private var backgroundImage : Bitmap? = null

    //Score
    private var scorePaint = Paint()

    //Level
    private var levelPaint = Paint()

    //Life
    private var life : ArrayList<Bitmap>? = null

    init {
        bird = BitmapFactory.decodeResource(resources, R.drawable.bird1)
        backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.bg)

        scorePaint.color = Color.BLACK
        scorePaint.textSize = 32F
        scorePaint.typeface = Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias = true

        levelPaint.color = Color.DKGRAY
        levelPaint.textSize = 32F
        levelPaint.typeface = Typeface.DEFAULT_BOLD
        levelPaint.textAlign = Paint.Align.CENTER
        levelPaint.isAntiAlias = true

        life = ArrayList(2)
        life!!.add(BitmapFactory.decodeResource(resources, R.drawable.heart))
        life!!.add(BitmapFactory.decodeResource(resources, R.drawable.heart_g))
    }

    override fun onDraw(canvas : Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(backgroundImage!!, 0F, 0F, null)

        canvas?.drawBitmap(bird!!, 0F, 0F, null)

        canvas?.drawText("Score : 0", 20F, 60F, scorePaint)

        canvas?.drawText("Lv.1", (width / 2).toFloat(), 60F, levelPaint)

        canvas?.drawBitmap(life!![0], 790F, 30F, null)
        canvas?.drawBitmap(life!![0], 880F, 30F, null)
        canvas?.drawBitmap(life!![1], 970F, 30F, null)
    }
}