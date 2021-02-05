package com.himenagi.summonshapes.gameplay.enemy

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.PointF
import android.util.DisplayMetrics
import com.himenagi.summonshapes.commons.Constants
import com.himenagi.summonshapes.enumerators.EnemyMoveType
import java.util.*

/**
 * 敵機管理クラス
 */
class EnemyManager {
    constructor() {
        var options = EnemyOptions(
                PointF(200.0f, 50.0f),
                EnemyMoveType.Straight,
                EnemyMoveOptions(4.0f, 0.0f)
        )

        enemies.add(Enemy(options))
    }

    var enemies = mutableListOf<Enemy>()

    fun update() {
        this.enemies.forEach { it.update() }

        // 死んでいる敵を消す
        this.enemies.removeAll { !it.isLive }
    }

    fun draw(canvas: Canvas) {
        this.enemies.forEach { it.draw(canvas) }
    }
}