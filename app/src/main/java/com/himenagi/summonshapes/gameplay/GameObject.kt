package com.himenagi.summonshapes.gameplay

import android.graphics.Canvas
import android.graphics.PointF

/**
 * 画面内に配置されるオブジェクトの共通基底クラス
 */
open class GameObject {
    var pos = PointF()
        protected set

    var isLive = true
        protected set

    protected var count = 0L

    open fun update() {

    }

    open fun draw(canvas: Canvas) {

    }

    open fun surrounded() {

    }
}