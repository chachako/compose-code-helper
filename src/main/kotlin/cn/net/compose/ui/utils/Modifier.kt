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
package cn.net.compose.ui.utils

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.input.pointer.pointerInput
import java.awt.Cursor
import java.awt.event.MouseEvent

@Composable
fun Modifier.windowDraggable(window: ComposeWindow): Modifier {
    return pointerInput(Unit) {
        forEachGesture {
            awaitPointerEventScope {
                val firstDown = awaitFirstDown()
                if (firstDown.pressed) {
                    val firstEvent = awaitPointerEvent()
                    val firstWindowPointer = firstEvent.mouseEvent?.point ?: return@awaitPointerEventScope

                    var start = false

                    while (true) {
                        val event = awaitPointerEvent()
                        if (!start) {
                            start = true
                        }

                        val displayPointer = event.mouseEvent?.locationOnScreen ?: break
                        window.setLocation(
                            (displayPointer.x - firstWindowPointer.x),
                            (displayPointer.y - firstWindowPointer.y),
                        )

                        when (event.mouseEvent?.id) {
                            null, MouseEvent.MOUSE_RELEASED -> {
                                window.cursor = Cursor.getDefaultCursor()
                                break
                            }
                        }
                    }
                }
            }
        }
    }
}
