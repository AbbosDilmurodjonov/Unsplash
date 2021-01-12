package uz.dilmurodjonov_a.unsplash.data.bean

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
data class UrlsBean(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)

/** Example
 * @param "raw": "https://images.unsplash.com/face-springmorning.jpg",
 * @param "full": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg",
 * @param "regular": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=1080&fit=max",
 * @param "small": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=400&fit=max",
 * @param "thumb": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=200&fit=max"
 */
