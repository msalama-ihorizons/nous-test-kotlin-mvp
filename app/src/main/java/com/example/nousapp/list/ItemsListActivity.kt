package com.example.nousapp.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nousapp.R

class ItemsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_list)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.itemsFrameLayout,
                ItemsListFragment.newInstance(), ItemsListFragment::class.java.name
            )
            .commit()

    }
}