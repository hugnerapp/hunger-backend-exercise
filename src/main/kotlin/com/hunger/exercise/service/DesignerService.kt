package com.hunger.exercise.service

import com.hunger.exercise.data.dto.DesignerDTO
import java.time.LocalDate

interface DesignerService {

    fun addOrUpdateDesigner(designerDTO: DesignerDTO)
    fun removeDesignerById(id: Long)
    fun findDesignerById(id: Long):DesignerDTO
    fun findShopAndDesignersOnWork(date: LocalDate): List<Map<String, Any>>
}
