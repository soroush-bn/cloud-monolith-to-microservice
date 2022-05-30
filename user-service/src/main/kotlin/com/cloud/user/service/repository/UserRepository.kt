package com.cloud.user.service.repository

import com.cloud.user.service.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun getUserEntityByUsername(userName: String): UserEntity
}