package cn.net.compose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cn.net.compose.utils.Utils

@Composable
fun SearchBar() {
    val density = LocalDensity.current
    val focusRequester = FocusRequester()
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color(0xFF33363D)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        maxLines = 1,
        leadingIcon = {
            Icon(Icons.Filled.Search, null)
        },
        shape = RoundedCornerShape(9.dp),
        placeholder = {
            Text("Search keywords")
        }
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }

}
