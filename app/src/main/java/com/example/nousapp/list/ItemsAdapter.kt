package com.example.nousapp.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nousapp.R
import com.example.nousapp.data.model.Item
import com.leodroidcoder.genericadapter.BaseViewHolder
import com.leodroidcoder.genericadapter.GenericRecyclerViewAdapter
import com.leodroidcoder.genericadapter.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.item_item.view.*

class ItemsAdapter(context: Context?, listener: NousRecyclerItemClickListener?) :
    GenericRecyclerViewAdapter<
            Item,
            ItemsAdapter.NousRecyclerItemClickListener,
            ItemsAdapter.ItemViewHolder>(context, listener) {

    private var context: Context? = null

    init {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflate(R.layout.item_item, parent), listener)
    }

    inner class ItemViewHolder(
        itemView: View,
        listener: NousRecyclerItemClickListener?
    ) : BaseViewHolder<Item, NousRecyclerItemClickListener>(itemView, listener),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onBind(item: Item?) {
            context?.let {
                Glide.with(it).load(item?.imageUrl)
                    .into(itemView.itemPhoto)
            }

        }

        override fun onClick(v: View?) {
            listener.onItemClick(getItem(adapterPosition))
        }

    }

    interface NousRecyclerItemClickListener : OnRecyclerItemClickListener {
        fun onItemClick(item: Item?)
    }

}