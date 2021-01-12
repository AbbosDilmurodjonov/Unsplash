package uz.dilmurodjonov_a.unsplash.ui._base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<D : ViewDataBinding?, VM : BaseViewModel<*>?> : Fragment() {
    var databinding: D? = null
        protected set

    protected var viewModel: VM? = null


    abstract val layoutId: Int
    abstract val viewModelClass: Class<VM>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = ViewModelProvider(this)[viewModelClass]
        return databinding!!.root
    }

    override fun onPause() {
        super.onPause()
        viewModel!!.setRouter(null)
    }
}