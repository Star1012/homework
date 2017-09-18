package com.example.administrator.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView tvAboutVerInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvAboutVerInfo=(TextView) findViewById(R.id.tvAboutVerInfo);
        tvAboutVerInfo.setText("版本信息：version1.0.1\n数据库：SQLite\n语言：Java\n" +
                "操作系统：Android\n作者：黄星\n班级：信1403-2\n联系方式：15232159886");
    }

    // 返回按钮单击
    public void btnAboutClose_Click(View view)
    {
        finish();
    }
}
