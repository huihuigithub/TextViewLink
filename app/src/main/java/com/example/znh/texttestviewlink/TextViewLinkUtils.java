package com.example.znh.texttestviewlink;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ninghui on 2016/7/5.
 */
public class TextViewLinkUtils {
    private static TextViewLinkUtils mTextViewLinkUtils;
    private OnUrlLinkClickListener mUrlLinkClickListener;

    private TextViewLinkUtils() {
    }

    public static synchronized TextViewLinkUtils getInstance() {
        if (mTextViewLinkUtils == null) {
            mTextViewLinkUtils = new TextViewLinkUtils();
        }
        return mTextViewLinkUtils;
    }

    /**
     * 链接替换为文字
     *
     * @param textView
     * @param strContent
     * @return
     */
    public TextView replaceUrl(TextView textView, String strContent) {
        textView.setAutoLinkMask(Linkify.WEB_URLS);
        textView.setText(strContent);
        CharSequence text = textView.getText();
        String textStr = text.toString();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) textView.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            HashSet<String> urlsSet = new HashSet<>();
            for (int i = 0; i < urls.length; i++) {
                urlsSet.add(urls[i].getURL());
            }
            Iterator<String> stringIterator = urlsSet.iterator();
            while (stringIterator.hasNext()) {
                String str = "<a href=\"%s\">%s</a>";
                String url = stringIterator.next();
                textStr = textStr.replace(url, String.format(str, url, "网络连接"));
            }
            textView.setAutoLinkMask(0);
            textView.setText(Html.fromHtml(textStr));
        }
        return textView;
    }

    public void setLink(TextView textView, String strContent, int highLightColor, int foregroundColor, OnUrlLinkClickListener urlLinkClickListener) {
        this.mUrlLinkClickListener = urlLinkClickListener;
        textView = replaceUrl(textView, strContent);
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) textView.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();
            for (URLSpan url : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                style.setSpan(new ForegroundColorSpan(foregroundColor), sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setHighlightColor(highLightColor);
            textView.setText(style);
        }
    }

    /**
     * 自己处理链接点击
     */
    private class MyURLSpan extends ClickableSpan {
        private String mUrl;

        MyURLSpan(String url) {
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            if (mUrlLinkClickListener != null) {
                mUrlLinkClickListener.onLinkClick(widget, mUrl);
            }
        }
    }

    public interface OnUrlLinkClickListener {
        void onLinkClick(View view, String url);
    }
}
