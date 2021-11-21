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

import androidx.compose.ui.graphics.Color
import cn.net.compose.model.ColorPair

/**
 * @author 凛 (https://github.com/RinOrz)
 */
class AppDarkAppearance : AppAppearance() {
  override val isLight: Boolean = false

  override val logo: String = "logoDark"

  override val contentColor: Color = Color.White
  override val surfaceColor: Color = Color(0xFF24272C)
  override val backgroundColor: Color = Color(0xFF1E2125)

  override val titleBarColors: ColorPair = backgroundColor to Color(0xFF22252A)

  override val searchBarColor: Color = Color(0xFF33363D)
}
