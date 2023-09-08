package domain

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import theme.GREEN_YES
import theme.RED_NO

enum class Answer(val icon: @Composable () -> Unit, val color: Color) {
    NO(
        icon = {
            Icon(
                painter = painterResource("drawable/ic_thumb_down.svg"),
                tint = RED_NO,
                contentDescription = null
            )
        },
        color = RED_NO
    ),
    YES(
        icon = {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                tint = GREEN_YES,
                contentDescription = null
            )
        },
        color = GREEN_YES
    )
}