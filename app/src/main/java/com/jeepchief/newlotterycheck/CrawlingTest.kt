package com.jeepchief.newlotterycheck

import com.jeepchief.newlotterycheck.util.Log
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements



fun main() {
    var document: Document? = null
    var elements: Elements? = null
    try {
        println("crawling start")
        var param = "=0868m041120213645m010306132438m142933354042m021823262731m1217284143441293818248"
//        https://m.dhlottery.co.kr/qr.do?method=winQr&v=0868m041120213645m010306132438m142933354042m021823262731m1217284143441293818248
//        document = Jsoup.connect("https://m.dhlottery.co.kr/qr.do?method=winQr&v=0868m041120213645m010306132438m142933354042m021823262731m1217284143441293818248").get()
        document = Jsoup.connect("https://m.dhlottery.co.kr/qr.do?method=winQr&v${param}").get()
//        document = Jsoup.connect("https://www.naver.com").get()
        println(document?.getElementsByClass("clr")?.size)
        println(document?.select(".clr")?.size)

        document?.let {
            elements = it.select(".clr")
            println(elements?.text())
        } ?: run {

        }
    } catch (httpException: HttpStatusException) {
        Log.e("http exception!! ${httpException.message}")
    } catch(e: Exception) { e.printStackTrace() }
}
