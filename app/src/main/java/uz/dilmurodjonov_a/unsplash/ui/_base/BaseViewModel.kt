package uz.dilmurodjonov_a.unsplash.ui._base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<D : BaseRouter<*>?>(application: Application) :
    AndroidViewModel(application) {

    private var router: WeakReference<*>? = null

    fun getRouter(): D? {
        return if (router != null) router!!.get() as D? else null
    }

    fun setRouter(r: Any?) {
        router = WeakReference<Any?>(r)
    }

}