package com.na.coworking.ui.account.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.ui.global.GExaText

@Composable
fun PagerTitle(
    page: MutableState<Page>
) {
    LazyRow(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.soft_white))
            .padding(horizontal = 30.dp)
    ) {
        items(Page.entries) { curPage -> PageWithTitle(curPage, page) }
    }
}

@Composable
private fun PageWithTitle(
    curPage: Page,
    page: MutableState<Page>
) {
    val colorId =
        if (curPage == page.value) R.color.soft_gray else R.color.soft_black

    Box(
        modifier = Modifier
            .line(curPage, page)
            .fillMaxHeight()
            .clickable(
                onClick = { page.value = curPage },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
    ) {
        PageTitle(curPage, colorId)
    }

    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
}

@Composable
private fun PageTitle(page: Page, colorId: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        GExaText(
            text = page.title(),
            fontSize = 16.sp,
            color = colorResource(id = colorId),
        )
    }
}

@Composable
private fun Modifier.line(page: Page, currentPage: MutableState<Page>): Modifier {
    val topDividerColor = colorResource(id = R.color.soft_black)

    return this.drawBehind {
        if (page == currentPage.value) {
            drawLine(
                color = topDividerColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}