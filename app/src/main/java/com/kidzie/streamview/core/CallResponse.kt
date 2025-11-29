package com.kidzie.streamview.core

import com.google.gson.annotations.SerializedName

data class CallResponse<T>(
    @SerializedName("results")
    val data : T,
    val page : Int,
    val totalPages : Int,
    val totalResults : Int
)
