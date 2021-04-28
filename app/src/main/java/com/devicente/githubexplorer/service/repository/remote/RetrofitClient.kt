package com.devicente.githubexplorer.service.repository.remote

import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient private constructor() {
    companion object {

        private const val baseUrl = GithubExplorerConstants.BASE_URL
        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            if (!Companion::retrofit.isInitialized) {
                val mosh = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(MoshiConverterFactory.create(mosh))
                    .build()

            }
            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}