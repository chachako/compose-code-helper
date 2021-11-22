/*
 * Copyright (c) 2021. The Compose Museum Open Source Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    https://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * In addition, if you modified the project, you must include the Compose Museum
 * organization URL in your code file: https://github.com/compose-museum/
 * 
 * 如果您修改了此项目，请确保源文件中包含 Compose 博物馆 URL: https://github.com/compose-museum/
 */
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
