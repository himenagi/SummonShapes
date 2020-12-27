package com.himenagi.summonshapes.gameplay.enemy

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.PointF
import android.util.DisplayMetrics
import com.himenagi.summonshapes.commons.Constants
import com.himenagi.summonshapes.enumerators.EnemyMoveType

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

    private var enemies = mutableListOf<Enemy>()

    fun update() {
        this.enemies.forEach{ enemy -> enemy.update() }

        // 画面外に出た敵機を削除
        var displayMetrics = Resources.getSystem().displayMetrics
        this.enemies.removeAll { enemy ->
            enemy.pos.x + 60.0f < 0.0f - Constants.margin ||
                    displayMetrics.widthPixels + Constants.margin < enemy.pos.x ||
                    enemy.pos.y + 60.0f < 0.0f - Constants.margin ||
                    displayMetrics.heightPixels + Constants.margin < enemy.pos.y }
    }

    fun draw(canvas: Canvas) {
        this.enemies.forEach{ enemy -> enemy.draw(canvas) }
    }
}