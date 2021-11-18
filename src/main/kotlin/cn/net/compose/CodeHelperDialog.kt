package cn.net.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.mouseClickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.SearchBar
import cn.net.compose.ui.theme.WidgetTheme
import cn.net.compose.utils.windowDraggable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.event.WindowEvent
import java.awt.event.WindowFocusListener
import java.awt.geom.RoundRectangle2D
import javax.swing.*
import javax.swing.Box
import javax.swing.border.Border
import javax.swing.border.EmptyBorder

// 创建一个 dialog，去除自带的按钮，顶部栏，以及自带的 padding

class CodeHelperDialog(project: Project?) : DialogWrapper(
    project, null, true, IdeModalityType.MODELESS, false
) {

    private val windowWidth = 1024
    private val windowHeight = 768

    init {
        window.addWindowFocusListener( // 创建焦点的监听，当点击窗口外的地方，关闭 dialog
            object : WindowFocusListener {
                override fun windowLostFocus(e: WindowEvent?) {
                    close(CLOSE_EXIT_CODE)
                }

                override fun windowGainedFocus(e: WindowEvent?) {

                }
            }
        )
        setUndecorated(true)
        window.shape = RoundRectangle2D.Double(0.0, 0.0, windowWidth.toDouble(), windowHeight.toDouble(), 30.0, 30.0)
        init()
    }

    override fun createContentPaneBorder(): Border {
        return EmptyBorder(0, 0, 0, 0) // 去除 padding
    }

    override fun createActions(): Array<Action> {
        return arrayOf() // 去除按钮
    }

    @OptIn(ExperimentalComposeUiApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
    override fun createCenterPanel(): JComponent {
        return ComposePanel().apply {
            setBounds(0, 0, windowWidth, windowHeight)
            setContent {
                WidgetTheme(darkTheme = true) {
                    val focusRequester = FocusRequester()
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 10.dp,
                        modifier = Modifier
                            .onKeyEvent {
                                if(it.key == Key.Escape && it.type == KeyEventType.KeyDown) {
                                    close(CLOSE_EXIT_CODE)
                                }
                                true
                            }
                            .focusRequester(focusRequester)
                            .focusable()
                            .windowDraggable(window)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            SearchBar()
                        }
                    }
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                        requestFocusInWindow()
                    }
                }
            }
        }
    }
}
