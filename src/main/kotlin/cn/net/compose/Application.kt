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
@file:JvmName("ComposeCodeHelper")

package cn.net.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.singleWindowApplication
import cn.net.compose.ui.components.getCurrentWindowTitleBar
import cn.net.compose.ui.theme.AppTheme
import cn.net.compose.ui.theme.currentAppearance
import cn.net.compose.ui.view.Sidebar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.cursorForHorizontalResize
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.utils.windowDraggable
import cn.net.compose.ui.view.FocusView
import cn.net.compose.ui.view.Menubar
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.VerticalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

/**
 * Layout overview:
 * ```
 * -----------------
 * |   | _________ |
 * |   |           |
 * |   |           |
 * -----------------
 * ```
 *
 * @author 凛 (https://github.com/RinOrz)
 */
fun main() = singleWindowApplication(
  undecorated = true
) {
  val windowTitleBar = getCurrentWindowTitleBar()
  AppTheme {
    Surface(modifier = Modifier.fillMaxSize()) {
      Column {
        AppWindowTitleBar(windowTitleBar.height)
        AppWindowContent(currentAppearance, window)
      }
    }
  }
  SideEffect {
    // FIXME: Find out the transparent method of IDEA to support Windows system,
    //  or wait for compose-jb support:
    //  https://sourcegraph.com/github.com/JetBrains/intellij-community@master/-/blob/platform/platform-impl/src/com/intellij/openapi/wm/impl/IdeGlassPaneImpl.java?L23&subtree=true
    //  https://github.com/JetBrains/compose-jb/issues/877
    windowTitleBar.hideTitleText()
    windowTitleBar.transparentBackground()
  }
}

@Composable
private fun AppWindowContent(currentAppearance: AppAppearance, window: ComposeWindow) = HorizontalSplitPane(
  modifier = Modifier.background(currentAppearance.backgroundColor)
) {
    Sidebar(currentAppearance)
    second {
      VerticalSplitPane(
        modifier = Modifier.padding(start = 2.dp),
        splitPaneState = rememberSplitPaneState(moveEnabled = false)
      ) {
        Menubar(currentAppearance, window)
        FocusView()
      }
    }
    splitter {
      handle {
        // Draggable area
        Spacer(Modifier.width(10.dp).fillMaxHeight().markAsHandle().cursorForHorizontalResize())
      }
    }
  }

@Composable
private fun AppWindowTitleBar(height: Dp) {
  val gradient = currentAppearance.titleBarColors
  Spacer(
    Modifier
      .fillMaxWidth()
      .height(height)
      .background(
        Brush.verticalGradient(
          0.0f to gradient.first,
          0.5f to gradient.first,
          1.0f to gradient.second,
        )
      )
  )
}
