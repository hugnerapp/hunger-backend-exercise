package com.hunger.exercise.exception

enum class CustomExceptionType(var code:Int,var desc:String) {
    USER_INPUT_ERROR(400,"輸入數據有誤"),
    SYSTEM_ERROR(500,"系統異常"),
    OTHER_ERROR(999,"未知異常")
}