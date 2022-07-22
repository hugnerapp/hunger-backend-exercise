package com.hunger.exercise.data.`do`

import com.hunger.exercise.data.dto.DesignerDTO
import javax.persistence.*

@Entity
@Table
data class Designer(
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    val id: Long?,

    @Column
    val name: String,

    @Column
    val shopId: Long
) {
    fun toDTO()=DesignerDTO(
        id = id,
        name = name,
        shopId = shopId
    )
}
