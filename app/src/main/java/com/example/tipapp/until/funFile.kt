package com.example.tipapp.until

fun calculateTipAmount(tippersentage: Int, totalbill: Double): Double {
    if (totalbill>1 && totalbill.toString().toString().isNotEmpty()) return  ((totalbill*tippersentage.toDouble())/100)
    else return 0.0


}

fun perPersonBill(totalBill:Double,tipPersentage:Int,splitby:Int):Double{

    val finalBillPerPerson = calculateTipAmount(tipPersentage,totalBill)+totalBill
    return  (finalBillPerPerson/splitby)

}