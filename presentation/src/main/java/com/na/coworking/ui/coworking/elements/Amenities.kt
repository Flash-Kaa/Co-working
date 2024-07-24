package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Amenity
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText

@Composable
fun Amenities(coworking: MutableState<Workspace>) {
    if (coworking.value.amenities.isNotEmpty()) {
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            GTeraText(
                text = stringResource(R.string.amenities_for_you),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(5.dp))

            coworking.value.amenities.forEach { amenity ->
                Spacer(modifier = Modifier.height(10.dp))
                Amenity(amenity)
            }
        }
    }
}

@Composable
private fun Amenity(amenity: Amenity) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO: image
        Box(
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(5.dp))
                .background(
                    color = colorResource(id = R.color.red),
                    shape = RoundedCornerShape(5.dp)
                )
                .size(30.dp)
        )

        GExaText(
            text = amenity.template.name,
            fontSize = 13.sp,
            color = colorResource(id = R.color.red),
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(7.dp)
                .fillMaxWidth()
        )
    }
}