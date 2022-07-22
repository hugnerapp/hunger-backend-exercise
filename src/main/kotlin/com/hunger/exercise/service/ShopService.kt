package com.hunger.exercise.service

import com.hunger.exercise.data.`do`.Shop
import com.hunger.exercise.data.dto.ShopDTO
import com.hunger.exercise.data.dto.ShopPageDTO
import org.springframework.data.domain.Page

interface ShopService {

    fun addShop( shopDTO: ShopDTO)
    fun modifyShop ( shopDTO: ShopDTO)
    fun removeById(id: Long)
    fun findAllShop(): List<Shop>
    fun findShopAndDesigners(id: Long): ShopDTO
    fun findShopPageListByShopNameOrByDesignerName(shopPageDTO: ShopPageDTO): Page<Shop>?
}
