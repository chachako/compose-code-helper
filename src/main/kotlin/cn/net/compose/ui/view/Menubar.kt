@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.CenterRow
import cn.net.compose.ui.components.SearchBar
import cn.net.compose.ui.components.SvgImage
import cn.net.compose.ui.components.SvgImageButton
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import cn.net.compose.ui.utils.windowDraggable
import org.jetbrains.compose.splitpane.SplitPaneScope


fun SplitPaneScope.Menubar(currentAppearance: AppAppearance, window: ComposeWindow) = first(currentAppearance.menuBarHeight) {
  CenterRow(Modifier.fillMaxSize().background(currentAppearance.surfaceColor).windowDraggable(window)) {
    Spacer(Modifier.width(15.dp))
    SearchBar()
    Box(Modifier.weight(1f))
    SvgImageButton(currentAppearance.iconTranslate) {

    }
    SvgImageButton(currentAppearance.iconSideController) {

    }
  }
}
