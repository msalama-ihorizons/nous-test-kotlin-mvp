package com.example.nousapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nousapp.R
import com.example.nousapp.data.model.Item
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.item_item.view.*

class ItemDetailsFragment : Fragment() {

    private var item: Item? = null

    companion object {

        private const val EXTRA_ITEM = "extraItem"

        fun newInstance(item: Item?): ItemDetailsFragment {
            val args: Bundle = Bundle()
            val fragment = ItemDetailsFragment()
            args.putSerializable(EXTRA_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getSerializable(EXTRA_ITEM)?.let {
            item = it as Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle.text = item?.title
        tvDesc.text = item?.description

        activity?.let {
            Glide.with(it).load(item?.imageUrl)
                .into(itemPhoto)
        }
    }
}