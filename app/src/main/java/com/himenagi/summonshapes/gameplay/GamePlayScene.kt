package com.himenagi.summonshapes.gameplay

import android.graphics.Canvas
import android.view.MotionEvent
import com.himenagi.summonshapes.commons.GenericScene
import com.himenagi.summonshapes.enumerators.SceneName
import com.himenagi.summonshapes.gameplay.enemy.EnemyManager
import java.util.*

class GamePlayScene: GenericScene {
    constructor(): super(SceneName.GamePlay)

    private var shapeManager = ShapeManager()

    private var enemyManager = EnemyManager()

    override fun update(): SceneName {
        this.enemyManager.update()

        // 図形とオブジェクトとの当たり判定
        this.createGameObjects().forEach { this.shapeManager.isCollide(it) }

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


    /**
     * GameObjectのリストを作成する
     */
    private fun createGameObjects(): List<GameObject> {
        var gameObjects = mutableListOf<GameObject>()
        gameObjects.addAll(this.enemyManager.enemies)

        return Collections.unmodifiableList(gameObjects)
    }
}