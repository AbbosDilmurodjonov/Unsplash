package uz.dilmurodjonov_a.unsplash.ui.user_profile

import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean
import uz.dilmurodjonov_a.unsplash.ui._base.BaseRouter

interface UserProfileRouter : BaseRouter<List<PhotosBean>> {

    fun onBack()

    fun onSuccessGetUser(user: UserBean?)
}