package com.ddd.twinkle.safehero.monitoring

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import com.google.android.material.tabs.TabLayout





class SearchActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    lateinit var searchManager: SearchManager
    lateinit var searchMenuItem: MenuItem
    lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        addTabItem()



    }

    private fun addTabItem() {
        val tabLayout = findViewById<TabLayout>(R.id.layout_tab)
        tabLayout.addTab(tabLayout.newTab().setText("TAB-3"))
        tabLayout.addTab(tabLayout.newTab().setText("TAB-4"))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater.apply {
            this.inflate(com.ddd.twinkle.safehero.R.menu.search_menu, menu)
        }

        searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchMenuItem = menu.findItem(com.ddd.twinkle.safehero.R.id.search)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
