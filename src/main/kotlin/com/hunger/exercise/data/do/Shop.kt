package com.hunger.exercise.data.`do`

import com.hunger.exercise.data.dto.ShopDTO
import javax.persistence.*

@Entity
@Table
data class Shop(

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    val id: Long?,

    @Column
    var code: String?,

    @Column
    var name: String,
) {

    fun toDTO() = ShopDTO(
        id = id,
        code = code,
        name = name
    )

}