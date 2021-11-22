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
@file:Suppress("LeakingThis") // FIXME: https://github.com/RinOrz/sweekt/issues/7

package cn.net.compose.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cn.net.compose.model.ColorPair
import cn.net.compose.model.DpPair
import cn.net.compose.model.to

/**
 * @author 凛 (https://github.com/RinOrz)
 */
abstract class AppAppearance {
    abstract val isLight: Boolean

    abstract val logo: String

    abstract val contentColor: Color

    abstract val surfaceColor: Color

    abstract val backgroundColor: Color

    abstract val titleBarColors: ColorPair

    abstract val searchBarColor: Color

    val iconSizeDefault: Dp = 24.dp

    val iconButtonPaddingDefault: PaddingValues = PaddingValues(16.dp)

    val logoSize: Dp = 52.dp

    val sidebarMinMaxWidths: DpPair = 82.dp to 214.dp

    val menuBarHeight: Dp = 86.dp

    val iconTranslate: String = "translate"

    val iconSideController: String = "sideController"

    val shapes @Composable get() = MaterialTheme.shapes

    fun toMaterialColors(): Colors = Colors(
        primary = surfaceColor,
        primaryVariant = surfaceColor,
        secondary = surfaceColor,
        secondaryVariant = surfaceColor,
        background = backgroundColor,
        surface = surfaceColor,
        error = Color(0xFFCF6679),
        onPrimary = contentColor,
        onSecondary = contentColor,
        onBackground = contentColor,
        onSurface = contentColor,
        onError = contentColor,
        isLight = isLight
    )

    companion object {
        val Local = staticCompositionLocalOf<AppAppearance> {
            error("CompositionLocal LocalComposeScene not provided")
        }
    }
}

val currentAppearance: AppAppearance
    @Composable
    get() = AppAppearance.Local.current
