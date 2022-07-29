package com.hunger.exercise.data.dto

import com.hunger.exercise.data.`do`.Shop

data class ShopDTO(
    var id: Long?,
    var code: String?,
    var name: String,
) {
    var designerList:List<DesignerDTO> = listOf()

    fun toDo()=Shop(
        id = id,
        code = code,
        name = name
    )

}
