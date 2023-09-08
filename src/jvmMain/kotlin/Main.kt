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
import domain.utils.*
import theme.BLUE_ANSWER
import theme.GREEN_QUESTION
import theme.WhatIsAnimationComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun App() {
    var isStarted by remember { mutableStateOf(false) }
    val currentExpression = remember { mutableStateOf(IS_MY_ANIMATION_MORE_LIKE_ART ?: null) }

    val expressionCardInteractionSource = remember { MutableInteractionSource() }
    val yesInteractionSource = remember { MutableInteractionSource() }
    val noInteractionSource = remember { MutableInteractionSource() }

    val isExpressionCardHovered by expressionCardInteractionSource.collectIsHoveredAsState()
    val isYesHovered by yesInteractionSource.collectIsHoveredAsState()
    val isNoHovered by noInteractionSource.collectIsHoveredAsState()

    val expressionCardScaleSize by animateFloatAsState(
        if (isExpressionCardHovered)
            HOVERED_ITEM_SCALE
        else
            DEFAULT_ITEM_SCALE
    )
    val yesScaleSize by animateFloatAsState(
        if (isYesHovered)
            HOVERED_ITEM_SCALE
        else
            DEFAULT_ITEM_SCALE
    )
    val noScaleSize by animateFloatAsState(
        if (isNoHovered)
            HOVERED_ITEM_SCALE
        else
            DEFAULT_ITEM_SCALE
    )

    WhatIsAnimationComposeTheme {
        val expression = currentExpression.value

        Column(modifier = Modifier.fillMaxSize().padding(36.dp)) {
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
                                                Text(
                                                    modifier = Modifier.padding(8.dp),
                                                    text = link.description,
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.SemiBold
                                                )
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
                                    currentExpression.value = IS_MY_ANIMATION_MORE_LIKE_ART
                                }) {
                                Text(text = RESTART, fontWeight = FontWeight.Bold)
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
                    Text(text = START, fontWeight = FontWeight.Black)
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
