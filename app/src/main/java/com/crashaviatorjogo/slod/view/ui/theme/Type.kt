package com.crashaviatorjogo.slod.view.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.crashaviatorjogo.slod.R

val Typography = Typography(
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 22.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 12.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 10.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.impact)),
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 12.sp
    ),
)