package com.himenagi.summonshapes

import android.content.Context
import android.os.Handler
import android.view.View
import com.himenagi.summonshapes.enumerators.SceneName

class MainView constructor(context: Context) : View(context) {
    init {
        // 定時更新処理登録
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                this@MainView.update()
                handler.postDelayed(this, 100)
            }
        }

        handler.post(runnable)
    }

    /**
     * 現在のシーン
     */
    private var sceneName = SceneName.GamePlay

    public fun update() {
        invalidate()
    }
}