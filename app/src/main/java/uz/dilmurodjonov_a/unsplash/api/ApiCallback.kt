package uz.dilmurodjonov_a.unsplash.api


interface ApiCallback<T> {
    fun onSuccess(response: T)
    fun onErrorMsg(errorMsg: String?)
}