package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePager(
    coworking: MutableState<Workspace>
) {
    val state = rememberPagerState { coworking.value.images.size }

    Box(
        modifier = Modifier
            .height(230.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = state,
            modifier = Modifier
                .height(230.dp)
                .fillMaxWidth(),
            pageContent = { /*TODO: image from coworking*/ImageDrawer(R.drawable.background_main) }
        )

        PageIndicator(state)
    }
}

@Composable
private fun ImageDrawer(imageId: Int) {
    Image(
        bitmap = ImageBitmap.imageResource(id = imageId),
        contentScale = ContentScale.Inside,
        modifier = Modifier.fillMaxSize(),
        contentDescription = null
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PageIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(9.dp)
                    .clip(CircleShape)
                    .background(
                        if (pagerState.currentPage == index)
                            colorResource(id = R.color.red)
                        else colorResource(id = R.color.light_gray)
                    )
            )

            if (index != pagerState.pageCount) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}