package com.example.nousapp.itemlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nousapp.R
import com.example.nousapp.data.model.Item
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Status
import com.example.nousapp.itemdetails.ItemsDetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_items_list.*

class ItemsListFragment : Fragment() {

    private val NUMBER_OF_COL = 4
    private var itemsAdapter: ItemsAdapter? = null
    private lateinit var itemListViewModel: ItemListViewModel

    companion object {
        fun newInstance(): ItemsListFragment {
            val args: Bundle = Bundle()
            val fragment = ItemsListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemListViewModel = ViewModelProvider(this).get(ItemListViewModel::class.java)

        itemsAdapter = ItemsAdapter(activity, object : ItemsAdapter.NousRecyclerItemClickListener {
            override fun onItemClick(item: Item?) {
               //startActivity(ItemsDetailsActivity.newIntent(activity, item))
                itemListViewModel.getItems()
            }

            override fun onItemClick(position: Int) {
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items_list, container, false)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems.layoutManager = GridLayoutManager(context, NUMBER_OF_COL)
        rvItems.adapter = itemsAdapter

        itemListViewModel.itemsLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING ->
                    progressLoading?.visibility = View.VISIBLE

                Status.SUCCESS -> {
                    itemsAdapter?.items = it?.data?.items
                }

                Status.ERROR -> {
                    Snackbar.make(
                        rootLayout,
                        it.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                Status.COMPLETE -> {
                    progressLoading?.visibility = View.GONE
                }
            }
        })

        itemListViewModel.getItems()

    }
}