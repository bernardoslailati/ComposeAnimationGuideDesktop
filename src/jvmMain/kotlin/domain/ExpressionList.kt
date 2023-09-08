package domain

import domain.Answer.*
import domain.ExpressionType.ANSWER
import domain.ExpressionType.QUESTION
import java.net.URI

// ---------------- QUESTIONS -----------------------

val IS_MY_ANIMATION_MORE_LIKE_ART = Expression(
    text = "Is my animation more like art, consisting of many visual elements? (i.e. SVGs or images)", type = QUESTION
) { answer ->
    when (answer) {
        NO -> DOES_IT_NEED_TO_REPEAT_FOREVER
        YES -> DOES_IT_HAVE_SIMPLE_SVG
    }
}

val DOES_IT_HAVE_SIMPLE_SVG = Expression(
    text = "Does it have simple SVGs? (i.e. an icon with micro-animations)", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATION_FRAMEWORK
        YES -> ANIMATED_VECTOR_DRAWABLE
    }
}

val DOES_IT_NEED_TO_REPEAT_FOREVER = Expression(
    text = "Does it need to repeat forever?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> DO_YOU_NEED_TO_ANIMATE_ASPECTS
        YES -> REMEMBER_INFINITE_TRANSITION
    }
}

val DO_YOU_NEED_TO_ANIMATE_ASPECTS = Expression(
    text = "Do you need to animate aspects (parameters) of a single composable?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> CHANGING_BETWEEN_MULTIPLE_COMPOSABLES
        YES -> DO_YOU_NEED_TO_ANIMATE_MULTIPLE_PARAMETERS
    }
}

val CHANGING_BETWEEN_MULTIPLE_COMPOSABLES = Expression(
    text = "Changing between multiple composables that have different content?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> LIST_ITEM_ANIMATIONS
        YES -> WITH_NAVIGATION_COMPOSE
    }
}

val WITH_NAVIGATION_COMPOSE = Expression(
    text = "With navigation-compose?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATED_CONTENT_OR_CROSS_FADE_OR_PAGER
        YES -> ENTER_AND_EXIT_TRANSITION
    }
}

val LIST_ITEM_ANIMATIONS = Expression(
    text = "List item animations?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> GESTURE_DRIVEN_ANIMATION
        YES -> ANIMATE_ITEM_PLACEMENT
    }
}

val GESTURE_DRIVEN_ANIMATION = Expression(
    text = "Gesture driven animation? Your animation is the only source of truth?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ONE_SHOT_ANIMATION_WITHOUT_STATE
        YES -> ANIMATABLE_WITH_ANIMATE_OR_SNAP_TO
    }
}

val ONE_SHOT_ANIMATION_WITHOUT_STATE = Expression(
    text = "One shot animation without state management?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANSWER_NOT_HERE
        YES -> ANIMATION_STATE_OR_ANIMATE
    }
}

val DO_YOU_NEED_TO_ANIMATE_MULTIPLE_PARAMETERS = Expression(
    text = "Do you need to animate multiple parameters?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATING_APPEAREANCE_DISAPPEARANCE
        YES -> BASED_ON_THE_SAME_SHARED_STATE
    }
}

val ANIMATING_APPEAREANCE_DISAPPEARANCE = Expression(
    text = "Animating appearance/disappearance?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATING_SIZE
        YES -> ANIMATED_VISIBILITY_OR_ANIMATE_FLOAT_AS_STATE
    }
}

val BASED_ON_THE_SAME_SHARED_STATE = Expression(
    text = "Based on the same shared state, all at the same time?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATABLE_WITH_ANIMATE_TO
        YES -> UPDATE_TRANSITION
    }
}

val ANIMATING_SIZE = Expression(
    text = "Animating size?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATING_TEXT
        YES -> ANIMATE_CONTENT_SIZE
    }
}

val ANIMATING_TEXT = Expression(
    text = "Animating text?", type = QUESTION
) { answer ->
    when (answer) {
        NO -> ANIMATE_AS_STATE
        YES -> TEXT_MOTION_ANIMATED
    }
}

// ---------------- ANSWERS -----------------------

val ANIMATED_VECTOR_DRAWABLE = Expression(
    text = "AnimatedVectorDrawable", type = ANSWER,
    links = listOf(
        Link(
            description = "AnimatedVectorDrawable",
            uri = URI("https://developer.android.com/jetpack/compose/animation/avd")
        )
    )
) { _ -> null }

val ANIMATION_FRAMEWORK = Expression(
    text = "Animation framework (i.e. Lottie)", type = ANSWER,
    links = listOf(
        Link(
            description = "Lottie",
            uri = URI("https://airbnb.design/lottie/")
        )
    )
) { _ -> null }

val REMEMBER_INFINITE_TRANSITION = Expression(
    text = "rememberInfiniteTransition", type = ANSWER,
    links = listOf(
        Link(
            description = "rememberInfiniteTransition",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#rememberinfinitetransition")
        )
    )
) { _ -> null }

val UPDATE_TRANSITION = Expression(
    text = "updateTransition() with AnimatedVisibility, animateFloat, animateInt, etc.", type = ANSWER,
    links = listOf(
        Link(
            description = "updateTransition",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#updatetransition")
        )
    )
) { _ -> null }

val ANIMATABLE_WITH_ANIMATE_TO = Expression(
    text = "Animatable with animateTo() called in with different timings (using suspend functions)", type = ANSWER,
    links = listOf(
        Link(
            description = "Animatable",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#animatable")
        )
    )
) { _ -> null }

val ANIMATED_VISIBILITY_OR_ANIMATE_FLOAT_AS_STATE = Expression(
    text = "AnimatedVisibility or animateFloatAsState with Modifier.alpha()", type = ANSWER,
    links = listOf(
        Link(
            description = "AnimatedVisibility",
            uri = URI("https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedvisibility")
        ),
        Link(
            description = "animateFloatAsState",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#animate-as-state")
        )
    )
) { _ -> null }

val ANIMATE_CONTENT_SIZE = Expression(
    text = "animateContentSize()", type = ANSWER,
    links = listOf(
        Link(
            description = "Modifier.animateContentSize",
            uri = URI("https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedContentSize")
        )
    )
) { _ -> null }

val TEXT_MOTION_ANIMATED = Expression(
    text = "TextMotion.Animated with animate*AsState", type = ANSWER,
    links = listOf(
        Link(
            description = "TextMotion.Animated",
            uri = URI("https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/TextMotion#Animated()")
        ),
        Link(
            description = "animate*AsState",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#animate-as-state")
        )
    )
) { _ -> null }

val ANIMATE_AS_STATE = Expression(
    text = "animate*AsState", type = ANSWER,
    links = listOf(
        Link(
            description = "animate*AsState",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#animate-as-state")
        )
    )
) { _ -> null }

val ENTER_AND_EXIT_TRANSITION = Expression(
    text = "enterTransition and exitTransition", type = ANSWER,
    links = listOf(
        Link(
            description = "composable() with enterTransition and exitTransition set",
            uri = URI("https://developer.android.com/reference/kotlin/androidx/navigation/compose/package-summary#(androidx.navigation.NavGraphBuilder).composable(kotlin.String,kotlin.collections.List,kotlin.collections.List,kotlin.Function1,kotlin.Function1,kotlin.Function1,kotlin.Function1,kotlin.Function2)")
        )
    )
) { _ -> null }

val ANIMATED_CONTENT_OR_CROSS_FADE_OR_PAGER = Expression(
    text = "AnimatedContent or CrossFade or Pager", type = ANSWER,
    links = listOf(
        Link(
            description = "AnimatedContent",
            uri = URI("https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent")
        ),Link(
            description = "Crossfade",
            uri = URI("https://developer.android.com/jetpack/compose/animation/composables-modifiers#crossfade")
        ),Link(
            description = "Pager",
            uri = URI("https://developer.android.com/jetpack/compose/layouts/pager")
        ),
    )
) { _ -> null }

val ANIMATE_ITEM_PLACEMENT = Expression(
    text = "animateItemPlacement() (reorder and delete coming soon)", type = ANSWER,
    links = listOf(
        Link(
            description = "animateItemPlacement()",
            uri = URI("https://developer.android.com/jetpack/compose/lists?hl=pt-br#item-animations")
        )
    )
) { _ -> null }

val ANIMATABLE_WITH_ANIMATE_OR_SNAP_TO = Expression(
    text = "Animatable with animateTo / snapTo", type = ANSWER,
    links = listOf(
        Link(
            description = "Animatable with animateTo and snapTo",
            uri = URI("https://developer.android.com/jetpack/compose/animation/value-based#animatable")
        )
    )
) { _ -> null }

val ANIMATION_STATE_OR_ANIMATE = Expression(
    text = "AnimationState or animate()", type = ANSWER,
    links = listOf(
        Link(
            description = "AnimationState",
            uri = URI("https://developer.android.com/reference/kotlin/androidx/compose/animation/core/AnimationState")
        ),
        Link(
            description = "animate",
            uri = URI("https://developer.android.com/reference/kotlin/androidx/compose/animation/core/package-summary#animate(kotlin.Float,kotlin.Float,kotlin.Float,androidx.compose.animation.core.AnimationSpec,kotlin.Function2)")
        )
    )
) { _ -> null }

val ANSWER_NOT_HERE = Expression(
    text = "Answer not here? File a request: goo.gle/compose-feedback.", type = ANSWER,
    links = listOf(
        Link(
            description = "Compose Feedback",
            uri = URI("https://goo.gle/compose-feedback")
        )
    )
) { _ -> null }