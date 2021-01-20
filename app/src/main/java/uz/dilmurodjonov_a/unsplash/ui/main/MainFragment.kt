package uz.dilmurodjonov_a.unsplash.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import uz.dilmurodjonov_a.unsplash.R
import uz.dilmurodjonov_a.unsplash.adapter.RecyclerAdapter
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.databinding.FragmentMainBinding
import uz.dilmurodjonov_a.unsplash.databinding.ItemPhotoBinding
import uz.dilmurodjonov_a.unsplash.ui._base.BaseFragment
import uz.dilmurodjonov_a.unsplash.ui.main.item.PhotoItemUIController
import uz.dilmurodjonov_a.unsplash.ui.user_profile.UserProfileFragment


/**
 * Created by Abbos Dilmurodjonov (AyDee) on 10.01.2021.
 */
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(),
    MainRouter, RecyclerAdapter.AdapterListener<PhotosBean> {

    private val pagePerCount: Int = 10

    private lateinit var adapter: RecyclerAdapter<PhotosBean>
    private var page: Int = 0

    override val layoutId: Int = R.layout.fragment_main
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override fun onResume() {
        super.onResume()
        viewModel!!.setRouter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel!!.setRouter(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RecyclerAdapter(R.layout.item_photo, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = MainUIController(this)
        databinding?.controller = controller

        databinding?.recyclerPhotos?.adapter = adapter
        databinding?.recyclerPhotos?.setHasFixedSize(true)

        databinding?.recyclerPhotos?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && page != 0
                    && databinding?.progressLoad?.visibility != View.VISIBLE
                ) {
                    viewModel?.getPhotoList(++page, pagePerCount)
                }
            }
        })

        databinding?.imageMain?.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.placeholder
            )
        )

        viewModel!!.setRouter(this)
        page = 1
        Handler(Looper.getMainLooper()).postDelayed({ viewModel?.getPhotoList(page) }, 300)
    }

    override fun onSuccessGetRandomPhoto(photo: PhotosBean) {
        //TODO on success get random photo
    }

    override fun onItemSelected(photo: PhotosBean?) {
        databinding?.controller?.bean = photo
    }

    override fun openUserProfile(username: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, UserProfileFragment.newInstance(username))
            .addToBackStack(null)
            .commit()
    }

    override fun setLoading(b: Boolean) {

        databinding?.progressLoad?.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onSuccess(response: List<PhotosBean>) {
        if (page == 1) {
            adapter.setList(response.toMutableList())
            databinding?.controller?.bean = response[0]
        } else adapter.addItems(response)

    }

    override fun onError(errorMsg: String?) {
        Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_SHORT).show()
        page--
    }

    override fun setController(dataBinding: ViewDataBinding?) {
        val controller = PhotoItemUIController(this)
        (dataBinding as ItemPhotoBinding).controller = controller
    }

    override fun bindItem(dataBinding: ViewDataBinding?, item: PhotosBean) {
        (dataBinding as ItemPhotoBinding).controller?.bean = item
        dataBinding.executePendingBindings()
    }


}