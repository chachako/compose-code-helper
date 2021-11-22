@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.net.compose.ui.components.CenterColumn
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import cn.net.compose.wand.HeightSpacer
import org.jetbrains.compose.splitpane.SplitPaneScope

/**
 * Layout overview:
 * ```
 * --------          -----------
 * |  []  |          |  []     |
 * |  []  |    ->    |  []     |
 * |  []  |          |  []     |
 * --------          -----------
 * ```
 *
 * @author 凛 (https://github.com/RinOrz)
 */
fun SplitPaneScope.Sidebar(currentAppearance: AppAppearance) = first(currentAppearance.sidebarMinMaxWidths.first) {
  CenterColumn(Modifier.fillMaxSize().background(currentAppearance.surfaceColor)) {
    HeightSpacer(20.dp)
    Logo(Modifier.padding(horizontal = 12.dp))
  }
}

@Composable
private fun Logo(modifier: Modifier) = Image(
  painter = painterGraphicsSvg(currentAppearance.logo),
  modifier = modifier.size(currentAppearance.logoSize),
  contentDescription = "App Logo",
)
