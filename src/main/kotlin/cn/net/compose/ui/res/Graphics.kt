package cn.net.compose.ui.res

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
fun painterGraphicsRes(resourcePath: String): Painter = painterResource("graphics/$resourcePath")

@Composable
fun painterGraphicsSvg(svgPath: String): Painter = painterGraphicsRes("$svgPath.svg")