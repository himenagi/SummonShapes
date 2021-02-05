package com.himenagi.summonshapes.commons

import android.graphics.PointF
import kotlin.math.sqrt

class Utility {
    companion object {
        fun toRadian(degree :Double): Double {
            return degree * Math.PI / 180.0f
        }

        fun getVectorLength(v: PointF): Float {
            return sqrt((v.x * v.x) + (v.y * v.y))
        }

        fun normalize(v: PointF): PointF {
            var l = this.getVectorLength(v)
            return PointF(v.x / l, v.y / l)
        }
    }
}