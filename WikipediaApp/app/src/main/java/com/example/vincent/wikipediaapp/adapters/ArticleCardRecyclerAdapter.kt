package com.example.vincent.wikipediaapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.holders.CardHolder
import com.example.vincent.wikipediaapp.models.WikiePage

/**
 *
 * This adapter is used for the Recyclerview of the articles when you are on the explore page or on the favorites page
 *
 * @property currentResults is a list of the wikipages that wil be displayed


*/
class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {
    val currentResults: ArrayList<WikiePage> = ArrayList<WikiePage>()

    /**
     * In this function we get the size of the list of the wikipages
     */
    override fun getItemCount(): Int {
        return currentResults.size
    }
    /**
     * In this function we will update our view
     */
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }
    /**
     * In this function we get a carditem
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }
}