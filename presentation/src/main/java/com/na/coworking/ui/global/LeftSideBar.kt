package com.na.coworking.ui.global

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.actions.GlobalAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val WIDTH: Float = 200f
private const val CLOSE_SENSITIVITY_COEF = 10f
private const val TOUCH_SENSITIVITY_COEF = 0.3f
private const val CLOSE_COEF = 0.6f


@Composable
fun LeftSideBar(
    isOpen: MutableState<Boolean>,
    getAction: (GlobalAction) -> () -> Unit
) {
    SideBarDrawer(isOpen = isOpen) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))

            LeftSizeTextButton(
                text = "Главный экран",
                onClick = {
                    getAction(GlobalAction.ToMainPage).invoke()
                    isOpen.value = false
                }
            )
            LeftSizeTextButton(
                text = stringResource(R.string.personal_account),
                onClick = {
                    getAction(GlobalAction.ToPersonAccount).invoke()
                    isOpen.value = false
                }
            )
            LeftSizeTextButton(
                text = stringResource(R.string.coworkings),
                onClick = {
                    getAction(GlobalAction.ToListOfCoworking).invoke()
                    isOpen.value = false
                }
            )
            LeftSizeTextButton(
                text = stringResource(R.string.contacts),
                onClick = {
                    getAction(GlobalAction.ToContacts).invoke()
                    isOpen.value = false
                }
            )
        }
    }
}

@Composable
private fun LeftSizeTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        GExaText(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun SideBarDrawer(
    isOpen: MutableState<Boolean>,
    content: @Composable (BoxScope.() -> Unit)
) {
    if (isOpen.value) {
        val offsetX = remember { Animatable(0f) }
        Blackout(offsetX, isOpen)
        SideBar(offsetX, isOpen, content)
    }
}

@Composable
private fun Blackout(
    offsetX: Animatable<Float, AnimationVector1D>,
    isOpen: MutableState<Boolean>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { isOpen.value = false }
            .background(
                colorResource(id = R.color.soft_white).copy(
                    alpha = (WIDTH - offsetX.value * CLOSE_SENSITIVITY_COEF) / WIDTH * 0.7f
                )
            )
    )
}

@Composable
private fun SideBar(
    offsetX: Animatable<Float, AnimationVector1D>,
    isOpen: MutableState<Boolean>,
    content: @Composable (BoxScope.() -> Unit)
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(modifier = Modifier.leftSideBar(offsetX, isOpen))

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(WIDTH.dp)
                .offset(x = (offsetX.value * CLOSE_SENSITIVITY_COEF + 20).dp, y = 20.dp),
            contentAlignment = Alignment.TopStart,
            content = content
        )
    }
}

@Composable
private fun Modifier.leftSideBar(
    offsetX: Animatable<Float, AnimationVector1D>,
    isOpen: MutableState<Boolean>
) = this
    .shadow(
        elevation = 1.dp,
        clip = false,
        shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp)
    )
    .background(
        color = colorResource(id = R.color.white),
        shape = RoundedCornerShape(topStart = 40.dp, bottomStart = 40.dp),
    )
    .fillMaxHeight()
    .width((WIDTH - offsetX.value * CLOSE_SENSITIVITY_COEF).dp)
    .pointerInput(offsetX, isOpen)

@Composable
private fun Modifier.pointerInput(
    offsetX: Animatable<Float, AnimationVector1D>,
    isOpen: MutableState<Boolean>
): Modifier {
    val scope = rememberCoroutineScope()
    val onEnd: () -> Unit = {
        scope.launch {
            if (offsetX.value * CLOSE_SENSITIVITY_COEF > WIDTH * CLOSE_COEF) {
                offsetX.animateTo(WIDTH / CLOSE_SENSITIVITY_COEF)
                isOpen.value = false
                offsetX.animateTo(0f)
            }

            offsetX.animateTo(0f)
        }
    }

    return this.then(
        Modifier.pointerInput(Unit) {
            detectHorizontalDragGestures(
                onHorizontalDrag = { change, dragAmount ->
                    changeScope(scope, offsetX, dragAmount)
                    change.consume()
                },
                onDragEnd = onEnd,
                onDragCancel = onEnd
            )
        }
    )
}

private fun changeScope(
    scope: CoroutineScope,
    offsetX: Animatable<Float, AnimationVector1D>,
    dragAmount: Float
) {
    scope.launch {
        val resultOffsetX = offsetX.value + dragAmount * TOUCH_SENSITIVITY_COEF

        if (resultOffsetX > 0 && resultOffsetX * CLOSE_SENSITIVITY_COEF < WIDTH) {
            offsetX.animateTo(resultOffsetX)
        }
    }
}