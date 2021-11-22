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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.net.compose.ui.components.CenterColumn
import cn.net.compose.ui.components.SecondaryText
import cn.net.compose.ui.components.SvgImage
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import cn.net.compose.wand.HeightSpacer
import org.jetbrains.compose.splitpane.SplitPaneScope

/**
 * Layout overview:
 * ```
 * --------          -----------
 * |  []  |          |  []     |
 * |  []  |    ->    |  []     |
 * |  []  |          |  []     |
 * --------          -----------
 * ```
 *
 * @author 凛 (https://github.com/RinOrz)
 */
fun SplitPaneScope.Sidebar(currentAppearance: AppAppearance) = first(currentAppearance.sidebarMinMaxWidths.first) {
    CenterColumn(Modifier.fillMaxSize().background(currentAppearance.surfaceColor)) {
        HeightSpacer(20.dp)
        Row(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Logo(Modifier.padding(horizontal = 12.dp))
            Column(
                modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
                horizontalAlignment = Alignment.End
            ) {
                SecondaryText(
                    text = "Compose ver.",
                    fontSize = 12.sp
                )
                SecondaryText(
                    text = "1.0.0-beta01",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun Logo(modifier: Modifier) = SvgImage(
    name = currentAppearance.logo,
    modifier = modifier.size(currentAppearance.logoSize),
    contentDescription = "App Logo",
)
