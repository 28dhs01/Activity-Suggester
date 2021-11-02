package com.dhruv.activitysuggester

import com.dhruv.activitysuggester.model.ActivitySuggesterDataClass
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/activity")
    fun getData() : Call<ActivitySuggesterDataClass>
}