package com.hunger.exercise.service.impl

import com.hunger.exercise.dao.DesignerDAO
import com.hunger.exercise.dao.ServiceTimeDAO
import com.hunger.exercise.dao.ShopDAO
import com.hunger.exercise.data.`do`.Shop
import com.hunger.exercise.data.dto.ShopDTO
import com.hunger.exercise.data.dto.ShopPageDTO
import com.hunger.exercise.exception.CustomException
import com.hunger.exercise.exception.CustomExceptionType
import com.hunger.exercise.service.ShopService
import com.hunger.exercise.utils.StringUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class ShopServiceImpl(
    val shopDAO: ShopDAO,
    val designerDAO: DesignerDAO,
    val serviceTimeDAO: ServiceTimeDAO,
    val stringUtil: StringUtil
) : ShopService {

    override
    fun addShop(shopDTO: ShopDTO) {
        val shop: Shop = shopDTO.toDo()

        //check Name Repeat
        if (shopDAO.checkNameRepeat(shop.name) > 0) {
            throw CustomException(CustomExceptionType.SYSTEM_ERROR, "店铺名称已存在")
        }

        //check Random Repeat
        var code = ""
        do {
            code = stringUtil.radnomEnglishAndNum(6)
        } while (shopDAO.checkCodeRepeat(code) > 0)

        shop.code = code
        shopDAO.save(shop)

    }

    override fun modifyShop(shopDTO: ShopDTO) {
        shopDAO.findById(shopDTO.id!!)
            .orElseThrow { RuntimeException() }
            .run {
                if (this.name != shopDTO.name) {
                    shopDAO.checkNameRepeat(shopDTO.name)
                    this.name = shopDTO.name
                    shopDAO.save(this)
                }
            }
    }


    override
    fun removeById(id: Long) = shopDAO.deleteById(id)

    override
    fun findAllShop(): List<Shop> = shopDAO.findAll()

    //*取得單筆店家資料，包含其下的設計師資料
    override
    fun findShopAndDesigners(id: Long): ShopDTO {
        val shop: Shop = shopDAO.findById(id).get()
        val designerList = designerDAO.findByShopId(id)
        val designerDTOList = designerList.map { it.toDTO() }.toMutableList()
        val res: ShopDTO = shop.toDTO()
        res.designerList = designerDTOList

        return res

    }

    //    分頁查詢店家列表，
    //    可以透過店家名稱或設計師名稱查詢到店家
    override
    fun findShopPageListByShopNameOrByDesignerName(shopPageDTO: ShopPageDTO): Page<Shop>? {

        if(shopPageDTO.shopName?.isNotBlank() == true){
           return findShopPageListByShopName(shopPageDTO)
        }

        if(shopPageDTO.designerName?.isNotBlank() == true){
            return findShopPageListByDesignerName(shopPageDTO)
        }

        //分頁查詢
        val pageable: Pageable = PageRequest.of(shopPageDTO.page, shopPageDTO.pageSize)

        return shopDAO.findAll(pageable)
    }

    private fun findShopPageListByShopName(shopPageDTO: ShopPageDTO): Page<Shop>  {
        val pageable: Pageable = PageRequest.of(shopPageDTO.page, shopPageDTO.pageSize)
        return shopDAO.findAll(equalShopName(shopPageDTO.shopName!!), pageable)
    }

    fun equalShopName(name:String): Specification<Shop> {
        return Specification<Shop> { root, query, builder -> builder.like(root.get("name"),name) }
    }


    //  設計師名稱查詢到店家
    fun findShopPageListByDesignerName(shopPageDTO: ShopPageDTO): Page<Shop>? {

        // find Shop Name
        val shop = designerDAO.findByName(shopPageDTO.designerName!!)
        if(shop.isEmpty()){
           return null
        }
        val shopId = shop.first().shopId
        val shopName = shopDAO.findById(shopId).get().name

        // sort 排序
        val pageable: Pageable = PageRequest.of(shopPageDTO.page, shopPageDTO.pageSize)

        return shopDAO.findAll(equalShopName(shopName), pageable)

    }












}