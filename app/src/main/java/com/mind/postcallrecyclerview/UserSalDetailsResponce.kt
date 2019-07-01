package com.mind.postcallrecyclerview

import com.google.gson.annotations.SerializedName

class UserSalDetailsResponce {

    @SerializedName("name")
    val name: String? = null
    @SerializedName("salary")
    val salary: String? = null
    @SerializedName("age")
    val age: String? = null
    @SerializedName("id")
    val id: String? = null

    @SerializedName("error")
    var error: ErrorBean? = null

    class ErrorBean {
        @SerializedName("text")
        var text: String = ""
    }
}