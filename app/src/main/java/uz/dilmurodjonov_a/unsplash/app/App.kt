package uz.dilmurodjonov_a.unsplash.app

import android.app.Application
import uz.dilmurodjonov_a.unsplash.api.RetrofitClient

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.initClient();
    }
}