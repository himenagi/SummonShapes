package com.himenagi.summonshapes

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.view.View
import com.himenagi.summonshapes.commons.GenericScene
import com.himenagi.summonshapes.enumerators.SceneName
import com.himenagi.summonshapes.gameplay.GamePlayScene

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

    /**
     * シーン構成
     */
    private val scenes = mapOf<SceneName, GenericScene>(
            SceneName.GamePlay to GamePlayScene()
    )

    fun update() {
        this.scenes[this.sceneName]?.update()
        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        this.scenes[this.sceneName]?.draw(canvas)
    }
}