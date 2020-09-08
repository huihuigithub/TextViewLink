package com.znh.app_common.view

import android.content.Context
import android.graphics.Canvas
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by znh on 2016/7/11
 *
 * 可以自定义省略号的TextView
 */
class EllipsisTextView : AppCompatTextView {

    //能展示的最大行数
    private var ellipsisMaxLines: Int = Int.MAX_VALUE

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setEllipsisMaxLines(ellipsisMaxLines: Int) {
        this.ellipsisMaxLines = ellipsisMaxLines
    }

    override fun onDraw(canvas: Canvas) {
        val charSequence = text
        if (ellipsisMaxLines < lineCount) {
            val lastCharDown = layout.getLineVisibleEnd(ellipsisMaxLines - 1)
            if (charSequence.length > lastCharDown) {
                val spannableStringBuilder = SpannableStringBuilder().apply {
                    append(charSequence.subSequence(0, lastCharDown)).append("...")
                }
                text = spannableStringBuilder
            }
        }
        super.onDraw(canvas)
    }
}