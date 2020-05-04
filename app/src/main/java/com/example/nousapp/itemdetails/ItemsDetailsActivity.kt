package com.example.nousapp.itemdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nousapp.R
import com.example.nousapp.data.model.Item

class ItemsDetailsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ITEM = "extraItem"

        fun newIntent(context: Context?, item: Item?): Intent {
            val intent = Intent(context, ItemsDetailsActivity::class.java)
            intent.putExtra(EXTRA_ITEM, item)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.itemDetailsFrameLayout,
                ItemDetailsFragment.newInstance(
                    intent.getSerializableExtra(EXTRA_ITEM) as Item?
                ), ItemDetailsFragment::class.java.name
            )
            .commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}