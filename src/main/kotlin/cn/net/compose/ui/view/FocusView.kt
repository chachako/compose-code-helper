@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import org.jetbrains.compose.splitpane.SplitPaneScope

fun SplitPaneScope.FocusView() = second {
  Surface {

  }
}