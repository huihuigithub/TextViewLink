package com.example.znh.texttestviewlink;

import android.content.Context;
import android.graphics.Canvas;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by ninghui on 2016/7/11.
 */
public class MyTextView extends TextView {
    private int lines = Integer.MAX_VALUE;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyMaxLines(int lines) {
        this.lines = lines;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        CharSequence charSequence = getText();
        if (lines < getLineCount()) {
            int lastCharDown = getLayout().getLineVisibleEnd(lines);
            Log.i("lan", lines + "");
            if (charSequence.length() > lastCharDown) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown)).append("...");
                setText(spannableStringBuilder);
            }
        }
        super.onDraw(canvas);
    }
}
