package uz.dilmurodjonov_a.unsplash.ui.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 11.01.2021.
 */
class MainUIController(private val router: MainRouter) : BaseObservable() {

    var bean: PhotosBean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }

    fun onMenuClick() {
        //TODO open-> NavigationView
    }

    fun onNotificationClick() {
        //TODO open-> Bottom Sheet Dialog
    }

    fun onSearchClick() {
        //TODO goto-> Search Fragment
    }

    fun onDashboardClick() {
        //TODO goto-> Dashboard Fragment
    }

    fun onHomeClick() {
        //TODO goto-> Home Fragment
    }

    fun onFavoriteClick() {
        //TODO goto-> Favorite Fragment
    }

    fun onProfileClick() {
        //TODO goto-> Profile Fragment
    }

    fun onUserInfoClick() {
        bean?.user?.username?.let { router.openUserProfile(it) }
    }


    @Bindable
    fun getImageUrl(): String {
        return if (bean != null) bean?.urls?.regular!! else ""
    }

    @Bindable
    fun getUserImageUrl(): String {
        return if (bean != null
            && bean?.user != null
            && bean?.user?.profile_image != null
            && bean?.user?.profile_image?.large != null
        ) bean?.user?.profile_image?.large!! else ""
    }

    @Bindable
    fun getName(): String {
        return if (bean != null
            && bean?.user != null
            && bean?.user?.name != null
        ) bean?.user?.name!! else ""
    }

    @Bindable
    fun getUserName(): String {
        return if (bean != null
            && bean?.user != null
            && bean?.user?.username != null
        )
            bean?.user?.username!! else ""
    }

    @Bindable
    fun getPhotoDescription(): String {
        return if (bean != null && bean?.description != null) bean?.description!! else "No Description"
    }

    @Bindable
    fun getLikeCount(): String {
        val count = if (bean != null && bean?.likes != null) bean?.likes!! else 0
        return if (count / 1_000 > 0) String.format(
            "%.1fK",
            count.toDouble() / 1_000
        ) else count.toString()
    }

    @Bindable
    fun getHasInstagram(): Boolean {
        if (bean != null && bean?.user != null && bean?.user?.instagram_username != null)
            return bean?.user?.instagram_username!!.isNotEmpty()

        return false
    }

    @Bindable
    fun getHasTwitter(): Boolean {
        if (bean != null && bean?.user != null && bean?.user?.twitter_username != null)
            return bean?.user?.twitter_username!!.isNotEmpty()

        return false
    }

}