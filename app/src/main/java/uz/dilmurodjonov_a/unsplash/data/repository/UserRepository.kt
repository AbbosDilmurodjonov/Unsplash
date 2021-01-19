package uz.dilmurodjonov_a.unsplash.data.repository

import android.app.Application
import uz.dilmurodjonov_a.unsplash.api.ApiCallback
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean
import uz.dilmurodjonov_a.unsplash.data.repository.base.BaseRepository

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
class UserRepository(application: Application) : BaseRepository(application) {
    fun getUser(username: String, apiCallback: ApiCallback<UserBean?>) {
        request(api.getUser(username), apiCallback)
    }
}