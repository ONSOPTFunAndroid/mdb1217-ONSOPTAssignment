package com.example.myapplication

interface ItemTouchHelperListener {
    fun onItemMoved(from : Int, to : Int)
    fun onItemSwiped(position : Int)
}