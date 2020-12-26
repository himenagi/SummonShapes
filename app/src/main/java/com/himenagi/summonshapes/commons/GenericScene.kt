package com.himenagi.summonshapes.commons

import android.graphics.Canvas
import com.himenagi.summonshapes.enumerators.SceneName
import kotlin.properties.Delegates

open class GenericScene(protected val mySceneName: SceneName) {
    /**
     * シーン状態リセット
     */
    open fun reflesh() {

    }

    /**
     * 画面更新処理
     */
    open fun update(): SceneName {
        return this.mySceneName
    }

    /**
     * 画面描画処理
     */
    open fun draw(canvas: Canvas) {

    }
}