package com.znh.app_common.view

import android.graphics.Color
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.URLSpan
import android.text.util.Linkify
import android.widget.TextView
import java.util.*

/**
 * Created by znh on 2016/7/5
 */
class TVLinkHelper private constructor(builder: Builder) {

    //目标TextView控件
    private var textView: TextView? = builder.textView

    //TextView上显示的文本内容
    private var strContent: String? = builder.strContent

    //点击色
    private var highLightColor: Int = builder.highLightColor

    //替换后的文字颜色
    private var foregroundColor: Int = builder.foregroundColor

    //点击回调监听
    private var urlLinkClickListener: OnUrlLinkClickListener? = builder.urlLinkClickListener


    /**
     * 链接替换为文字
     */
    private fun replaceUrl(): TextView? {
        if (textView == null || TextUtils.isEmpty(strContent)) {
            return null
        }
        textView!!.autoLinkMask = Linkify.WEB_URLS
        textView!!.text = strContent
        val text = textView!!.text
        var textStr = text.toString()
        if (text is Spannable) {
            val end = text.length
            val sp = textView!!.text as Spannable
            val urls = sp.getSpans(0, end, URLSpan::class.java)
            val urlsSet = HashSet<String>()
            for (urlSpan in urls) {
                urlsSet.add(urlSpan.url)
            }
            for (url in urlsSet) {
                val str = "<a href=\"%s\">%s</a>"
                textStr = textStr.replace(url, String.format(str, url, "网络连接"))
            }
            textView!!.autoLinkMask = 0
            textView!!.text = Html.fromHtml(textStr)
        }
        return textView
    }

    /**
     * 替换链接并为替换后的文本设置颜色
     */
    fun link() {
        textView = replaceUrl()
        if (textView == null || TextUtils.isEmpty(strContent)) {
            return
        }
        val text = textView!!.text
        if (text is Spannable) {
            val end = text.length
            val sp = textView!!.text as Spannable
            val urls = sp.getSpans(0, end, URLSpan::class.java)
            val style = SpannableStringBuilder(text)
            style.clearSpans()
            for (url in urls) {
                val linkURLSpan = LinkUrlSpan(url.url, urlLinkClickListener)
                style.setSpan(linkURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                style.setSpan(ForegroundColorSpan(foregroundColor), sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            }
            textView!!.highlightColor = highLightColor
            textView!!.text = style
        }
    }

    class Builder() {
        internal var textView: TextView? = null

        internal var strContent: String? = null

        internal var highLightColor: Int = Color.BLACK

        internal var foregroundColor: Int = Color.BLACK

        internal var urlLinkClickListener: OnUrlLinkClickListener? = null

        fun builder(): TVLinkHelper {
            return TVLinkHelper(this)
        }

        fun textView(textView: TextView?): Builder {
            this.textView = textView
            return this
        }

        fun strContent(strContent: String?): Builder {
            this.strContent = strContent
            return this
        }

        fun highLightColor(highLightColor: Int): Builder {
            this.highLightColor = highLightColor
            return this
        }

        fun foregroundColor(foregroundColor: Int): Builder {
            this.foregroundColor = foregroundColor
            return this
        }

        fun urlLinkClickListener(urlLinkClickListener: OnUrlLinkClickListener?): Builder {
            this.urlLinkClickListener = urlLinkClickListener
            return this
        }
    }
}