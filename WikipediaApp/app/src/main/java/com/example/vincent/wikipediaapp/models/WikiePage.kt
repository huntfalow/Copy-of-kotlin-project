package com.example.vincent.wikipediaapp.models
/**
 *
 * This class is a model for storing a wikipedia page
 *
 * @property pageid id of the page
 * @property title title of the page
 * @property fullurl url of the page
 * @property thumbnail thumbnail of the page
 */
class WikiePage {
    var pageid: Int? = null
    var title: String? = null
    var fullurl: String? = null
    var thumbnail: WikiThumbnail? = null

}