package uz.dilmurodjonov_a.unsplash.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean
import uz.dilmurodjonov_a.unsplash.utils.Constants

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
interface Api {

    @GET("photos")
    fun getPhotoList(
        @Query("page") page: Int,
        @Query("per_page") perPageCount: Int,
        @Query("client_id") accessKey: String = Constants.ACCESS_KEY
    ): Call<List<PhotosBean>>

    @GET("users/{username}/photos")
    fun getUserPhotoList(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPageCount: Int,
        @Query("client_id") accessKey: String = Constants.ACCESS_KEY
    ): Call<List<PhotosBean>>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String,
        @Query("client_id") accessKey: String = Constants.ACCESS_KEY
    ): Call<UserBean?>

    @GET("photos/random")
    fun getRandomPhoto(
        @Query("client_id") accessKey: String = Constants.ACCESS_KEY
    ): Call<PhotosBean>

}