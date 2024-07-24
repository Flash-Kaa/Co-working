package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GTeraText

@Composable
fun CoworkingSchemeWithTitle(coworking: MutableState<Workspace>) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        GTeraText(
            text = stringResource(R.string.coworking_scheme),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))


        CoworkingScheme(coworking.value.objects)
    }
}