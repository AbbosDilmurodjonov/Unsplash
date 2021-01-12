package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class LinksBean(
    val self: String,
    val html: String,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val download: String?,
    val download_location: String?
)

/** Example
 * @param "self": "https://api.unsplash.com/users/poorkane",
 * @param "html": "https://unsplash.com/poorkane",
 * @param "photos": "https://api.unsplash.com/users/poorkane/photos",
 * @param "likes": "https://api.unsplash.com/users/poorkane/likes",
 * @param "portfolio": "https://api.unsplash.com/users/poorkane/portfolio"
 */
