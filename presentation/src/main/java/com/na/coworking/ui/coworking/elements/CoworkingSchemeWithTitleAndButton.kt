package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GTeraText
import com.na.coworking.ui.global.RedButton

@Composable
fun CoworkingSchemeWithTitleAndButton(
    coworking: MutableState<Workspace>,
    bookingDialogIsOpen: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        GTeraText(
            text = stringResource(R.string.coworking_scheme),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(20.dp)
                )
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(30.dp)
        ) {
            CoworkingScheme(coworking.value.objects)

            Spacer(modifier = Modifier.height(50.dp))

            RedButton(
                text = stringResource(R.string.booking_place),
                onClick = { bookingDialogIsOpen.value = true })
        }
    }
}