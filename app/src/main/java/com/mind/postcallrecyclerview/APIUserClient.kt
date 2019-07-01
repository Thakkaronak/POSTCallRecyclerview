package com.mind.postcallrecyclerview

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIUserClient {
    private val BASE_URL = "http://dummy.restapiexample.com/api/v1/"
    val apiInterface: APIInterface
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(APIInterface::class.java)
        }
}