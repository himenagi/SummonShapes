package com.himenagi.summonshapes.gameplay

import android.graphics.Canvas
import com.himenagi.summonshapes.commons.GenericScene
import com.himenagi.summonshapes.enumerators.SceneName
import com.himenagi.summonshapes.gameplay.enemy.EnemyManager

class GamePlayScene: GenericScene {
    constructor(): super(SceneName.GamePlay)

    private var enemyManager = EnemyManager()

    override fun update(): SceneName {
        this.enemyManager.update()

        return super.update()
    }

    override fun draw(canvas: Canvas) {
        this.enemyManager.draw(canvas)
    }
}