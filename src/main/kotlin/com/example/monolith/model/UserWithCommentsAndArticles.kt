package com.example.monolith.model

import com.example.monolith.entity.UserEntity

data class UserWithCommentsAndArticles(val user: UserEntity, val comments: Comments, val articles: Articles)
