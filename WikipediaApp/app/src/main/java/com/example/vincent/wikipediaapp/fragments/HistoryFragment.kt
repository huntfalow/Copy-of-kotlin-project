package com.example.vincent.wikipediaapp.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.WikiApp
import com.example.vincent.wikipediaapp.adapters.ArticleListItemRecyclerAdapter
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.models.WikiePage
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
/**
 *
 * This fragment its purpose is to display display everything on the favorites page
 *
 * @property wikiManager used for getting the history of the user
 * @property historyRecycler used for displaying the articles on the page
 * @property adapter used for filling up the recyclerview
 */
class HistoryFragment : Fragment() {


    private var wikiManager: WikiManager? = null
    var historyRecycler: RecyclerView? = null
    private val adapter = ArticleListItemRecyclerAdapter()

    init{
        setHasOptionsMenu(true);
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity?.applicationContext as WikiApp).wikiManager
    }
    /**
     * This function is used for creating the view and showing articles from the user's history
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById<RecyclerView>(R.id.history_article_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter

        return view
    }
    /**
     * This function is used for getting the articles from the user's history
     */
    override fun onResume(){
        super.onResume()

        doAsync {
            val history = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(history as ArrayList<WikiePage>)
            activity?.runOnUiThread{ adapter.notifyDataSetChanged() }
        }
    }
    /**
     * This function is used for inflating the history menu
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater!!.inflate(R.menu.history_menu, menu)
    }
    /**
     * This function is used for clearing the history
     */

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_clear_history){
            // show confirmation alert
            activity?.alert("Are you sure you want to clear your history?", "Confirm"){
                yesButton {
                    // yes was hit...
                    // clear history async
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager?.clearHistory()
                    }
                    activity?.runOnUiThread{ adapter.notifyDataSetChanged() }
                }
                noButton {
                    // You can put something here but we don't need it

                }
            }?.show()
        }

        return true
    }



}
