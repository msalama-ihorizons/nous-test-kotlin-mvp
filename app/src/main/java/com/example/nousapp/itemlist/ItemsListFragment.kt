package com.example.nousapp.itemlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nousapp.R
import com.example.nousapp.data.model.Item
import com.example.nousapp.data.model.NousResponse
import com.example.nousapp.data.model.Status
import com.example.nousapp.itemdetails.ItemsDetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_items_list.*

private const val TAG = "ItemsListFragment"

class ItemsListFragment : Fragment() {

    private val NUMBER_OF_COL = 4
    private var itemsAdapter: ItemsAdapter? = null
    //private lateinit var itemListViewModel: ItemListViewModel
    private val itemListViewModel: ItemListViewModel by activityViewModels()

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


        itemsAdapter = ItemsAdapter(activity, object : ItemsAdapter.NousRecyclerItemClickListener {
            override fun onItemClick(item: Item?) {
               //startActivity(ItemsDetailsActivity.newIntent(activity, item))
               // itemListViewModel.refresh()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //itemListViewModel = activity?.let { ViewModelProvider(it).get(ItemListViewModel::class.java) }?: throw Exception("Invalid Activity")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems.layoutManager = LinearLayoutManager(context)
        rvItems.adapter = itemsAdapter

        itemListViewModel.getItemsLiveData().observe(viewLifecycleOwner, Observer {

            Log.i(TAG, "Recive From View Model")
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

    }
}