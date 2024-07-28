package com.na.coworking.ui.mainpage.elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.actions.MainPageAction
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText
import com.na.coworking.ui.global.RedButton

@Composable
internal fun ListOfCoworking(
    state: State<List<Workspace>>,
    getAction: (MainPageAction) -> (() -> Unit)
) {
    if (state.value.isNotEmpty()) {
        Column {
            Title()
            Spacer(modifier = Modifier.height(20.dp))
            List(state, getAction)
        }
    }
}

@Composable
private fun Title() {
    GTeraText(
        text = stringResource(id = R.string.coworkings),
        fontSize = 16.sp,
        modifier = Modifier.padding(start = 30.dp)
    )
}

@Composable
private fun List(
    state: State<List<Workspace>>,
    getAction: (MainPageAction) -> (() -> Unit)
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        item { Spacer(modifier = Modifier.width(30.dp)) }

        items(state.value) { coworking ->
            CoworkingCard(coworking, getAction)
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}

@Composable
private fun CoworkingCard(
    coworking: Workspace,
    getAction: (MainPageAction) -> () -> Unit
) {
    Box {
        CoworkingInfo(coworking, getAction)
        Rating(coworking)
    }
}

@Composable
private fun Rating(coworking: Workspace) {
    Box(modifier = Modifier.padding(20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.red),
                    shape = RoundedCornerShape(35.dp)
                )
                .padding(vertical = 5.dp, horizontal = 8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_star_24),
                contentDescription = "rating is ${coworking.rating.toString()}",
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(15.dp)
            )
            GExaText(
                text = coworking.rating.toString(),
                fontSize = 13.sp,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
private fun CoworkingInfo(
    coworking: Workspace,
    getAction: (MainPageAction) -> (() -> Unit)
) {
    Column(
        modifier = Modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .width(300.dp)
            .height(450.dp)
    ) {
        ImagePager(coworking)

        Location(coworking, Modifier.padding(horizontal = 30.dp, vertical = 10.dp))

        Description(coworking, Modifier.padding(horizontal = 30.dp))

        RedButton(
            text = stringResource(R.string.booking),
            onClick = getAction(MainPageAction.ToCoworking(coworking.id)),
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
            fontSize = 13.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagePager(coworking: Workspace) {
    val state = rememberPagerState { coworking.images.size }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .height(210.dp)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = state,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .height(210.dp)
                .fillMaxWidth(),
            pageContent = { ImageDrawer(R.drawable.background_main) }
        )

        PageIndicator(state)
    }
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

@Composable
private fun ImageDrawer(imageId: Int) {
    Image(
        bitmap = ImageBitmap.imageResource(id = imageId),
        contentScale = ContentScale.None,
        modifier = Modifier.fillMaxSize(),
        contentDescription = null
    )
}

@Composable
private fun Description(
    coworking: Workspace,
    modifier: Modifier = Modifier
) {
    GExaText(
        text = coworking.description,
        fontSize = 13.sp,
        color = colorResource(id = R.color.soft_gray),
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Justify
    )
}

@Composable
private fun Location(
    coworking: Workspace,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Institute(coworking)
            Address(coworking)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Name(coworking)
    }
}

@Composable
private fun Name(coworking: Workspace) {
    GExaText(
        text = coworking.name,
        fontSize = 16.sp,
        color = colorResource(id = R.color.red)
    )
}

@Composable
private fun Institute(coworking: Workspace) {
    GExaText(
        text = coworking.institute,
        fontSize = 16.sp
    )
}

@Composable
private fun Address(coworking: Workspace) {
    Row {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_location_pin_24),
            tint = colorResource(id = R.color.soft_gray),
            contentDescription = null
        )

        GExaText(
            text = coworking.address,
            fontSize = 13.sp,
            color = colorResource(id = R.color.soft_gray),
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}