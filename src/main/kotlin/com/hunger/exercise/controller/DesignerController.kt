package com.hunger.exercise.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.ObjectMapper
import com.hunger.exercise.data.dto.DesignerDTO
import com.hunger.exercise.exception.Response
import com.hunger.exercise.service.DesignerService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/designer")
class DesignerController(
    val designerService: DesignerService
) {

    //    新增店家下的設計師及服務時段
    @PostMapping("/add")
    fun addDesigner(@RequestBody designer: DesignerDTO): Response {
        designerService.addOrUpdateDesigner(designer)
        return Response.success()
    }


    //    更新店家下的設計師及服務時段
    @PutMapping("/modify")
    fun modifyDesigner(@RequestBody designer: DesignerDTO): Response {
        designerService.addOrUpdateDesigner(designer)
        return Response.success()
    }


    //    刪除店家下的設計師
    @DeleteMapping("/{id}")
    fun deleteDesigner(@PathVariable id:Long): Response {
        designerService.removeDesignerById(id)
        return Response.success()
    }


    //    取得單筆設計師，包含所屬店家及其服務時段
    @PostMapping("/{id}")
    fun findDesignerById(@PathVariable id:Long): Response {
        val designer = designerService.findDesignerById(id)
        return Response.success(designer)
    }


//    以日期查詢有服務的設計師及所屬店家
    @PostMapping("/findDesignerByDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    fun findDesignerByDate(@RequestBody date: String): Response {

    val dateString = ObjectMapper().readTree(date).get("date").asText()
    val dateArray = dateString.split("-")
    val inputTime = LocalDate.of(dateArray[0].toInt(),dateArray[1].toInt(), dateArray[2].toInt())

    val designerOnWork = designerService.findShopAndDesignersOnWork(inputTime)

    return Response.success(designerOnWork)

}

}