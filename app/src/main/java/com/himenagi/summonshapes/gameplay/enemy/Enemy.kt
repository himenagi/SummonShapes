package com.himenagi.summonshapes.gameplay.enemy

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import com.himenagi.summonshapes.commons.Utility
import com.himenagi.summonshapes.enumerators.EnemyMoveType
import kotlin.properties.Delegates
import kotlin.reflect.KFunction

/**
 * 敵機クラス
 */
class Enemy(options: EnemyOptions) {
    var pos = options.pos
        private set

    private val moveType = options.moveType

    private val moveOptions = options.moveOptions

    private var count = 0L

    private val move = mapOf<EnemyMoveType, () -> Unit>(
            EnemyMoveType.Straight to ::move_straight
    )

    fun update() {
        this.move[this.moveType]?.invoke()

        this.count++
    }

    fun draw(canvas: Canvas) {
        var paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 8.0f
        paint.isAntiAlias = true

        canvas.drawCircle(this.pos.x, this.pos.y, 60.0f, paint)
    }



    //---------------------------------------------------------------------------------------------------------
    // 移動用関数 ここから
    //---------------------------------------------------------------------------------------------------------
    private fun move_straight() {
        this.pos.x += this.moveOptions.velocity * Math.cos(Utility.toRadian((this.moveOptions.direction.toDouble()))).toFloat()
        this.pos.y += this.moveOptions.velocity * Math.sin(Utility.toRadian((this.moveOptions.direction.toDouble()))).toFloat()
    }
}