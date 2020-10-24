package com.morteza.flappybirdclone

import android.os.*
import android.widget.*
import androidx.appcompat.app.*

/**
 * Created by MJ on 2020/10/24 at 13:45
 */
class MainActivity:AppCompatActivity() {
    private var gameView:GameView? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting up gameView
        gameView = GameView(this)
        setContentView(gameView)
    }
}