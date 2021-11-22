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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.currentAppearance
import com.meowool.sweekt.firstCharTitlecase

@Composable
fun SvgImage(
    name: String,
    contentDescription: String? = name.firstCharTitlecase(),
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    size: Dp = currentAppearance.iconSizeDefault
) = Image(
    painter = painterGraphicsSvg(name),
    contentDescription = contentDescription,
    modifier = modifier.size(size),
    alignment = alignment,
    contentScale = contentScale,
    alpha = alpha,
    colorFilter = colorFilter
)

@Composable
fun SvgImageButton(
    name: String,
    contentDescription: String? = name.firstCharTitlecase(),
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape = CircleShape,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    size: Dp = currentAppearance.iconSizeDefault,
    contentPadding: PaddingValues = currentAppearance.iconButtonPaddingDefault,
    onClick: () -> Unit,
) = Button(
    onClick,
    elevation = null,
    contentPadding = contentPadding,
    shape = shape,
    colors = ButtonDefaults.buttonColors(Color.Transparent)
) {
    SvgImage(name, contentDescription, modifier, alignment, contentScale, alpha, colorFilter, size)
}
