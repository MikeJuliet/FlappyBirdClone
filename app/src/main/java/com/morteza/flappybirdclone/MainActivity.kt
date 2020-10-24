package com.morteza.flappybirdclone

import android.os.*
import androidx.appcompat.app.*
import java.util.*
import kotlin.concurrent.*

/**
 * Created by MJ on 2020/10/24 at 13:45
 */
class MainActivity : AppCompatActivity() {

    private var gameView : GameView? = null
    private var handler : Handler = Handler()

    companion object {

        private const val TIMER_INTERVAL : Long = 30
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting up gameView
        gameView = GameView(this)
        setContentView(gameView)

        val timer = Timer()
        timer.schedule(timerTask {
            kotlin.run {
                handler.post {
                    gameView!!.invalidate()
                }
            }
        }, 0, TIMER_INTERVAL)
    }
}