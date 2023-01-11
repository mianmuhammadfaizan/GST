package com.example.tipapp

import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.modifier.modifierLocalConsumer


import com.example.tipapp.Component.Inputfield
import com.example.tipapp.Component.roundedIconButton
import com.example.tipapp.ui.theme.TipAppTheme
import com.example.tipapp.until.calculateTipAmount
import com.example.tipapp.until.perPersonBill
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipApp {



                allContent()

            }

        }
    }
}

@Composable
fun TipApp(content:@Composable ()->Unit){


    TipAppTheme {

        Surface(

            color = MaterialTheme.colors.background
        ) {

            Column() {
                content()
            }


        }
    }
}

@Preview
@Composable
fun perHeadSection(totalPerPerson:Double=155.0){

    Surface(modifier = Modifier
        .width(320.dp)
        .height(150.dp)
        .padding(start = 20.dp, top = 30.dp, bottom = 10.dp, end = 5.dp), elevation = 10.dp, border = BorderStroke(1.dp,Color(0xFF80cbc4)),color=Color(0xFFb2dfdb), shape = RoundedCornerShape(20.dp)
    ) {

        Column(modifier = Modifier.padding(top=10.dp, bottom = 10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                  var total="%.2f".format(totalPerPerson)

                    Text(text = "Total Per Person", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "$ "+"$total",fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

}



}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun allContent(){


    billForm{   billAmount->
        Log.d("Hello!!!   ", "${billAmount.toInt()*100}")
    }





}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun billForm(modifier: Modifier=Modifier, onValChange:(String)->Unit){




    var totalbillState= remember {
        mutableStateOf("")
    }
    var keyporsState = remember(totalbillState.value) {
        totalbillState.value.trim().isNotEmpty()
    }
    var keyboardComposable=LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }


    val numberOfPersons = remember {
        mutableStateOf(1)
    }
//
//    val tipPersentageValue = remember {
//        mutableStateOf(0)
//    }

    val finalBill= remember {
        mutableStateOf(0.0)
    }

    var totalTipAmount= remember {
        mutableStateOf(0.0)
    }

    val tipPersentage=(sliderPositionState.value*100).toInt()

     perHeadSection(totalPerPerson = finalBill.value)


    Surface(modifier = Modifier
        .fillMaxWidth()

        .padding(10.dp),elevation = 10.dp, shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp,Color(0xFF80cbc4))) {

        Column(modifier = Modifier.padding(6.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

            Inputfield(

                valueState = totalbillState,
                lableID ="Enter Bill",
                enable = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if(!keyporsState) return@KeyboardActions
                    onValChange(totalbillState.value)
                    keyboardComposable?.hide()
                    finalBill.value= perPersonBill(totalbillState.value.toDouble(),tipPersentage, splitby =numberOfPersons.value )


                })


              if(keyporsState){

                    Row(modifier=Modifier.padding(5.dp), horizontalArrangement = Arrangement.Start) {
                        Text(text = "Split",Modifier.align(alignment =CenterVertically))
                        Spacer(modifier = Modifier.width(130.dp))
                      
                        Row(modifier=Modifier.padding(horizontal = 3.dp), horizontalArrangement = Arrangement.End) {


                                roundedIconButton(imageVector = Icons.Default.Add, onClick = {
                                    numberOfPersons.value+=1
                                    finalBill.value= perPersonBill(totalbillState.value.toDouble(),tipPersentage, splitby =numberOfPersons.value )

                                })
                                Text(text = "${numberOfPersons.value}", modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 9.dp, end = 9.dp),)


                            roundedIconButton(imageVector = Icons.Default.Remove, onClick = {

                              if (numberOfPersons.value>1) numberOfPersons.value-=1 else numberOfPersons.value=1

                                finalBill.value= perPersonBill(totalbillState.value.toDouble(),tipPersentage, splitby =numberOfPersons.value )




                            })



                        }
                        
                        

                    }

            
                    Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {
                        
                        Text(text = "Tip", modifier = Modifier
                            .padding(start = 4.dp)
                            .align(alignment = CenterVertically))


                                Spacer(modifier = Modifier.width(180.dp))
                        Text(text =  "$ "+"${totalTipAmount.value}", modifier = Modifier
                            .padding(start = 4.dp)
                            .align(alignment = CenterVertically))
                    }
                                    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
                                        Spacer(modifier = Modifier.heightIn(10.dp))
                                         //   val totalpersentage :Int=Math.round((sliderPositionState.value*100)*10)/10
                                            Text(text = "$tipPersentage"+" %")


                                        Spacer(modifier = Modifier.heightIn(10.dp))

                                        Slider(
                                            value = sliderPositionState.value,
                                            onValueChange = { newVal ->
                                                sliderPositionState.value = newVal

                                            totalTipAmount.value= calculateTipAmount(tipPersentage,totalbillState.value.toDouble())
                                             finalBill.value= perPersonBill(totalbillState.value.toDouble(),tipPersentage, splitby =numberOfPersons.value )

                                            },
                                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                            steps = 10,
                                        )




                                      }

            

            
               


               }
            else{
                Box() {

                }
            }




        }


    }


}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipAppTheme {

        TipApp {

        }

    }
}