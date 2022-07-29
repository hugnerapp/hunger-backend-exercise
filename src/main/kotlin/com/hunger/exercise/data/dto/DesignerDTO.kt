package com.hunger.exercise.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.hunger.exercise.data.`do`.Designer
import com.hunger.exercise.data.`do`.Shop


data class DesignerDTO(
    @JsonProperty("id")
    val id: Long?,
    val name: String,
    val shopId: Long,
) {
    var serviceTime:List<ServiceTimeDTO> = listOf()
    var shop: Shop? =null

    fun toDo() = Designer(
        id = id,
        name = name,
        shopId = shopId
    )

    fun addDesignerIdOnServiceTime(id:Long):List<ServiceTimeDTO>{
        serviceTime.forEach {
            it.designerId = id
        }
        return this.serviceTime
    }





}