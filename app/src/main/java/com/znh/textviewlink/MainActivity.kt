package com.znh.textviewlink

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.znh.app_common.view.OnUrlLinkClickListener
import com.znh.app_common.view.TVLinkHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by znh on 2016/7/11
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置最大展示行数为8行
        text_view.setEllipsisMaxLines(8)

        //展示的文本内容
        val str = "http://www.123.com哈哈哈哈哈哈哈哈哈是伐啦设计风框架按时来哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈" +
                "哈哈哈哈哈哈哈哈哈哈哈哈关机算法了哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈框架健康得及哈哈哈哈" +
                "哈哈http://www.456.com哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈" +
                "哈哈哈哈http://www.789.com了发上哈哈哈哈哈哈了框架按时和点击跟领导科技高峰alkbjslkjflkjslfjkslfj的反馈" +
                "给聚技哈哈哈哈哈哈来开框架按时"

        //处理链接
        TVLinkHelper.Builder()
                .textView(text_view)
                .foregroundColor(Color.BLUE)
                .highLightColor(Color.YELLOW)
                .strContent(str)
                .urlLinkClickListener(object : OnUrlLinkClickListener {
                    override fun onLinkClick(view: View?, url: String?) {
                        Toast.makeText(this@MainActivity, url, Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        intent.putExtra("url_data", "跳转到了${url}页面")
                        startActivity(intent)
                    }
                })
                .builder()
                .link()
    }
}