package com.hunger.exercise.data.`do`

import com.hunger.exercise.data.dto.ServiceTimeDTO
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table
data class ServiceTime(

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    val id:Long?,
    @Column
    val weekend:Int,
    @Column(columnDefinition = "TIME")
    val workOnTime:LocalTime,
    @Column(columnDefinition = "TIME")
    val WorkOffTime:LocalTime,
    @Column
    var designerId:Long

){
    fun toDTO() = ServiceTimeDTO(
        weekend,
        workOnTime,
        WorkOffTime
    )

    }




