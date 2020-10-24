package com.morteza.flappybirdclone

import android.content.*
import android.graphics.*
import android.view.*

/**
 * Created by MJ on 2020/10/24 at 13:53
 */
class GameView(context : Context) : View(context) {

    //Canvas
    private var canvasWidth : Int = 0
    private var canvasHeight : Int = 0

    //Bird
    //    private var bird : Bitmap? = null
    private var bird : ArrayList<Bitmap> = ArrayList(2)
    private val birdX = 10
    private var birdY = 0
    private var birdSpeed = 0

    //Background
    private var backgroundImage : Bitmap? = null

    //Score
    private var scorePaint = Paint()

    //Level
    private var levelPaint = Paint()

    //Life
    private var life : ArrayList<Bitmap> = ArrayList(2)

    //Status check
    private var touchFlag : Boolean = false

    init {
        bird.add(BitmapFactory.decodeResource(resources, R.drawable.bird1))
        bird.add(BitmapFactory.decodeResource(resources, R.drawable.bird2))

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

        life.add(BitmapFactory.decodeResource(resources, R.drawable.heart))
        life.add(BitmapFactory.decodeResource(resources, R.drawable.heart_g))

        // Bird first position
        birdY = 500
    }

    override fun onDraw(canvas : Canvas?) {
        super.onDraw(canvas)

        canvasWidth = width
        canvasHeight = height

        canvas?.drawBitmap(backgroundImage!!, 0F, 0F, null)

        //  Bird
        val minBirdY = bird[0].height
        val maxBirdY = canvasHeight - bird[0].height * 3

        birdY += birdSpeed
        if (birdY < minBirdY) birdY = minBirdY
        if (birdY > maxBirdY) birdY = maxBirdY
        birdSpeed += 2

        if (touchFlag){
            //  Flap wings
            canvas?.drawBitmap(bird[1], birdX.toFloat(), birdY.toFloat(), null)
            touchFlag = false
        }else{
            canvas?.drawBitmap(bird[0], birdX.toFloat(), birdY.toFloat(), null)
        }

        canvas?.drawText("Score : 0", 20F, 60F, scorePaint)

        canvas?.drawText("Lv.1", (width / 2).toFloat(), 60F, levelPaint)

        canvas?.drawBitmap(life[0], 790F, 30F, null)
        canvas?.drawBitmap(life[0], 880F, 30F, null)
        canvas?.drawBitmap(life[1], 970F, 30F, null)
    }

    override fun onTouchEvent(event : MotionEvent?) : Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            touchFlag = true
            birdSpeed = -20
        }
        return true
    }
}