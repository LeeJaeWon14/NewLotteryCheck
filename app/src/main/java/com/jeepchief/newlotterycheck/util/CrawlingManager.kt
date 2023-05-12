package com.jeepchief.newlotterycheck.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.jsoup.HttpStatusException

object CrawlingManager {
    fun getElements(url: String) : Elements? {
        var document: Document? = null
        var elements: Elements? = null
        try {
            document = Jsoup.connect(url).get()
            document?.let {
                elements = document.select("meta[property^=og:]")
            } ?: run {
                return null
            }
        } catch (httpException: HttpStatusException) {
            Log.e("http exception!! ${httpException.message}")
        } catch(e: Exception) { e.printStackTrace() }
        return elements
    }

    fun getMyNumbers(url: String) {

    }
}