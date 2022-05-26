package com.cloud.user.service.model

import com.cloud.user.service.entity.UserEntity

data class UserWithCommentsAndArticles(val user: UserEntity, val comments: Comments, val articles: Articles)
