package com.example.vincent.wikipediaapp.managers

import com.example.vincent.wikipediaapp.models.WikiResult
import com.example.vincent.wikipediaapp.models.WikiePage
import com.example.vincent.wikipediaapp.providers.ArticleDataProvider
import com.example.vincent.wikipediaapp.repositories.FavoritesRepository
import com.example.vincent.wikipediaapp.repositories.HistoryRepository
/**
 *
 * This class is used as the manager, this manages al out repositories and al it's functions and the DataProvider
 *
 * @property provider image of the article
 * @property favoritesRepository repository of all the favorites
 * @property historyRepository repository of the history
 */
class WikiManager(private val provider: ArticleDataProvider,
                  private val favoritesRepository: FavoritesRepository,
                  private val historyRepository: HistoryRepository) {
    private var favoritesCache: ArrayList<WikiePage>? = null
    private var historyCache: ArrayList<WikiePage>? = null
    /**
     * In this function we search for an article
     */
    fun search(term: String, skip: Int, take: Int, handler: (result: WikiResult) -> Unit?){
        return provider.search(term, skip, take, handler)
    }
    /**
     * In this function we get random articles
     */
    fun getRandom(take: Int, handler: (result: WikiResult) -> Unit?) {
        return provider.getRandom(take, handler)
    }
    /**
     * In this function we get the history of a user
     */
    fun getHistory(): ArrayList<WikiePage>?{
        if(historyCache == null)
            historyCache = historyRepository.getAllHistory()
        return historyCache
    }
    /**
     * In this function we get the favorites of a user
     */
    fun getFavorites(): ArrayList<WikiePage>? {
        if(favoritesCache == null)
            favoritesCache = favoritesRepository.getAllFavorites()
        return favoritesCache
    }
    /**
     * In this function we add an article to the favorites list
     */
    fun addFavorite(page: WikiePage){
        favoritesCache?.add(page)
        favoritesRepository.addFavorite(page)
    }
    /**
     * In this function we remove a favorite from the favorites list
     */
    fun removeFavorite(pageId: Int){
        favoritesRepository.removeFavoriteById(pageId)
        favoritesCache = favoritesCache!!.filter { it.pageid != pageId } as ArrayList<WikiePage>
    }
    /**
     * In this function we get a favorite from the favorites list
     */
    fun getIsFavorite(pageId: Int): Boolean{
        return favoritesRepository.isArticleFavorite(pageId)
    }
    /**
     * In this function we add an article to the history list
     */
    fun addHistory(page: WikiePage){
        historyCache?.add(page)
        historyRepository.addFavorite(page)
    }
    /**
     * In this function we clear the history
     */
    fun clearHistory() {
        historyCache?.clear()
        val allHistory = historyRepository.getAllHistory()
        allHistory?.forEach { historyRepository.removePageById(it.pageid!!) }
    }
}