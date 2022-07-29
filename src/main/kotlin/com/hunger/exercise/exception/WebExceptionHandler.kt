package com.hunger.exercise.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class WebExceptionHandler {


    @ExceptionHandler(CustomException::class)
    @ResponseBody
    fun customerException(e: CustomException): Response {
        return Response.fail(e)
    }


    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun customerException(ex: Exception): Response {
        return Response.fail(CustomException(CustomExceptionType.OTHER_ERROR))
    }
}