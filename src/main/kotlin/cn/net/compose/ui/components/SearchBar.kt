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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    placeHolder: String = "Search keywords"
) {

    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        decorationBox = {
            CenterRow(
                modifier = Modifier.height(48.dp).width(250.dp).background(Color(0xFF33363D), shape = RoundedCornerShape(8.dp)),
            ) {
                Icon(Icons.Filled.Search, null, modifier = Modifier.padding(8.dp))
                if(!isFocused && text == "") {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = placeHolder,
                            fontSize = 14.sp
                        )
                    }
                }
                it()
            }
        },
        cursorBrush = SolidColor(Color.Gray),
        modifier = Modifier.onFocusChanged {
            isFocused = it.isFocused
        }
    )
}
