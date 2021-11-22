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
@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.CenterRow
import cn.net.compose.ui.components.SearchBar
import cn.net.compose.ui.components.SvgImageButton
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.wand.WidthSpacer
import org.jetbrains.compose.splitpane.SplitPaneScope

fun SplitPaneScope.Menubar(currentAppearance: AppAppearance) = first(currentAppearance.menuBarHeight) {
    CenterRow(Modifier.fillMaxSize().background(currentAppearance.surfaceColor)) {
        WidthSpacer(16.dp)
        SearchBar()
        Box(Modifier.weight(1f))
        SvgImageButton(currentAppearance.iconTranslate) {
        }
        SvgImageButton(currentAppearance.iconSideController) {
        }
        WidthSpacer(16.dp)
    }
}
