package com.example.tipapp.Component

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


val ButtonSizeIncreas =Modifier.size(40.dp)

@Composable


fun roundedIconButton(
    modifier: Modifier=Modifier,
    imageVector: ImageVector,
    onClick:()->Unit,
    tint: Color=Color.Black.copy(alpha = 0.8f),
    backgroundcolor:Color=Color(0xFF80cbc4),
    elevation: Dp =5.dp,



){


        Card(modifier = Modifier
            .padding(all = 4.dp)
            .clickable { onClick.invoke() }
            .then(ButtonSizeIncreas),
            shape = CircleShape,
            backgroundColor  =backgroundcolor,
            elevation = elevation,




            ) {

            Icon(imageVector = imageVector, contentDescription ="Add or Remove", tint = tint )

        }






}