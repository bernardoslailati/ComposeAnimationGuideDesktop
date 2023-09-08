package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.Answer
import domain.Answer.NO
import domain.Expression

@Composable
fun AnswerButton(modifier: Modifier = Modifier, currentExpression: MutableState<Expression?>, answer: Answer) {
    OutlinedButton(
        modifier = modifier,
        border = BorderStroke(width = 2.dp, color = answer.color),
        onClick = {
            currentExpression.value = currentExpression.value?.next?.let { it(answer) }
        }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (answer == NO)
                Icon(
                    painter = painterResource("drawable/ic_thumb_down.svg"),
                    tint = answer.color,
                    contentDescription = null
                )
            else
                Icon(
                    imageVector = Icons.Outlined.ThumbUp,
                    tint = answer.color,
                    contentDescription = null
                )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = answer.name,
                fontWeight = FontWeight.SemiBold,
                color = answer.color,
                textAlign = TextAlign.Center
            )
        }
    }
}