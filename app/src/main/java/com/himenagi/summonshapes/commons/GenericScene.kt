package com.himenagi.summonshapes.commons

import android.graphics.Canvas
import android.view.MotionEvent
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
     * タッチ時のイベント
     */
    open fun onDown(e: MotionEvent) {

    }

    /**
     * スワイプ時のイベント
     */
    open fun onMove(e: MotionEvent) {

    }

    /**
     * 画面から指を離した時のイベント
     */
    open fun onUp(e: MotionEvent) {

    }

    /**
     * 画面描画処理
     */
    open fun draw(canvas: Canvas) {

    }
}