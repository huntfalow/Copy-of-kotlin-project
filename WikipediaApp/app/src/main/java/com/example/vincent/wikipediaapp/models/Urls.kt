package com.example.vincent.wikipediaapp.models
/**
 *
 * This object is to store the url's needed to use the wikipedia api
 *
 * @property BaseUrl the basic url we use to use the api

 */
object Urls {
    val BaseUrl = "https://nl.wikipedia.org/w/api.php"
    /**
     * In this function we return the url to search an article
     */
    fun getSearchUrl(term: String, skip: Int, take: Int) : String{
        return BaseUrl + "?action=query" +
                "&formatversion=2" +
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsoffset=$skip" +
                "&prop=pageimages|info" +
                "&piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbptterms=description" +
                "&format=json" +
                "&inprop=url"
    }
    /**
     * In this function we return the url to get random articles
     */
    fun getRandomUrl(take: Int) : String{
        return BaseUrl + "?action=query" +
                "&format=json" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnnamespace=0" +
                "&prop=pageimages|info" +
                "&grnlimit=$take" +
                "&inprop=url" +
                "&pithumbsize=200"
    }
}