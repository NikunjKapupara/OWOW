package com.owow.androidapp.ws_models

import com.google.gson.annotations.SerializedName

data class LoginModel(
        @SerializedName("api_token")
        var apiToken: String, // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvYXBpLndlYXJlbWF0Y2hwbGF5LmNvbVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNTM1MDk1MDMwLCJleHAiOjE1NjY2MzEwMzAsIm5iZiI6MTUzNTA5NTAzMCwianRpIjoia3NmalRtN2ZOelh6YWl5QiIsInN1YiI6MjY2MCwicHJ2IjoiNGFjMDVjMGY4YWMwOGYzNjRjYjRkMDNmYjhlMWY2MzFmZWMzMjJlOCJ9.t65Zc40zbzB_S1hAqw7fvStSDbhoqU_Ko_ZH9D2DTqo
        var message: String,
        var errors: Errors
){
        data class Errors(
                var login: List<String>
        )
}