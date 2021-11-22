@file:Suppress("FunctionName")

package cn.net.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.net.compose.ui.components.CenterColumn
import cn.net.compose.ui.components.CenterRow
import cn.net.compose.ui.components.SecondaryText
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.AppAppearance
import cn.net.compose.ui.theme.currentAppearance
import cn.net.compose.wand.HeightSpacer
import cn.net.compose.wand.WidthSpacer
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
    Row(
      modifier = Modifier.fillMaxWidth().height(56.dp),
      verticalAlignment = Alignment.Bottom
    ) {
      Logo(Modifier.padding(horizontal = 12.dp))
      Column(
        modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
        horizontalAlignment = Alignment.End
      ) {
        SecondaryText(
          text = "Compose ver.",
          fontSize = 12.sp
        )
        SecondaryText(
          text = "1.0.0-beta01",
          fontSize = 12.sp
        )
      }
    }
  }
}

@Composable
private fun Logo(modifier: Modifier) = Image(
  painter = painterGraphicsSvg(currentAppearance.logo),
  modifier = modifier.size(currentAppearance.logoSize),
  contentDescription = "App Logo",
)
