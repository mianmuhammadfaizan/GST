package com.example.tipapp.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Inputfield(
    modifier: Modifier=Modifier.padding(20.dp),

     valueState:MutableState<String>,
    lableID:String,
    enable:Boolean,
    isSingleLine:Boolean,
    keybordType:KeyboardType=KeyboardType.Number,
    imeAction:ImeAction=ImeAction.Next,
    onAction:KeyboardActions=KeyboardActions.Default,
    



){
    OutlinedTextField(value = valueState.value, onValueChange ={valueState.value=it},
    label = { Text(text = lableID)},
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription ="Money Icon" ) }
    , singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = Color(0xFF212121) ),
        modifier=Modifier.padding(5.dp).fillMaxWidth(),
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType =keybordType, imeAction = imeAction),
        keyboardActions = onAction



        )


}
