package uz.dilmurodjonov_a.unsplash.ui.main

import android.app.Application
import uz.dilmurodjonov_a.unsplash.api.ApiCallback
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.repository.PhotosRepository
import uz.dilmurodjonov_a.unsplash.ui._base.BaseViewModel

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
class MainViewModel(application: Application) : BaseViewModel<MainRouter>(application) {

    private val photosRepository: PhotosRepository = PhotosRepository(application)

    fun getPhotoList(page: Int, perCount: Int = 20) {
        getRouter()?.setLoading(true)
        photosRepository.photosList(page, perCount, object : ApiCallback<List<PhotosBean>> {
            override fun onSuccess(response: List<PhotosBean>) {
                getRouter()?.setLoading(false)
                getRouter()?.onSuccess(response)
            }

            override fun onErrorMsg(errorMsg: String?) {
                getRouter()?.setLoading(false)
                getRouter()?.onError(errorMsg)
            }

        })
    }


    fun getRandomPhoto() {
        getRouter()?.setLoading(true)
        photosRepository.randomPhoto(object : ApiCallback<PhotosBean> {
            override fun onSuccess(response: PhotosBean) {
                getRouter()?.setLoading(false)
                getRouter()?.onSuccessGetRandomPhoto(response)
            }

            override fun onErrorMsg(errorMsg: String?) {
                getRouter()?.setLoading(false)
                getRouter()?.onError(errorMsg)
            }

        })
    }
}