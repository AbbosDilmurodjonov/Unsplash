package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class LocationBean(
    val city: String,
    val country: String,
    val position: PositionBean,
) {
    /** Example
     * @param "city": "Montreal",
     * @param "country": "Canada",
     **/


    data class PositionBean(val latitude: Double, val longitude: Double)
    /** Example
     * @param "latitude": 45.473298,
     * @param "longitude": -73.638488
     **/

}




