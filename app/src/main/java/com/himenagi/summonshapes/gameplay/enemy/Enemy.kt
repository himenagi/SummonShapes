package com.himenagi.summonshapes.gameplay.enemy

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import com.himenagi.summonshapes.commons.Constants
import com.himenagi.summonshapes.commons.Utility
import com.himenagi.summonshapes.enumerators.EnemyMoveType
import com.himenagi.summonshapes.gameplay.GameObject
import kotlin.properties.Delegates
import kotlin.reflect.KFunction

/**
 * 敵機クラス
 */
class Enemy(options: EnemyOptions): GameObject() {
    init {
        pos = options.pos
    }

    private val moveType = options.moveType

    private val moveOptions = options.moveOptions

    private val move = mapOf<EnemyMoveType, () -> Unit>(
            EnemyMoveType.Straight to ::move_straight
    )

    override fun update() {
        this.move[this.moveType]?.invoke()

        // 画面外に出たら消す
        var displayMetrics = Resources.getSystem().displayMetrics
        if(pos.x + 60.0f < 0.0f - Constants.margin ||
                displayMetrics.widthPixels + Constants.margin < pos.x ||
                pos.y + 60.0f < 0.0f - Constants.margin ||
                displayMetrics.heightPixels + Constants.margin < pos.y) {
            isLive = false
        }

        this.count++
    }

    override fun draw(canvas: Canvas) {
        var paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 8.0f
        paint.isAntiAlias = true

        canvas.drawCircle(this.pos.x, this.pos.y, 60.0f, paint)
    }

    override fun surrounded() {
        isLive = false
    }



    //---------------------------------------------------------------------------------------------------------
    // 移動用関数 ここから
    //---------------------------------------------------------------------------------------------------------
    private fun move_straight() {
        this.pos.x += this.moveOptions.velocity * Math.cos(Utility.toRadian((this.moveOptions.direction.toDouble()))).toFloat()
        this.pos.y += this.moveOptions.velocity * Math.sin(Utility.toRadian((this.moveOptions.direction.toDouble()))).toFloat()
    }
}