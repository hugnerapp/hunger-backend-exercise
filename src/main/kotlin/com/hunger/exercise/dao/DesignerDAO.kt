package com.hunger.exercise.dao

import com.hunger.exercise.data.`do`.Designer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.query.QueryByExampleExecutor

interface DesignerDAO : JpaRepository<Designer, Long>,
    JpaSpecificationExecutor<Designer>,
    QueryByExampleExecutor<Designer> {
    fun findByShopId(id: Long):List<Designer>

    fun findByName(name: String):List<Designer>


}