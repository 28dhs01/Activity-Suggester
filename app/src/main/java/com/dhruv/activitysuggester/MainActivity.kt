package com.dhruv.activitysuggester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.dhruv.activitysuggester.databinding.ActivityMainBinding
import com.dhruv.activitysuggester.model.ActivitySuggesterDataClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()
        binding.btnNewActivitySuggester.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        binding.progressBar.isVisible = true
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.boredapi.com/")
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ActivitySuggesterDataClass?> {
            override fun onResponse(
                call: Call<ActivitySuggesterDataClass?>,
                response: Response<ActivitySuggesterDataClass?>
            ) {
                binding.progressBar.isVisible = false
                val responseBody = response.body()!!
                binding.tvActivitySuggester.text = responseBody.activity
            }

            override fun onFailure(call: Call<ActivitySuggesterDataClass?>, t: Throwable) {
                binding.progressBar.isVisible = false
                Log.e("MainActivity","onFailure"+t.message)
            }
        })
    }
}