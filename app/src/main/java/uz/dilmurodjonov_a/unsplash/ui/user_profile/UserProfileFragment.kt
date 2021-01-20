package uz.dilmurodjonov_a.unsplash.ui.user_profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import uz.dilmurodjonov_a.unsplash.R
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


    override val layoutId: Int = R.layout.fragment_user_profile
    override val viewModelClass: Class<UserProfileViewModel> = UserProfileViewModel::class.java
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString(USERNAME_KEY)
        adapter = RecyclerAdapter(R.layout.item_user_photo, this)

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
            page = 1
            viewModel?.getUser(it)

            databinding?.nestedScroll?.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener
                { v, _, scrollY, _, _ ->
                    Log.d(
                        "Hello",
                        "onViewCreated: $scrollY - ${databinding?.nestedScroll?.height?.minus(60)}"
                    )

                    if (scrollY >= (databinding?.textFollows?.y?.toInt() ?: 0)) {
                        databinding?.titleTextName?.visibility = View.VISIBLE
                        databinding?.imageNotification?.visibility = View.VISIBLE
                    } else {
                        databinding?.titleTextName?.visibility = View.INVISIBLE
                        databinding?.imageNotification?.visibility = View.INVISIBLE
                    }

                    if (scrollY >= Math.abs(v.measuredHeight - v.getChildAt(0).measuredHeight)
                            .minus(120)
                        && databinding?.progressLoad?.visibility != View.VISIBLE
                        && page != 0
                    ) {
                        viewModel?.getPhotoList(it, ++page, pagePerCount)
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
        page--
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