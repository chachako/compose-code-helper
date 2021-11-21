package cn.net.compose.wand

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun WidthSpacer(width: Dp, modifier: Modifier = Modifier) = Spacer(modifier.width(width))

@Composable
fun HeightSpacer(height: Dp, modifier: Modifier = Modifier) = Spacer(modifier.height(height))

@Composable
fun SquareSpacer(size: Dp, modifier: Modifier = Modifier) = Spacer(modifier.size(size))