package com.hunger.exercise.dao

import com.hunger.exercise.data.`do`.Shop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ShopDAO : JpaRepository<Shop, Long>,
    JpaSpecificationExecutor<Shop> {

    @Query(value = "select count(*) from Shop s where s.name = :name ", nativeQuery = true)
    fun checkNameRepeat(@Param("name") name: String): Int

    @Query(value = "select count(*) from Shop s where s.code = :code ", nativeQuery = true)
    fun checkCodeRepeat(@Param("code") code: String): Int



    @Query(value =
    """
    select d.name ,shop.name shopName
    from shop
    left join  designer d on shop.id = d.shop_id
    left join service_time s on d.id = s.designer_id
    where s.weekend = :weekend
    """, nativeQuery = true)
    fun findShopAndDesignersOnWork(@Param("weekend") code: Int): List<Map<String,Any>>


}