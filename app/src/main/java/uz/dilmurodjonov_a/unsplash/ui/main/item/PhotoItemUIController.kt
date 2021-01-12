package uz.dilmurodjonov_a.unsplash.ui.main.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.ui.main.MainRouter

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 11.01.2021.
 */
class PhotoItemUIController(val router: MainRouter) : BaseObservable() {

    var bean: PhotosBean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }

    fun onItemClick(){
        router.onItemSelected(bean);
    }

    @Bindable
    fun getImageUrl(): String {
        return bean?.urls?.thumb!!
    }


}