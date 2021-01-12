package uz.dilmurodjonov_a.unsplash.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Abbos Dilmurodjonov (AyDee) on 11.01.2021.
 */
class RecyclerAdapter<T>(val layoutId: Int, val listener: AdapterListener<T>) :
    RecyclerView.Adapter<RecyclerAdapter<T>.ViewHolder?>() {

    private var list: MutableList<T>? = null

    fun setList(list: MutableList<T>?) {
        if (this.list != null)
            notifyItemRangeRemoved(0, this.list!!.size)

        this.list = list
        notifyItemRangeInserted(0, list!!.size)
    }

    fun addItems(list: List<T>) {
        if (this.list != null) {
            val position = this.list!!.size
            this.list!!.addAll(list)
            notifyItemRangeInserted(position, list.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil
            .inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    inner class ViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(item: T) {
            listener.bindItem(dataBinding, item)
        }

        init {
            listener.setController(dataBinding)
        }
    }

    interface AdapterListener<T> {
        fun setController(dataBinding: ViewDataBinding?)
        fun bindItem(dataBinding: ViewDataBinding?, item: T)
    }


}
