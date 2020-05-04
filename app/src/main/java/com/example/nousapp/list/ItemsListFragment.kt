package com.example.nousapp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nousapp.R
import com.example.nousapp.data.model.Item
import com.example.nousapp.details.ItemsDetailsActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_items_list.*

class ItemsListFragment: Fragment(), ItemsListContract.View {

    private val NUMBER_OF_COL = 4
    private var itemsListPresenter: ItemsListPresenter? = null
    private var itemsAdapter: ItemsAdapter? = null

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

        itemsListPresenter = ItemsListPresenter(this, ItemsListFetcher())

        itemsAdapter = ItemsAdapter(activity, object : ItemsAdapter.NousRecyclerItemClickListener {
            override fun onItemClick(item: Item?) {
                startActivity(ItemsDetailsActivity.newIntent(activity, item))
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems.layoutManager = GridLayoutManager(context, NUMBER_OF_COL)
        rvItems.adapter = itemsAdapter

        itemsListPresenter?.loadItems()
    }


    override fun showItemsList(items: List<Item>?) {
        items?.let {
            itemsAdapter?.items = items
        }
    }

    override fun hideProgress() {
        progressLoading.visibility= View.GONE
    }

    override fun showProgress() {
        progressLoading.visibility= View.VISIBLE
    }

    override fun showFailureMessage(errorMessage: String?) {
        if (errorMessage != null) {
            Snackbar.make(
                rootLayout,
                errorMessage,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}