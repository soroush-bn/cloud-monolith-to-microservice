package com.cloud.user.service.model

import com.cloud.user.service.entity.UserEntity

data class UserWithCommentAndArticle(val user: UserEntity, val comment: Comment,val article:Article)
