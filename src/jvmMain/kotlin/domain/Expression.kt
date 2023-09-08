package domain

data class Expression(
    val text: String,
    val type: ExpressionType,
    val links: List<Link>? = null,
    val previous: Expression? = null,
    val next: (answer: Answer) -> Expression?,
)