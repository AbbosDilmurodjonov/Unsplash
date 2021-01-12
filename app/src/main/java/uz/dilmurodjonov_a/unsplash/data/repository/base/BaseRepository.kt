package uz.dilmurodjonov_a.unsplash.data.repository.base

import android.app.Application
import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import uz.dilmurodjonov_a.unsplash.R
import uz.dilmurodjonov_a.unsplash.api.Api
import uz.dilmurodjonov_a.unsplash.api.ApiCallback
import uz.dilmurodjonov_a.unsplash.api.RetrofitClient

abstract class BaseRepository(application: Application) {
    private var retrofitClient: Retrofit? = null
    private val context: Context
    private var msg: String? = null

    val api: Api
        get() {
            if (retrofitClient == null) retrofitClient = RetrofitClient.initClient()
            return retrofitClient!!.create(Api::class.java)
        }

    fun <T> request(call: Call<T>, loaderCallback: ApiCallback<T>) {
        msg = ""
        call.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                if (response.isSuccessful && response.body() != null)
                    loaderCallback.onSuccess(response.body()!!)
                else
                    loaderCallback.onErrorMsg(context.resources.getString(R.string.failure_to_connect))
            }

            override fun onFailure(call: Call<T?>, t: Throwable) {
                loaderCallback.onErrorMsg(context.resources.getString(R.string.failure_to_connect))
            }
        })
    }

    init {
        context = application
    }
}