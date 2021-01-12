package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class PhotosBean(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val likes: Int,
    val liked_by_user: Boolean,
    val description: String,
    val user: UserBean,
    val location: LocationBean?,
    val tags: List<TagBean>?,
    val current_user_collections: List<CollectionBean>,
    val urls: UrlsBean,
    val links: LinksBean
)
/** Example
 * @param "id": "LBI7cgq3pbM",
 * @param "created_at": "2016-05-03T11:00:28-04:00",
 * @param "updated_at": "2016-07-10T11:00:01-05:00",
 * @param "width": 5245,
 * @param "height": 3497,
 * @param "color": "#60544D",
 * @param "blur_hash": "LoC%a7IoIVxZ_NM|M{s:%hRjWAo0",
 * @param "likes": 12,
 * @param "liked_by_user": false,
 * @param "description": "A man drinking a coffee."
 */

