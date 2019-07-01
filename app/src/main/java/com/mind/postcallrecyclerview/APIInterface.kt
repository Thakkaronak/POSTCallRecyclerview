package com.mind.postcallrecyclerview

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("create?")
    fun getUserData(@Body emp: SalaryDetailModel): Call<UserSalDetailsResponce>
}