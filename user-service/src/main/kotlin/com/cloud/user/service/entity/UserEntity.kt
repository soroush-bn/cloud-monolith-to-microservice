package com.cloud.user.service.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class UserEntity(var email: String = "",
                      @JsonIgnore
                var password: String = "",
                      var token: String = "",
                      var username: String = "",
                      var bio: String = "",
                      var image: String = "",
                      @Id @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long = 0)
