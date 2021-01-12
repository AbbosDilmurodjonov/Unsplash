package uz.dilmurodjonov_a.unsplash.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.dilmurodjonov_a.unsplash.utils.Constants


/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
object RetrofitClient {

    fun initClient(): Retrofit {
        val retrofit: Retrofit;

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVER_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}