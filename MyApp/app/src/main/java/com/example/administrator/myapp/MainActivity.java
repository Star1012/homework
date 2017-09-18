package com.example.administrator.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.DBConn.MyDBHelper;

public class MainActivity extends AppCompatActivity
{
    private MyDBHelper myDBHelper;
    private EditText username;
    private EditText userpsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBHelper=new MyDBHelper(this);
        try {
            myDBHelper.createDataBase();
        }catch (Exception e){
            throw new Error("无法创建新数据库");
        }
        //登录按钮的登录事件
        findViewById(R.id.btnlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.etusername);
                userpsd = (EditText) findViewById(R.id.etpassword);
                String userName = username.getText().toString();
                String userPsd = userpsd.getText().toString();
                if (isLogin(userName, userPsd))
                {
                    Toast.makeText(MainActivity.this,"欢迎您的到来！",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity.this,IndexActivity.class);
                    MainActivity.this.startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
            }
        });
    }
    //验证登陆
    public boolean isLogin(String username,String password)
    {
        myDBHelper.openDataBase();
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        String sql="select * from reader where rname=? and rpassword=?";
        Cursor cursor=db.rawQuery(sql,new String[]{username,password});
        if(cursor.moveToFirst())
        {
            cursor.close();
            return true;
        }
        return false;
    }
    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    //菜单项事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuMainAbout:
                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.mnuMainExit:
                finish();
                break;
        }
        return true;
    }
}
