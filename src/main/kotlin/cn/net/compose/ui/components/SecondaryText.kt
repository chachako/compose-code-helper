package cn.net.compose.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.TextUnit

@Composable
fun SecondaryText(
    text: String,
    style: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = text, style = style, fontSize = fontSize)
    }
}
