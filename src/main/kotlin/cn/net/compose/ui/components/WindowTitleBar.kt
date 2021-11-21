package cn.net.compose.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import com.meowool.sweekt.isMacSystem
import com.meowool.sweekt.isWindowsSystem

sealed interface WindowTitleBar {
  val owner: ComposeWindow
  val height: Dp

  fun transparentBackground()
  fun hideTitleText()

  /**
   * Title bar common processing of any system.
   * Do not do any title bar processing for unknown systems at this time.
   *
   * @author 凛 (https://github.com/RinOrz)
   */
  open class Default(override val owner: ComposeWindow) : WindowTitleBar {
    // Use the title bar of the system
    override val height: Dp = Dp.Hairline
    override fun transparentBackground() = Unit
    override fun hideTitleText() {
      owner.title = " "
    }
  }

  /**
   * Title bar processing of macOS system.
   *
   * @author 凛 (https://github.com/RinOrz)
   */
  class MacOS(override val owner: ComposeWindow) : Default(owner) {
    override val height: Dp = 28.dp

    override fun transparentBackground() = owner.rootPane.run {
      putClientProperty(WindowFullContent, true)
      putClientProperty(WindowTransparentTitleBar, true)
    }

    override fun hideTitleText() {
      if (owner.rootPane.getClientProperty(WindowTitleVisible) == null) return super.hideTitleText()
      owner.rootPane.putClientProperty(WindowTitleVisible, false)
    }

    private companion object Keys {
      // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L126
      const val WindowFullContent = "apple.awt.fullWindowContent"
      // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L127
      const val WindowTransparentTitleBar = "apple.awt.transparentTitleBar"
      // https://github.com/openjdk/jdk/blob/master/src/java.desktop/macosx/classes/sun/lwawt/macosx/CPlatformWindow.java#L128
      const val WindowTitleVisible = "apple.awt.windowTitleVisible"
    }
  }

  class Windows(override val owner: ComposeWindow) : Default(owner)
}

@Composable
fun FrameWindowScope.getCurrentWindowTitleBar(): WindowTitleBar = remember {
  when {
    isMacSystem -> WindowTitleBar.MacOS(window)
    isWindowsSystem -> WindowTitleBar.Windows(window)
    else -> WindowTitleBar.Default(window)
  }
}