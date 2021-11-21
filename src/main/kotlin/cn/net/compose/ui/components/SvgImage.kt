package cn.net.compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import cn.net.compose.ui.res.painterGraphicsSvg
import cn.net.compose.ui.theme.currentAppearance
import com.meowool.sweekt.firstCharTitlecase

@Composable
fun SvgImage(
  name: String,
  contentDescription: String? = name.firstCharTitlecase(),
  modifier: Modifier = Modifier,
  alignment: Alignment = Alignment.Center,
  contentScale: ContentScale = ContentScale.Fit,
  alpha: Float = DefaultAlpha,
  colorFilter: ColorFilter? = null,
  size: Dp = currentAppearance.iconSizeDefault
) = Image(
  painter = painterGraphicsSvg(name),
  contentDescription = contentDescription,
  modifier = modifier.size(size),
  alignment = alignment,
  contentScale = contentScale,
  alpha = alpha,
  colorFilter = colorFilter
)

@Composable
fun SvgImageButton(
  name: String,
  contentDescription: String? = name.firstCharTitlecase(),
  modifier: Modifier = Modifier,
  alignment: Alignment = Alignment.Center,
  contentScale: ContentScale = ContentScale.Fit,
  shape: Shape = CircleShape,
  alpha: Float = DefaultAlpha,
  colorFilter: ColorFilter? = null,
  size: Dp = currentAppearance.iconSizeDefault,
  contentPadding: PaddingValues = currentAppearance.iconButtonPaddingDefault,
  onClick: () -> Unit,
) = Button(
  onClick,
  elevation = null,
  contentPadding = contentPadding,
  shape = shape,
  colors = ButtonDefaults.buttonColors(Color.Transparent)
) {
  SvgImage(name, contentDescription, modifier, alignment, contentScale, alpha, colorFilter, size)
}