package com.na.coworking.ui.mainpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.na.coworking.actions.MainPageAction
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.mainpage.elements.DownPanel
import com.na.coworking.ui.mainpage.elements.ListOfCoworking
import com.na.coworking.ui.mainpage.elements.MainImage

@Composable
internal fun MainPageUI(
    padding: PaddingValues,
    state: State<List<Workspace>>,
    getAction: (MainPageAction) -> (() -> Unit)
) {
    LazyColumn(
        contentPadding = padding,
        modifier = Modifier.fillMaxSize()
    ) {
        item { MainImage() }
        item { Spacer(modifier = Modifier.height(50.dp)) }
        item { ListOfCoworking(state, getAction) }
        item { Spacer(modifier = Modifier.height(50.dp)) }
        item { DownPanel() }
    }
}