package uz.dilmurodjonov_a.unsplash.ui._base

interface BaseRouter<T> {
    fun setLoading(b: Boolean)
    fun onSuccess(response: T)
    fun onError(errorMsg: String?)
}