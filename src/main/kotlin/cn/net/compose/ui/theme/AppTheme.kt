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
package cn.net.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(
    darkAppearance: AppAppearance = AppDarkAppearance(),
    lightAppearance: AppAppearance = darkAppearance /* FIXME */,
    content: @Composable () -> Unit
) {
    val appearance = when (isSystemInDarkTheme()) {
        false -> lightAppearance
        true -> darkAppearance
    }
    val shapes = Shapes(
        small = RoundedCornerShape(size = 10.dp),
        medium = RoundedCornerShape(size = 14.dp),
        large = RoundedCornerShape(size = 16.dp),
    )

    MaterialTheme(colors = appearance.toMaterialColors(), shapes = shapes) {
        CompositionLocalProvider(AppAppearance.Local provides appearance, content = content)
    }
}
