package com.example.vincent.wikipediaapp.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.WikiApp
import com.example.vincent.wikipediaapp.adapters.ArticleCardRecyclerAdapter
import com.example.vincent.wikipediaapp.adapters.ArticleListItemRecyclerAdapter
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.models.WikiePage
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.doAsync
/**
 *
 * This fragment its purpose is to display display everything on the favorites page
 *
 * @property wikiManager used for displaying random articles on the page
 * @property favoritesRecyler used for getting the favorites
 * @property adapter used for filling up the recyclerview
 */

class FavoritesFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    var favoritesRecyler: RecyclerView? = null
    private val adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApp).wikiManager
    }

    /**
     * This function is used for creating the view and showing favorite articles
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecyler = view.findViewById<RecyclerView>(R.id.favorties_article_recycler);
        favoritesRecyler!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        favoritesRecyler!!.adapter = adapter


        return view
    }
    /**
     * This function is used for getting the favorite articles of the user
     */
    override fun onResume(){
        super.onResume()

        doAsync {
            val favoriteArticles = wikiManager!!.getFavorites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiePage>)
            activity?.runOnUiThread{ adapter.notifyDataSetChanged() }
        }
    }
}
