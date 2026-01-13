package com.aiden.qrcraft.ui.util

import kotlin.math.round

/**
 * Created by AidenChang on 2026/1/12
 */

/**
 * @brief 旋轉路徑計算器的抽象介面。
 * @details
 * 定義了一個用於計算從當前角度到目標角度最短旋轉路徑的契約。
 * 這遵循了【依賴反轉原則】，高層模組將依賴此抽象而非具體實現。
 */
interface RotationCalculator {
    /**
     * 計算並返回一個新的目標角度，以確保動畫路徑最短。
     * @param currentAngle 動畫目前所在的旋轉角度。
     * @param targetAngle 期望達到的目標角度。
     * @return 經過路徑優化的新目標角度。
     */
    fun calculate(currentAngle: Float, targetAngle: Float): Float
}

class CompensatingTurnsRotationCalculator : RotationCalculator {
    /**
     * @brief 計算到達目標角度的最短旋轉路徑 (補償圈數法)。
     * @details
     * 這種方法的思路非常直觀，它將角度問題看作是圈數的補償：
     *
     * 1. **計算圈數差 (Turn Difference):**
     *    首先，我們計算 `currentAngle` 和 `targetAngle` 相對於原點 (0度)
     *    分別轉了多少「圈」。例如，450度是1.25圈，90度是0.25圈。
     *    `turnDiff` 就是這兩個圈數的差值，例如 1.25 - 0.25 = 1.0。
     *
     * 2. **四捨五入到最近的整數圈:**
     *    `round(turnDiff)` 這個操作是此演算法的精髓。它能找到
     *    距離當前圈數最近的「整數圈」。
     *    如果 `turnDiff` 是 1.0，`round` 結果是 1。
     *    如果 `turnDiff` 是 0.8 (例如從 30° 到 320°)，`round` 結果是 1。
     *    如果 `turnDiff` 是 0.2 (例如從 30° 到 100°)，`round` 結果是 0。
     *    這個結果告訴我們，目標角度應該被視為在第幾圈。
     *
     * 3. **計算補償後的目標角度:**
     *    用 `targetAngle` 加上 `roundedTurns * 360`，我們就得到了一個
     *    與 `currentAngle` 在「同一圈」或「最近圈」上的等效目標角度。
     *    例如，如果 `currentAngle` 是 450°，`targetAngle` 是 30°。
     *    `turnDiff` 約為 (450-30)/360 = 1.16，四捨五入為 1。
     *    新的目標角度就是 30 + 1 * 360 = 390°。
     *    動畫就會從 450° 平滑地過渡到 390°，而不是跳躍到 30°。
     *
     * @param currentAngle 動畫目前所在的旋轉角度 (可能會超出 [0, 360] 範圍)。
     * @param targetAngle 期望達到的目標角度 (通常在 [0, 360] 範圍內)。
     * @return 一個經過圈數補償的新目標角度，確保動畫路徑最短。
     */
    override fun calculate(currentAngle: Float, targetAngle: Float): Float {
        // 計算圈數差
        val turnDiff = (currentAngle - targetAngle) / 360f
        // 四捨五入到最近的整數圈
        val roundedTurns = round(turnDiff)
        // 返回補償後的目標角度
        return targetAngle + roundedTurns * 360f
    }
}