@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.CenterRow
import cn.net.compose.ui.components.SvgImage
import cn.net.compose.ui.components.SvgImageButton
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import org.jetbrains.compose.splitpane.SplitPaneScope


fun SplitPaneScope.Menubar(currentAppearance: AppAppearance) = first(currentAppearance.menuBarHeight) {
  CenterRow(Modifier.fillMaxSize()) {
    Box(Modifier.weight(1f))
    SvgImageButton(currentAppearance.iconTranslate) {

    }
    SvgImageButton(currentAppearance.iconSideController) {

    }
  }
}