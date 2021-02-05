package com.himenagi.summonshapes.gameplay

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.view.MotionEvent

/**
 * 線・図形と囲みに関する処理の管理クラス
 */
class ShapeManager {
    /**
     * 線の軌跡
     */
    private var dots = mutableListOf<PointF>()

    private var shape = mutableListOf<PointF>()

    /**
     * 線の描画開始
     */
    fun lineStart(e: MotionEvent) {
        this.dots = mutableListOf(PointF(e.getX(0), e.getY(0)))
    }

    /**
     * 線を伸ばす
     */
    fun drawLine(e: MotionEvent) {
        // 囲みが発生した後に新しい線を作る場合は画面の再タップを必須とする
        if(this.dots.size == 0) {
            return
        }

        // 一定以上の点間距離を保つ
        var d1 = this.dots.last()
        var d2 = PointF(e.getX(0), e.getY(0))

        var distance = (d1.x - d2.x) * (d1.x - d2.x) + (d1.y - d2.y) * (d1.y - d2.y)

        if(distance >= 50.0f * 50.0f) {
            this.dots.add(d2)

            var result = this.isSurround()
            if(result != null) {
                // 囲みが発生した場合、その部分を図形として切り出し線の描画を終了する
                this.shape = this.dots.drop(result.first).take(result.second - result.first).toMutableList()
                this.lineEnd()
            }
        }
    }

    /**
     * 線の描画終了(線を消す)
     */
    fun lineEnd() {
        this.dots = mutableListOf<PointF>()
    }

    fun draw(canvas: Canvas) {
        var paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 8.0f
        paint.isAntiAlias = true

        // 軌跡の描画
        //this.dots.forEach {
        //    canvas.drawCircle(it.x, it.y, 18.0f, paint)
        //}

        // 線の描画
        this.dots.forEachIndexed { index, dot ->
            if(index == 0) {
                return@forEachIndexed
            }

            var prevDot = this.dots[index - 1]
            canvas.drawLine(prevDot.x, prevDot.y, dot.x, dot.y, paint)
        }

        // 図形の描画
        paint.color = Color.RED
        this.shape.forEachIndexed() { index, dot ->
            var prevDot = if(index == 0) this.shape.last() else this.shape[index - 1]
            canvas.drawLine(prevDot.x, prevDot.y, dot.x, dot.y, paint)
        }
    }

    fun isCollide(gameObject: GameObject) {
        // 線とオブジェクトとの当たり判定
        if(false) {
            this.lineEnd()
        }

        // 図形がオブジェクトを囲んでいるか判定
        if(false) {
            gameObject.surrounded()
        }
    }

    /**
     * 線で囲まれた領域が発生したか判定
     * 判定基準：
     * ① 始点と終点が一定距離以内まで近付く
     * ② 既存の線と最新の線分が交差する
     * @return 囲みが発生したらその部分の始点と終点の要素番号、それ以外はnull
     */
    private fun isSurround(): Pair<Int, Int>? {
        if(this.dots.size < 4) {
            return null;
        }

        // 判定基準①---------------------------------------------------------------------------------------

        var start = this.dots.first()
        var end = this.dots.last()

        var distance = (start.x - end.x) * (start.x - end.x) + (start.y - end.y) * (start.y - end.y);

        if(distance <= 100.0f * 100.0f) {
            return Pair(0, this.dots.size - 1)
        }

        // 判定基準①---------------------------------------------------------------------------------------

        // 判定基準②---------------------------------------------------------------------------------------

        // 最新の線分
        var d1 = this.dots.drop(this.dots.size - 2).take(2).toList()

        var oldDots = this.dots.take(this.dots.size - 2).toList()
        oldDots.forEachIndexed { index, dot ->
            if(index == 0) {
                return@forEachIndexed
            }

            // 判定対象の線分
            var d2 = listOf(oldDots[index - 1], dot)

            // 線分d1と線分d2の交差判定
            var s1 = (d1[0].x - d1[1].x) * (d2[0].y - d1[0].y) - (d1[0].y - d1[1].y) * (d2[0].x - d1[0].x);
            var t1 = (d1[0].x - d1[1].x) * (d2[1].y - d1[0].y) - (d1[0].y - d1[1].y) * (d2[1].x - d1[0].x);
            var s2 = (d2[0].x - d2[1].x) * (d1[0].y - d2[0].y) - (d2[0].y - d2[1].y) * (d1[0].x - d2[0].x);
            var t2 = (d2[0].x - d2[1].x) * (d1[1].y - d2[0].y) - (d2[0].y - d2[1].y) * (d1[1].x - d2[0].x);

            if(s1 * t1 < 0.0f && s2 * t2 < 0.0f) {
                return Pair(index, this.dots.size - 2)
            }
        }

        // 判定基準②---------------------------------------------------------------------------------------

        return null
    }
}