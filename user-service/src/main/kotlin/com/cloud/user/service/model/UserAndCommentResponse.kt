package com.cloud.user.service.model

import com.cloud.user.service.entity.UserEntity


data class UserAndCommentResponse(val user: UserEntity, val comments: Comments)