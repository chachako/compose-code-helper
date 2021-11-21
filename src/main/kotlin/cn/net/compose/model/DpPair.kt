package cn.net.compose.model

import androidx.compose.ui.unit.Dp
import com.meowool.sweekt.IntPair
import com.meowool.sweekt.packFloats
import com.meowool.sweekt.unpackFloat1
import com.meowool.sweekt.unpackFloat2

/**
 * A pair of DP values.
 *
 * @author 凛 (https://github.com/RinOrz)
 */
@JvmInline
value class DpPair(private val packedFloat: Long) {
  val first: Dp get() = Dp(unpackFloat1(packedFloat))
  val second: Dp get() = Dp(unpackFloat2(packedFloat))
}

/**
 * Creates a tuple of type [IntPair] from `this` and [that].
 *
 * This can be useful for creating [Map] literals with less noise.
 *
 * @see Pair
 * @author 凛 (https://github.com/RinOrz)
 */
infix fun Dp.to(that: Dp): DpPair = DpPair(packFloats(this.value, that.value))