package com.na.coworking.ui.mainpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.na.coworking.actions.MainPageAction
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.TopAppBar
import com.na.coworking.ui.mainpage.elements.DownPanel
import com.na.coworking.ui.mainpage.elements.ListOfCoworking
import com.na.coworking.ui.mainpage.elements.MainImage
import kotlinx.coroutines.flow.flow

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

@Preview(showBackground = true)
@Composable
private fun PreviewMainPageUI() {
    val list = listOf(
        Workspace(
            id = 1234,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 4f,
            images = listOf(Workspace.Image(12, ""), Workspace.Image(123, ""))
        ),
        Workspace(
            id = 1234,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 3.2f,
            images = listOf(
                Workspace.Image(12, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, "")
            )
        ),
        Workspace(
            id = 1234,
            name = "Радиоточка",
            description = "– это удобные столы, мягкие диваны, много подушек. Это первый коворкинг, в котором расположена точка питания. Здесь студенты могут не только подготовиться к учебным занятиям, но и вкусно перекусить, пообедать. ",
            address = "Ул. Мира, 32",
            institute = "ИРИТ-РТФ",
            rating = 3.2f,
            images = listOf(
                Workspace.Image(12, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, ""),
                Workspace.Image(123, "")
            )
        )
    )

    val sizeBarIsOpen = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { TopAppBar(sizeBarIsOpen, { {} }) },
        modifier = Modifier.fillMaxSize(),
    ) {
        val state = flow { emit(list) }.collectAsState(initial = emptyList())

        MainPageUI(
            padding = it,
            state = state,
            getAction = { {} }
        )
    }
}