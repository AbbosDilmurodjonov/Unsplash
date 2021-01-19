package uz.dilmurodjonov_a.unsplash.ui.user_profile.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.ui.main.MainRouter
import uz.dilmurodjonov_a.unsplash.ui.user_profile.UserProfileRouter

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 11.01.2021.
 */
class PhotoItemUIController(val router: UserProfileRouter) : BaseObservable() {

    var bean: PhotosBean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }

    fun onItemClick(){
        //TODO router.onItemSelected(bean);
    }

    @Bindable
    fun getImageUrl(): String {
        return bean?.urls?.thumb!!
    }


}