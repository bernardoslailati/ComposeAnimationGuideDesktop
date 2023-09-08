package domain.utils

import domain.Expression
import domain.ExpressionType

const val HOVERED_ITEM_SCALE = 1.1f
const val DEFAULT_ITEM_SCALE = 1f

const val START = "START"
const val RESTART = "RESTART"

fun Expression.isQuestion(): Boolean = this.type == ExpressionType.QUESTION

fun <T> T.isNull(): Boolean = this == null