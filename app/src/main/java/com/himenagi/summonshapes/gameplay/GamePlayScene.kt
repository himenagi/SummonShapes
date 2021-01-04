package com.himenagi.summonshapes.gameplay

import android.graphics.Canvas
import android.view.MotionEvent
import com.himenagi.summonshapes.commons.GenericScene
import com.himenagi.summonshapes.enumerators.SceneName
import com.himenagi.summonshapes.gameplay.enemy.EnemyManager

class GamePlayScene: GenericScene {
    constructor(): super(SceneName.GamePlay)

    private var shapeManager = ShapeManager()

    private var enemyManager = EnemyManager()

    override fun update(): SceneName {
        this.enemyManager.update()

        return this.mySceneName
    }

    override fun onDown(e: MotionEvent) {
        this.shapeManager.lineStart(e)
    }

    override fun onMove(e: MotionEvent) {
        this.shapeManager.drawLine(e)
    }

    override fun onUp(e: MotionEvent) {
        this.shapeManager.lineEnd()
    }

    override fun draw(canvas: Canvas) {
        this.shapeManager.draw(canvas)
        this.enemyManager.draw(canvas)
    }
}