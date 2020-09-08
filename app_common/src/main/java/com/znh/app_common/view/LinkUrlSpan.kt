package com.znh.app_common.view

import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by znh on 2016/7/11
 */
class LinkUrlSpan constructor(private var url: String, private var urlLinkClickListener: OnUrlLinkClickListener?) : ClickableSpan() {

    override fun onClick(view: View) {
        urlLinkClickListener?.onLinkClick(view, url)
    }
}