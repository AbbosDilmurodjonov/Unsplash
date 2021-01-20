package uz.dilmurodjonov_a.unsplash.ui.user_profile

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean

class UserProfileUIController(private val router: UserProfileRouter) : BaseObservable() {
    var user: UserBean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }

    fun onBackClick() = router.onBack()

    @Bindable
    fun getName(): String = user?.name ?: ""

    @Bindable
    fun getUsername(): String = user?.username ?: ""

    @Bindable
    fun getUserSmallImageUrl(): String = user?.profile_image?.medium ?: ""

    @Bindable
    fun getUserImageUrl(): String = user?.profile_image?.large ?: ""

    @Bindable
    fun getFollowingCount(): String = (user?.following_count ?: 0).toString()

    @Bindable
    fun getFollowersCount(): String = (user?.followers_count ?: 0).toString()

    @Bindable
    fun getTotalLikes(): String = (user?.total_likes ?: 0).toString()

    @Bindable
    fun getTotalPhotos(): String = (user?.total_photos ?: 0).toString()

    @Bindable
    fun getTotalDownload(): String = (user?.downloads ?: 0).toString()

    @Bindable
    fun getInstagram(): String = user?.instagram_username ?: ""

    @Bindable
    fun getHasInstagram(): Boolean = user?.instagram_username != null

    @Bindable
    fun getTwitter(): String = user?.twitter_username ?: ""

    @Bindable
    fun getHasTwitter(): Boolean = user?.twitter_username != null
}