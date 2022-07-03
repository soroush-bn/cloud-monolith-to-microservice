package com.example.monolith.model

import com.example.monolith.entity.UserEntity


data class UserAndCommentResponse(val user: UserEntity, val comments: Comments)