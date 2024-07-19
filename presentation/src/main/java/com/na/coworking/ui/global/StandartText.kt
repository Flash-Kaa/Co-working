package com.na.coworking.ui.global

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.na.coworking.R

private val grtskExaFont = FontFamily(
    Font(R.font.grtskexa)
)

private val grtskTeraFont = FontFamily(
    Font(R.font.grtsktera)
)

@Composable
fun GExaText(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight(500),
    color: Color = colorResource(id = R.color.soft_black),
    lineHeight: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,

        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        minLines = minLines,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = modifier,
        fontFamily = grtskExaFont
    )
}

@Composable
fun GExaText(
    text: AnnotatedString,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight(400),
    color: Color = colorResource(id = R.color.soft_black),
    lineHeight: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,

        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = modifier,
        fontFamily = grtskExaFont
    )
}

@Composable
fun GTeraText(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight(400),
    color: Color = colorResource(id = R.color.soft_black),
    lineHeight: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,

        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        minLines = minLines,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = modifier,
        fontFamily = grtskTeraFont
    )
}

@Composable
fun GTeraText(
    text: AnnotatedString,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight(400),
    color: Color = colorResource(id = R.color.soft_black),
    lineHeight: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,

        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = modifier,
        fontFamily = grtskTeraFont
    )
}