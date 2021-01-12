package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class CollectionBean(
    val id: Int,
    val title: String,
    val published_at: String,
    val last_collected_at: String,
    val updated_at: String,
    val cover_photo: Any?,
    val user: UserBean?,
)

/** Example
 * @param "id": 206,
 * @param "title": "Makers: Cat and Ben",
 * @param "published_at": "2016-01-12T18:16:09-05:00",
 * @param "last_collected_at": "2016-06-02T13:10:03-04:00",
 * @param "updated_at": "2016-07-10T11:00:01-05:00",
 * @param "cover_photo": null,
 * @param "user": null
 */
