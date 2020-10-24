package com.morteza.flappybirdclone

import android.content.*
import android.graphics.*
import android.util.*
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

    //Blue ball
    private var blueBallX = 0
    private var blueBallY = 0
    private val blueBallSpeed = 15
    private val blueBallPaint = Paint()

    //Black ball
    private var blackBallX = 0
    private var blackBallY = 0
    private val blackBallSpeed = 20
    private val blackBallPaint = Paint()

    //Background
    private var backgroundImage : Bitmap? = null

    //Score
    private var scorePaint = Paint()
    private var score : Int

    //Level
    private var levelPaint = Paint()

    //Life
    private var life : ArrayList<Bitmap> = ArrayList(2)
    private var lifeCount : Int? = null

    //Status check
    private var touchFlag : Boolean = false

    init {
        bird.add(BitmapFactory.decodeResource(resources, R.drawable.bird1))
        bird.add(BitmapFactory.decodeResource(resources, R.drawable.bird2))

        backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.bg)

        blueBallPaint.color = Color.BLUE
        blueBallPaint.isAntiAlias = false

        blueBallPaint.color = Color.BLACK
        blueBallPaint.isAntiAlias = false

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
        score = 0
        lifeCount = 3
    }

    override fun onDraw(canvas : Canvas?) {
        super.onDraw(canvas)

        canvasWidth = width
        canvasHeight = height

        backgroundImage = Bitmap.createScaledBitmap(backgroundImage!!, width, height, false)
        canvas?.drawBitmap(backgroundImage!!, 0F, 0F, null)

        //  Bird
        val minBirdY = bird[0].height
        val maxBirdY = canvasHeight - bird[0].height * 3

        birdY += birdSpeed
        if (birdY < minBirdY) birdY = minBirdY
        if (birdY > maxBirdY) birdY = maxBirdY
        birdSpeed += 2

        if (touchFlag) {
            //  Flap wings
            canvas?.drawBitmap(bird[1], birdX.toFloat(), birdY.toFloat(), null)
            touchFlag = false
        } else {
            canvas?.drawBitmap(bird[0], birdX.toFloat(), birdY.toFloat(), null)
        }

        //Blue ball
        blueBallX -= blueBallSpeed
        if (hitCheck(blueBallX, blueBallY)) {
            score += 10
            blueBallX = -100
        }
        if (blueBallX < 0) {
            blueBallX = canvasWidth + 20
            blueBallY = (Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY).toInt()
        }
        canvas?.drawCircle(blueBallX.toFloat(), blueBallY.toFloat(), 10F, blueBallPaint)

        //Black ball
        blackBallX -= blackBallSpeed
        if (hitCheck(blackBallX, blackBallY)) {
            blackBallX = -100
            lifeCount!! - 1
            if (lifeCount == 0) {
                //Game Over
                Log.e("MESSAGE", "GAME OVER")
            }
        }
        if (blackBallX < 0) {
            blackBallX = canvasWidth + 200
            blackBallY = (Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY).toInt()
        }
        canvas?.drawCircle(blackBallX.toFloat(), blackBallY.toFloat(), 20F, blackBallPaint)

        //Score
        canvas?.drawText("Score : $score", 20F, 60F, scorePaint)

        //Level
        canvas?.drawText("Lv.1", (width / 2).toFloat(), 60F, levelPaint)

        //Life
        for (i in 0..3) {
            val x : Int = (560 + life[0].width + 1.5 * i).toInt()
            val y : Int = 30

            if (i < lifeCount!!) {
                canvas?.drawBitmap(life[0], x.toFloat(), y.toFloat(), null)
            }else{
                canvas?.drawBitmap(life[1], x.toFloat(), y.toFloat(), null)
            }
        }
//        canvas?.drawBitmap(life[0], 790F, 30F, null)
//        canvas?.drawBitmap(life[0], 880F, 30F, null)
//        canvas?.drawBitmap(life[1], 970F, 30F, null)
    }

    fun hitCheck(x : Int, y : Int) : Boolean {
        if (birdX < x && x < (birdX + bird[0].width) && birdY < y && y < (birdY + bird[0].height)) {
            return true
        }
        return false
    }

    override fun onTouchEvent(event : MotionEvent?) : Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            touchFlag = true
            birdSpeed = -20
        }
        return true
    }
}