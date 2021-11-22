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
package cn.net.compose.ui.components

import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface WindowTitleBar {
    val height: Dp
    val isDecorated: Boolean

    fun transparentBackground(window: ComposeWindow)
    fun hideTitleText(window: ComposeWindow)

    /**
     * Title bar common processing of any system.
     * Do not do any title bar processing for unknown systems at this time.
     *
     * @author 凛 (https://github.com/RinOrz)
     */
    open class Default : WindowTitleBar {
        // Use the title bar of the system
        override val height: Dp = Dp.Hairline
        override val isDecorated: Boolean = false

        override fun transparentBackground(window: ComposeWindow) = Unit
        override fun hideTitleText(window: ComposeWindow) = Unit
    }

    /**
     * Title bar processing of macOS system.
     *
     * @author 凛 (https://github.com/RinOrz)
     */
    class MacOS : Default() {
        override val height: Dp = 28.dp
        override val isDecorated: Boolean = true

        override fun transparentBackground(window: ComposeWindow) = window.rootPane.run {
            putClientProperty(WindowFullContent, true)
            putClientProperty(WindowTransparentTitleBar, true)
        }

        override fun hideTitleText(window: ComposeWindow) = window.rootPane.run {
            when {
                getClientProperty(WindowTitleVisible) != null -> putClientProperty(WindowTitleVisible, false)
                else -> window.title = " "
            }
        }

        private companion object Keys {
            // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L126
            const val WindowFullContent = "apple.awt.fullWindowContent"
            // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L127
            const val WindowTransparentTitleBar = "apple.awt.transparentTitleBar"
            // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L128
            const val WindowTitleVisible = "apple.awt.windowTitleVisible"
        }
    }

    class Windows : Default() {
        override val height: Dp = 36.dp
    }
}
