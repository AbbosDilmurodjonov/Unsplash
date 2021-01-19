package uz.dilmurodjonov_a.unsplash.ui.user_profile

import android.app.Application
import uz.dilmurodjonov_a.unsplash.api.ApiCallback
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean
import uz.dilmurodjonov_a.unsplash.data.repository.PhotosRepository
import uz.dilmurodjonov_a.unsplash.data.repository.UserRepository
import uz.dilmurodjonov_a.unsplash.ui._base.BaseViewModel

class UserProfileViewModel(application: Application) :
    BaseViewModel<UserProfileRouter>(application) {

    private val userRepository: UserRepository = UserRepository(application)
    private val photosRepository: PhotosRepository = PhotosRepository(application)

    fun getPhotoList(username: String, page: Int, perCount: Int = 20) {
        getRouter()?.setLoading(true)
        photosRepository.userPhotosList(
            username,
            page,
            perCount,
            object : ApiCallback<List<PhotosBean>> {
                override fun onSuccess(response: List<PhotosBean>) {
                    getRouter()?.apply {
                        setLoading(false)
                        onSuccess(response)
                    }
                }

                override fun onErrorMsg(errorMsg: String?) {
                    getRouter()?.apply {
                        setLoading(false)
                        onError(errorMsg)
                    }
                }

            })
    }

    fun getUser(username: String) {

        getRouter()?.setLoading(true)

        userRepository.getUser(username, object : ApiCallback<UserBean?> {
            override fun onSuccess(response: UserBean?) {
                getRouter()?.apply {
                    setLoading(false)
                    onSuccessGetUser(response)
                }
            }

            override fun onErrorMsg(errorMsg: String?) {
                getRouter()?.apply {
                    setLoading(false)
                    onError(errorMsg)
                }
            }

        })

    }
}