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
                if(firstDown.pressed) {
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