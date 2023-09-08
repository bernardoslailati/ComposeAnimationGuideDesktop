import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import component.AnswerButton
import domain.Answer.NO
import domain.Answer.YES
import domain.IS_MY_ANIMATION_MORE_LIKE_ART
import domain.utils.isNull
import domain.utils.isQuestion
import domain.utils.openInBrowser
import theme.BLUE_ANSWER
import theme.GREEN_QUESTION
import theme.WhatIsAnimationComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun App() {
    var isStarted by remember { mutableStateOf(false) }
    var currentExpression = remember { mutableStateOf(IS_MY_ANIMATION_MORE_LIKE_ART ?: null) }

    val expressionCardInteractionSource = remember { MutableInteractionSource() }
    val yesInteractionSource = remember { MutableInteractionSource() }
    val noInteractionSource = remember { MutableInteractionSource() }

    val isExpressionCardHovered by expressionCardInteractionSource.collectIsHoveredAsState()
    val isYesHovered by yesInteractionSource.collectIsHoveredAsState()
    val isNoHovered by noInteractionSource.collectIsHoveredAsState()

    val expressionCardScaleSize by animateFloatAsState(if (isExpressionCardHovered) 1.1f else 1f)
    val yesScaleSize by animateFloatAsState(if (isYesHovered) 1.1f else 1f)
    val noScaleSize by animateFloatAsState(if (isNoHovered) 1.1f else 1f)

    WhatIsAnimationComposeTheme {
        val expression = currentExpression.value

        Column(modifier = Modifier.fillMaxSize().padding(48.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(0.2f),
                painter = painterResource("drawable/ic_android_developers.svg"),
                contentDescription = null
            )
            Text(
                "What's the correct Compose animation and when to use?", fontSize = 24.sp, fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Answer all the questions below and find out!", fontWeight = FontWeight.ExtraLight)
            Spacer(modifier = Modifier.height(36.dp))
            if (isStarted) {
                if (!currentExpression.isNull()) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth().scale(expressionCardScaleSize)
                                .hoverable(interactionSource = expressionCardInteractionSource),
                            content = {
                                expression?.text?.let {
                                    Text(
                                        modifier = Modifier.padding(24.dp),
                                        text = it,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            },
                            backgroundColor = if (expression?.isQuestion() == true)
                                GREEN_QUESTION
                            else
                                BLUE_ANSWER,
                            shape = RoundedCornerShape(16.dp),
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        if (expression?.isQuestion() == true) {
                            AnswerButton(
                                modifier = Modifier.scale(yesScaleSize)
                                    .hoverable(interactionSource = yesInteractionSource),
                                currentExpression = currentExpression,
                                answer = YES
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            AnswerButton(
                                modifier = Modifier.scale(noScaleSize)
                                    .hoverable(interactionSource = noInteractionSource),
                                currentExpression = currentExpression,
                                answer = NO
                            )
                        } else {
                            if (currentExpression.value?.links?.isNull() == false) {
                                currentExpression.value?.links?.let { links ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        links.forEach { link ->
                                            Chip(
                                                modifier = Modifier.padding(8.dp),
                                                onClick = {
                                                    openInBrowser(link.uri)
                                                },
                                                leadingIcon = {
                                                    Icon(
                                                        modifier = Modifier.padding(4.dp),
                                                        painter = painterResource(link.iconPath),
                                                        contentDescription = null
                                                    )
                                                }
                                            ) {
                                                Text(text = link.description, fontWeight = FontWeight.SemiBold)
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                border = BorderStroke(2.dp, Color.DarkGray),
                                onClick = {
                                    isStarted = false
                                }) {
                                Text("RESTART", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth().height(54.dp).clip(RoundedCornerShape(36.dp)),
                    onClick = {
                        isStarted = true
                        currentExpression.value = IS_MY_ANIMATION_MORE_LIKE_ART
                    }
                ) {
                    Text("START", fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose Animation Guide",
        icon = painterResource("drawable/ic_android.svg")
    ) {
        App()
    }
}
