package com.hunger.exercise.exception

class CustomException(var code:Int, override var message:String): RuntimeException() {


    constructor(exceptionType: CustomExceptionType): this(exceptionType.code, exceptionType.name)
    constructor(exceptionType: CustomExceptionType,message: String): this(exceptionType.code, message)

}