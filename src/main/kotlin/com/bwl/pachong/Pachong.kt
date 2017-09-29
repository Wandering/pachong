package com.bwl.pachong

import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.annotation.*
import com.geccocrawler.gecco.spider.HtmlBean


/**
 * Created by Administrator on 2017/8/23.
 */
@Gecco(matchUrl= arrayOf("http://lib.cqvip.com/qk/{pageNo}/{time}"), pipelines= arrayOf("consolePipeline"))
class MyGithub : HtmlBean {


    @RequestParameter("pageNo")
    var pageNo: String? = null//url中的{project}值

    @Text
    @HtmlField(cssPath = ".lastyears")
    var catalog: String? = null//抽取页面中的title

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(2) .social-count")
    var star: Int = 0//抽取页面中的star

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(3) .social-count")
    var fork: Int = 0//抽取页面中的fork

    @Html
    @HtmlField(cssPath = ".entry-content")
    var readme: String? = null//抽取页面中的readme

    companion object {

        private val serialVersionUID = -7127412585200687225L

        @JvmStatic
        fun main(args: Array<String>) {
            GeccoEngine.create()
                    //工程的包路径
                    .classpath("com.geccocrawler.gecco.demo")
                    //开始抓取的页面地址
                    .start("https://github.com/xtuhcy/gecco")
                    //开启几个爬虫线程
                    .thread(1)
                    //单个爬虫每次抓取完一个请求后的间隔时间
                    .interval(2000)
                    //循环抓取
                    .loop(true)
                    //使用pc端userAgent
                    .mobile(false)
                    //非阻塞方式运行
                    .start()
        }
    }
}