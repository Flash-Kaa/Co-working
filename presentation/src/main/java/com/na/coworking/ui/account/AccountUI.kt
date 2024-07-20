package com.na.coworking.ui.account

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.na.coworking.actions.AccountAction
import com.na.coworking.domain.entities.User
import com.na.coworking.ui.account.elements.Page
import com.na.coworking.ui.account.elements.PagerTitle
import com.na.coworking.ui.account.elements.UserTitleBg
import com.na.coworking.ui.account.elements.pagerDrawer
import com.na.coworking.ui.global.TopAppBar

@Composable
fun AccountUI(
    user: User,
    page: MutableState<Page>,
    paddingValues: PaddingValues,
    getAction: (AccountAction) -> (() -> Unit)
) {
    LazyColumn(
        contentPadding = paddingValues
    ) {
        item { UserTitleBg(user, getAction) }
        item { PagerTitle(page) }
        pagerDrawer(user, page, getAction)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val user = User(
        id = 0,
        email = "flashkaa@forever.com",
        firstName = "egor",
        secondName = "unknown",
        accessLevel = 0
    )

    val page = remember {
        mutableStateOf(Page.Booking)
    }

    Scaffold(
        topBar = { TopAppBar({ {} }) },
        modifier = Modifier.fillMaxSize(),
    ) {
        AccountUI(user, page, it, { {} })
    }
}