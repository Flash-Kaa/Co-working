package com.na.coworking.ui.coworking

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.na.coworking.actions.CoworkingEvent
import com.na.coworking.appComponent
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject

@Composable
fun DrawerCoworkingUI(
    id: Int,
    paddingValues: PaddingValues,
) {
    val viewModel: CoworkingVM = viewModel(
        factory = LocalContext.current.appComponent
            .provideTokenUseCasesComponent()
            .provideUserUseCasesComponent()
            .provideBookingsUseCase()
            .provideCoworkingUseCaseComponent()
            .provideCoworkingViewModelComponent()
            .provideFactoryWrapper()
            .Factory(id)
    )

    DrawerCoworkingUI(
        paddingValues = paddingValues,
        coworking = viewModel.coworking,
        timesRangesToBooking = viewModel.timesRange,
        daysToBooking = viewModel.days,
        getTemplates = viewModel::getTemplates,
        getTimes = viewModel::getTimes,
        getEvent = viewModel::getEvent
    )
}

@Composable
private fun DrawerCoworkingUI(
    coworking: MutableState<Workspace>,
    paddingValues: PaddingValues,
    timesRangesToBooking: List<String>,
    daysToBooking: List<String>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
    getEvent: (CoworkingEvent) -> Unit
) {
    CoworkingUI(
        coworking = coworking,
        paddingValues = paddingValues,
        timesRangesToBooking = timesRangesToBooking,
        daysToBooking = daysToBooking,
        getTemplates = getTemplates,
        getTimes = getTimes,
        getEvent = getEvent
    )
}