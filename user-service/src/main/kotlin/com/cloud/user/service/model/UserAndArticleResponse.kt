package com.cloud.user.service.model

import com.cloud.user.service.entity.UserEntity


data class UserAndArticleResponse(val user: UserEntity, val articles: Articles)