package com.hunger.exercise.controller

import com.hunger.exercise.data.dto.ShopDTO
import com.hunger.exercise.data.dto.ShopPageDTO
import com.hunger.exercise.exception.Response
import com.hunger.exercise.service.ShopService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/shop")
class ShopController(
    val shopService: ShopService
) {



    //    新增店家
    @PostMapping("/add")
    fun addShop(@RequestBody shop: ShopDTO): Response {

        shopService.addShop(shop)
        return Response.success()
    }

    //     更新店家

    @PatchMapping("/modify")
    fun modifyShopName(@RequestBody shop: ShopDTO): Response {
        shopService.modifyShop(shop)
        return Response.success()
    }

    //刪除店家
    @DeleteMapping("/{id}")
    fun deleteShop(@PathVariable id: Long): Response {
         shopService.removeById(id)
        return Response.success()
    }

    //    店家列表
    @PostMapping("/list")
    fun findShopList(): Response {
        val shopList = shopService.findAllShop()
        return Response.success(shopList)
    }

//    取得單筆店家資料，包含其下的設計師資料
    @PostMapping("/{id}")
    fun findShopById(@PathVariable id: Long): Response {
        val shop = shopService.findShopAndDesigners(id)
        return Response.success(shop)
    }


    // 分頁查詢店家列表，可以透過店家名稱或設計師名稱查詢到店家
    // pageNo: 第幾頁，pageSize: 每頁幾筆資料
    // 預設 pageNo = 0, pageSize = 10
    @PostMapping("/listPage")
    fun findShopListByPage(@RequestBody shopPageDTO: ShopPageDTO): Response {
        val shopList = shopService.findShopPageListByShopNameOrByDesignerName(shopPageDTO)
        return Response.success(shopList)
    }

}