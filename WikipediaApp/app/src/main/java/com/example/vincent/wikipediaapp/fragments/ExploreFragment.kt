package com.example.vincent.wikipediaapp.fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.WikiApp
import com.example.vincent.wikipediaapp.activities.SearchActivity
import com.example.vincent.wikipediaapp.adapters.ArticleCardRecyclerAdapter
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.models.WikiResult
import com.example.vincent.wikipediaapp.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explore.*

/**
 *
 * This fragment its purpose is to display display everything on the explore page
 *
 * @property wikiManager used for getting random articles on the page
 * @property searchCardView used for starting the search activity
 * @property exploreRecycler used for displaying the articles on the page
 * @property refresher used for refreshing the page
 * @property adapter used for filling up the recyclerview

 */

class ExploreFragment : Fragment() {
    private var wikiManager: WikiManager? = null
    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var refresher: SwipeRefreshLayout? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiApp).wikiManager
    }

    /**
     * This function is used for creating the view and showing the random articles
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }

        getRandomArticles()

        return view
    }
    /**
     * This function is used for getting random articles
     */
    private fun getRandomArticles(){
        refresher?.isRefreshing = true

        try{
            wikiManager?.getRandom(15) { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher?.isRefreshing = false
                }
            }
        }
        catch (ex :Exception){
            // show alert
            val builder = AlertDialog.Builder(activity as Context)
            builder.setMessage(ex.message).setTitle("oops!")
            val dialog = builder.create()
            dialog.show()
        }
    }
    }




