package uz.dilmurodjonov_a.unsplash.ui.main

import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.ui._base.BaseRouter

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 11.01.2021.
 */
interface MainRouter : BaseRouter<List<PhotosBean>> {
    fun onSuccessGetRandomPhoto(photo: PhotosBean)

    fun onItemSelected(photo: PhotosBean?)
}