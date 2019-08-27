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
 * This class is used as the repository for the favorites, all the functions needed for the favorites are here

 */
class FavoritesRepository(val databaseHelper: ArticleDatabase) {
    private val TABLE_NAME: String = "Favorites"
    /**
     * In this function we add an article to the favorites table (SQLlite DB)
     */
    fun addFavorite(page: WikiePage){
        databaseHelper.use {
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))
        }
    }
    /**
     * In this function we remove an article from the favorites table (SQLlite DB)
     */
    fun removeFavoriteById(pageId: Int){
        databaseHelper.use {
            delete(TABLE_NAME, "id = {pageId}", "pageId" to pageId)
        }
    }
    fun isArticleFavorite(pageId: Int) : Boolean{
        // get favorites and filter
        var pages = getAllFavorites()
        return pages.any { page ->
            page.pageid == pageId
        }
    }
    /**
     * In this function we get all the articles from the favorites table (SQLlite DB)
     */
    fun getAllFavorites() : ArrayList<WikiePage> {
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