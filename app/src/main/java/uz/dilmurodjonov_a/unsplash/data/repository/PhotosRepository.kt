package uz.dilmurodjonov_a.unsplash.data.repository

import android.app.Application
import uz.dilmurodjonov_a.unsplash.api.ApiCallback
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.repository.base.BaseRepository

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
class PhotosRepository(application: Application) : BaseRepository(application) {

    fun photosList(page: Int, perCount: Int, apiCallback: ApiCallback<List<PhotosBean>>) =
        request(api.getPhotoList(page, perCount), apiCallback)


    fun userPhotosList(
        username: String,
        page: Int,
        perCount: Int,
        apiCallback: ApiCallback<List<PhotosBean>>
    ) = request(api.getUserPhotoList(username, page, perCount), apiCallback)


    fun randomPhoto(apiCallback: ApiCallback<PhotosBean>) =
        request(api.getRandomPhoto(), apiCallback)


}