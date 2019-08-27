package com.example.vincent.wikipediaapp

import android.app.Application
import com.example.vincent.wikipediaapp.managers.WikiManager
import com.example.vincent.wikipediaapp.providers.ArticleDataProvider
import com.example.vincent.wikipediaapp.repositories.ArticleDatabase
import com.example.vincent.wikipediaapp.repositories.FavoritesRepository
import com.example.vincent.wikipediaapp.repositories.HistoryRepository
/**
 *
 * This class is used to create and declare all the needed classes in the app

 */
class WikiApp: Application() {
    private var dbHelper: ArticleDatabase? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set
    /**
     * In this function we declare all the needed classes in the app
     */
    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabase(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}