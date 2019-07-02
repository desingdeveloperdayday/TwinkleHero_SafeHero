package com.ddd.twinkle.safehero.emergency

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R


class SearchActivity : AppCompatActivity(),SearchView.OnQueryTextListener {

    lateinit var searchManager: SearchManager
    lateinit var searchMenuItem: MenuItem
    lateinit var searchView : SearchView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

   /* private fun createTabView(tabName : String) : View{
        val tabView = LayoutInflater.from(applicationContext).inflate(R.layout.custom_tab,null)
        text_tab_name.text = tabName
        return tabView
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater : MenuInflater = menuInflater.apply {
            this.inflate(R.menu.search_menu,menu)
        }

        searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchMenuItem = menu.findItem(R.id.search)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled= true
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
