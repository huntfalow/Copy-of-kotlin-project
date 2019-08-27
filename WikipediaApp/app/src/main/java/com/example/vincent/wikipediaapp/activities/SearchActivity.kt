package com.example.vincent.wikipediaapp.activities

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.WikiApp
import com.example.vincent.wikipediaapp.adapters.ArticleListItemRecyclerAdapter
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.models.WikiResult
import com.example.vincent.wikipediaapp.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.activity_search.*

/**
 *
 * This activity its purpose is to display display the items a user searched for and handle the interaction with the user
 *
 * @property wikiManager manager used for searching for articles
 * @property adapter used to fill up the recyclerview

 */

class SearchActivity : AppCompatActivity() {

    private var wikiManager: WikiManager? = null
    private var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    /**
     * During onCreate of the activity we fill up the recycler.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        wikiManager = (applicationContext as WikiApp).wikiManager

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        search_results_recycler.layoutManager = LinearLayoutManager(this)
        search_results_recycler.adapter = adapter
    }

    /**
     * In this function we check if home is selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    /**
     * In this function we inflate the search menu and we search for specific articles
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                // do the search and update the elements
                wikiManager?.search(query, 0, 20, { wikiResult ->
                    adapter.currentResults.clear()
                    adapter.currentResults.addAll(wikiResult.query!!.pages)
                    runOnUiThread { adapter.notifyDataSetChanged() }
                })
                println("updated search")

                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }
}
