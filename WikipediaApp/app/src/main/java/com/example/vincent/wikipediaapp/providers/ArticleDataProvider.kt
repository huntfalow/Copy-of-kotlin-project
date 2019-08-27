package com.example.vincent.wikipediaapp.providers

import com.example.vincent.wikipediaapp.models.Urls
import com.example.vincent.wikipediaapp.models.WikiResult
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import java.io.Reader
/**
 *
 * This class is used providing all the data
 */


class ArticleDataProvider {
    /**
     * In this function we let the wikipedia api know who we are
     */
    init{
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "hogeschool Wikipedia")
    }
    /**
     * In this function we get the data when we search for an article
     */
    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Urls.getSearchUrl(term, skip, take).httpGet()
            .responseObject(WikipediaDataDeserializer()){ _, response, result ->

                if(response.statusCode != 200){
                    throw Exception("Unable to get articles")
                }
                val(data, _) = result
                responseHandler.invoke(data as WikiResult)

            }
    }
    /**
     * In this function we get the data when we want random articles
     */
    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?){
        Urls.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataDeserializer()){ _, response, result ->

                if(response.statusCode != 200){
                }else{

                val(data, _) = result
                responseHandler.invoke(data as WikiResult)
                }
            }
    }
    /**
     * In this function we deserialize the data
     */
    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)
    }
}