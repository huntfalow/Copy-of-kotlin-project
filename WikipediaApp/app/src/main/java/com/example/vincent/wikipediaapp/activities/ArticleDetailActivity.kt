package com.example.vincent.wikipediaapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.vincent.wikipediaapp.R
import com.example.vincent.wikipediaapp.WikiApp
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.models.WikiePage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast

/**
 *
 * This fragment its purpose is to display display details about an article
 *
 * @property currentPage the current page selected by the user
 * @property wikiManager manager used for adding article to history and possibly to favorites or removing it from favorites
 */

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage: WikiePage? = null
    private var wikiManager: WikiManager? = null

    /**
     * During the onCreate of the activity, we set the SupportActionBar, we get the currentpage selected and we add this page to the history
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApp).wikiManager


        setSupportActionBar(toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        // get the page from the extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiePage>(wikiPageJson, WikiePage::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

        }

        article_detail_webview.loadUrl(currentPage!!.fullurl)

        wikiManager?.addHistory(currentPage!!)

    }
    /**
     * In this function the menu of the article details page gets inflated
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * This function adds and removes an article from the favorites
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        else if (item!!.itemId == R.id.action_favorite){
            try {
                // determine if article is already a favorite or not
                if(wikiManager!!.getIsFavorite(currentPage!!.pageid!!)){
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                }
                else{
                    wikiManager!!.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }
            }
            catch (ex: Exception){
                toast("Unable to update this article.")
            }
        }
        return true;
    }
}
