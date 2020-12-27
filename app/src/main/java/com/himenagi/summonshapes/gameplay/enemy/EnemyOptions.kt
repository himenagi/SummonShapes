package com.himenagi.summonshapes.gameplay.enemy

import android.graphics.PointF
import com.himenagi.summonshapes.enumerators.EnemyMoveType

/**
 * 敵機クラスの設定用クラス
 * @param pos 初期座標
 * @param moveType 移動タイプ
 * @param moveOptions 移動設定
 */
class EnemyOptions(val pos: PointF, val moveType: EnemyMoveType, val moveOptions: EnemyMoveOptions)