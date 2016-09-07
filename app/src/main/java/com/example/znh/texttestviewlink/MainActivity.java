package com.example.znh.texttestviewlink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private MyTextView myTextView;
    private TextViewLinkUtils mTextViewLinkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = (MyTextView) findViewById(R.id.text_view);

        myTextView.setMyMaxLines(2);

        String str = "http://www.baidu.com哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";

        mTextViewLinkUtils = TextViewLinkUtils.getInstance();
        mTextViewLinkUtils.setLink(myTextView, str, Color.YELLOW, Color.BLUE, new TextViewLinkUtils.OnUrlLinkClickListener() {
            @Override
            public void onLinkClick(View view, String url) {
                Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}

