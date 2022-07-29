package com.hunger.exercise.data.dto

import com.hunger.exercise.data.`do`.ServiceTime
import java.time.LocalTime

data class ServiceTimeDTO(

    val id:Long?,
    val weekend:Int,
    val workOnTime: LocalTime,
    val workOffTime: LocalTime,
    var designerId:Long?

) {
    constructor(
        weekend: Int,
        workOnTime: LocalTime,
        WorkOffTime: LocalTime
    ) : this(null, weekend, workOnTime, WorkOffTime, null)


    fun toDo()= designerId?.let {
        ServiceTime(
        id = null,
        weekend = weekend,
        workOnTime = workOnTime,
        WorkOffTime = workOffTime,
        designerId = it
    )
    }

}
