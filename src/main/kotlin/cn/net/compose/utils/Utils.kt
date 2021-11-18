package cn.net.compose.utils

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import java.awt.Cursor
import java.awt.Window
import java.awt.event.MouseEvent

object Utils {
    fun loadSvgPainter(name: String, density: Density): Painter =
        loadSvgPainter(this.javaClass.getResourceAsStream("/$name")!!, density)

}

@Composable
fun Modifier.windowDraggable(window: Window): Modifier {
    return pointerInput(Unit) {
        forEachGesture {
            awaitPointerEventScope {
                val down = awaitFirstDown()
                if(down.pressed) {
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
