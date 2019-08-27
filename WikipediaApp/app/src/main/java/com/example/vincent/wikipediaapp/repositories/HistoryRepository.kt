package com.example.vincent.wikipediaapp.repositories

import com.example.vincent.wikipediaapp.models.WikiThumbnail
import com.example.vincent.wikipediaapp.models.WikiePage
import com.google.gson.Gson
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
/**
 *
 * This class is used as the repository for the history, all the functions needed for the histories are here

 */
class HistoryRepository(val databaseHelper: ArticleDatabase) {
    private val TABLE_NAME: String = "History"
    /**
     * In this function we add an article to the history table (SQLlite DB)
     */
    fun addFavorite(page: WikiePage){
        removePageById(page.pageid!!)
        databaseHelper.use {
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }
    /**
     * In this function we remove an article from the history table (SQLlite DB)
     */
    fun removePageById(pageId: Int){
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }
    /**
     * In this function we get all the articles from the history table (SQLlite DB)
     */
    fun getAllHistory() : ArrayList<WikiePage> {
        var pages = ArrayList<WikiePage>()

        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
            val page = WikiePage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }

        databaseHelper.use {
            select(TABLE_NAME).parseList(articleRowParser)
        }
        return pages
    }
}