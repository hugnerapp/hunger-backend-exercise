package com.hunger.exercise.dao

import com.hunger.exercise.data.`do`.ServiceTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor


interface ServiceTimeDAO : JpaRepository<ServiceTime, Long>,
    JpaSpecificationExecutor<ServiceTime> {

    fun deleteByDesignerId(designerId: Long)
    fun findByDesignerId(id: Long):List<ServiceTime>

}
