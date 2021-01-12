package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class UserBean(
    val id: String,
    val username: String,
    val name: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val total_likes: Int,
    val total_photos: Int,
    val total_collections: Int,
    val instagram_username: String,
    val twitter_username: String,
    val profile_image: ProfileImageBean,
    val links: LinksBean
)

/** Example
 *@param "id": "pXhwzz1JtQU",
 *@param "username": "poorkane",
 *@param "name": "Gilbert Kane",
 *@param "portfolio_url": "https://theylooklikeeggsorsomething.com/",
 *@param "bio": "XO",
 *@param "location": "Way out there",
 *@param "total_likes": 5,
 *@param "total_photos": 74,
 *@param "total_collections": 52,
 *@param "instagram_username": "instantgrammer",
 *@param "twitter_username": "crew",
 **/
