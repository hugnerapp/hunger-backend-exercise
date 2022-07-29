package com.hunger.exercise.service.impl

import com.hunger.exercise.dao.DesignerDAO
import com.hunger.exercise.dao.ServiceTimeDAO
import com.hunger.exercise.dao.ShopDAO
import com.hunger.exercise.data.`do`.Designer
import com.hunger.exercise.data.`do`.ServiceTime
import com.hunger.exercise.data.dto.DesignerDTO
import com.hunger.exercise.data.dto.ServiceTimeDTO
import com.hunger.exercise.service.DesignerService
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate


@Service
class DesignerServiceImpl(
    val designerDAO: DesignerDAO,
    val serviceTimeDAO: ServiceTimeDAO,
    val shopDAO: ShopDAO
) : DesignerService {

    //    新增店家下的設計師及服務時段
    @Modifying
    @Transactional
    override
    fun addOrUpdateDesigner(designerDTO:DesignerDTO){
        val designer: Designer = designerDTO.toDo()
        //name could repeat
        val designerDb = designerDAO.save(designer)

        //when update ,delete all service time
        if(designerDTO.id !=null){
            serviceTimeDAO.deleteByDesignerId(designerDTO.id)
        }

        val designerServiceTime:List<ServiceTimeDTO> = designerDTO.addDesignerIdOnServiceTime(designerDb.id!!)

        serviceTimeDAO.saveAll( designerServiceTime.map { it.toDo()!! }.toList())

    }



    //   刪除店家下的設計師
    @Modifying
    @Transactional
    override
    fun removeDesignerById(id:Long){
        //刪除設計師
        designerDAO.deleteById(id)

        //刪除時段
        serviceTimeDAO.deleteByDesignerId(id)

    }

    //    取得單筆設計師，包含所屬店家及其服務時段
    override
    fun findDesignerById(id:Long):DesignerDTO{
        val designer:Designer = designerDAO.findById(id).get()
        val shop = shopDAO.findById(designer.shopId)
        val serviceTIme:List<ServiceTime> = serviceTimeDAO.findByDesignerId(id)
        val designerDTO:DesignerDTO = designer.toDTO()
        designerDTO.shop=shop.get()
        designerDTO.serviceTime=serviceTIme.map { it.toDTO() }
        return  designerDTO
    }

    override
    fun findShopAndDesignersOnWork(date: LocalDate): List<Map<String,Any>> {

        //判斷禮拜幾
        val weekDayValue = date.dayOfWeek.value
        return shopDAO.findShopAndDesignersOnWork(weekDayValue)
    }

}