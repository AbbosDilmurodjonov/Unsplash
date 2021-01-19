package uz.dilmurodjonov_a.unsplash.ui.user_profile

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.dilmurodjonov_a.unsplash.adapter.RecyclerAdapter
import uz.dilmurodjonov_a.unsplash.data.bean.PhotosBean
import uz.dilmurodjonov_a.unsplash.data.bean.UserBean
import uz.dilmurodjonov_a.unsplash.databinding.FragmentUserProfileBinding
import uz.dilmurodjonov_a.unsplash.databinding.ItemUserPhotoBinding
import uz.dilmurodjonov_a.unsplash.ui._base.BaseFragment
import uz.dilmurodjonov_a.unsplash.ui.user_profile.item.PhotoItemUIController

class UserProfileFragment : BaseFragment<FragmentUserProfileBinding, UserProfileViewModel>(),
    UserProfileRouter, RecyclerAdapter.AdapterListener<PhotosBean> {

    companion object {
        const val USERNAME_KEY = "uz.dilmurodjonov_a.unsplash.ui.user_profile.username"

        fun newInstance(username: String): UserProfileFragment {
            val args = Bundle()
            args.putString(USERNAME_KEY, username)

            val fragment = UserProfileFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private val pagePerCount: Int = 10

    private lateinit var adapter: RecyclerAdapter<PhotosBean>
    private var page: Int = 0


    override val layoutId: Int = uz.dilmurodjonov_a.unsplash.R.layout.fragment_user_profile
    override val viewModelClass: Class<UserProfileViewModel> = UserProfileViewModel::class.java
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString(USERNAME_KEY)

    }

    override fun onResume() {
        super.onResume()
        viewModel?.setRouter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.setRouter(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = UserProfileUIController(this)
        databinding?.controller = controller

        databinding?.recyclerPhotos?.layoutManager = GridLayoutManager(requireContext(), 3)
        databinding?.recyclerPhotos?.adapter = adapter

        viewModel?.setRouter(this)

        username?.let {
            viewModel?.getUser(it)

            Handler(Looper.getMainLooper()).postDelayed(
                { viewModel?.getPhotoList(it, page = 1) },
                300
            )
            databinding?.recyclerPhotos?.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && page != 0) {
                        viewModel?.getPhotoList(it, ++page, pagePerCount)
                    }
                }
            })
        }


    }

    override fun onBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onSuccessGetUser(user: UserBean?) {
        databinding?.controller?.user = user
    }

    override fun setLoading(b: Boolean) {
        databinding?.progressLoad?.visibility = if (b) View.VISIBLE else View.GONE
    }

    override fun onSuccess(response: List<PhotosBean>) {
        if (page == 1)
            adapter.setList(response.toMutableList())
        else adapter.addItems(response)
    }

    override fun onError(errorMsg: String?) {
        Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_SHORT).show()
    }

    override fun setController(dataBinding: ViewDataBinding?) {
        val controller = PhotoItemUIController(this)
        (dataBinding as ItemUserPhotoBinding).controller = controller
    }

    override fun bindItem(dataBinding: ViewDataBinding?, item: PhotosBean) {
        (dataBinding as ItemUserPhotoBinding).controller?.bean = item
        dataBinding.executePendingBindings()
    }
}