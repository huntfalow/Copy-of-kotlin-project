package com.example.vincent.wikipediaapp.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.activities.ArticleDetailActivity
import com.example.vincent.wikipediaapp.models.WikiePage
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card_item.view.*
/**
 *
 * This class is used to define everything needed for a Card
 *
 * @property articleImageView image of the article
 * @property titleTextView title of the article
 * @property currentPage the current page selected by the user
 */

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage: WikiePage? = null
    /**
     * In this function we open the ArticleDetailActivity when you click on an article
     */
    init{
        itemView.setOnClickListener { view: View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }
    /**
     * In this function we update the current page the user is on
     */
    fun updateWithPage(page: WikiePage){
        currentPage = page

        titleTextView.text = page.title

        // load image lazily with picasso
        if(page.thumbnail != null)
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
    }

}