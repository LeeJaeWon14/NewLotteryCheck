package com.jeepchief.newlotterycheck.util

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.jsoup.HttpStatusException

object CrawlingManager {
    suspend fun scanNumbers(param: String) : List<List<Int>>? {
        var document: Document? = null
        var elements: Elements? = null
        Log.e("param is >> $param")
        try {
            document = Jsoup.connect("https://m.dhlottery.co.kr/qr.do?method=winQr&v${param}").get()
//            document = Jsoup.connect("https://www.naver.com").get()
            document?.let {
                elements = it.select(".clr")
                Log.e("elements >> ${elements?.text()}")
            } ?: run {
                return null
            }
        } catch (httpException: HttpStatusException) {
            Log.e("http exception!! ${httpException.message}")
        } catch(e: Exception) { e.printStackTrace() }
        val resultList: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())
        elements?.text()?.split(" ")?.run {
            var count = 0
            var index = 0
            for(i in 7 until size) {
                count ++
                if(count == 7) {
                    count = 1
                    index ++
                    resultList.add(mutableListOf())
                }
                resultList[index].add(get(i).toInt())
            }
        }
        resultList.forEach { numList ->
            Log.e(numList.toString())
        }
        return resultList
    }

    fun getMyNumbers(url: String) {

    }
}