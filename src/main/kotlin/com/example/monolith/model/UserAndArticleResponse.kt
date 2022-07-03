package com.example.monolith.model

import com.example.monolith.entity.UserEntity


data class UserAndArticleResponse(val user: UserEntity, val articles: Articles)