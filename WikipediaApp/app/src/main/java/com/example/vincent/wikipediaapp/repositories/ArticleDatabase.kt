package com.example.vincent.wikipediaapp.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
/**
 *
 * This class is used to create the SQLiteDatabaese and it's tables

 */
class ArticleDatabase(context: Context) : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db", null, 1) {

    /**
     * In this function we create the tables needed for out favorites an history
     */
    override fun onCreate(db: SQLiteDatabase?) {
        // Defining tables in this database
        db?.createTable("Favorites", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)

        db?.createTable("History", true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailJson" to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Use to upgrade the schema if needed
    }
}