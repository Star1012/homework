package com.example.administrator.myapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexActivity extends FragmentActivity{

    private BottomViewItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        item=BottomViewItem.getInstance();
        //设置点击事件
        for(int i=0;i<item.viewNum;i++)
        {
            item.linears[i]=(LinearLayout) findViewById(item.linears_id[i]);
            item.images[i] = (ImageView)findViewById(item.images_id[i]);
            item.texts[i] = (TextView)findViewById(item.texts_id[i]);
            item.linears[i].setOnClickListener(new MyListener());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,new OneFragment()).commit();
    }
    //实现单击事件
    private class MyListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.one_layout:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new OneFragment()).commit();
                    setTabSelection(0);
                    break;
                case R.id.two_layout:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new TwoFragment()).commit();
                    setTabSelection(1);
                    break;
                case R.id.three_layout:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, new ThreeFragment()).commit();
                    setTabSelection(2);
            }
        }
    }
    private void setTabSelection(int index) {
        clearSelection();
        item.images[index].setImageResource(item.images_selected[index]);
        item.texts[index].setTextColor(getResources().getColor(R.color.colorSelected));
    }
    private void clearSelection() {
        for (int i = 0; i < item.viewNum; i++) {
            item.images[i].setImageResource(item.images_unselected[i]);
            item.texts[i].setTextColor(getResources().getColor(R.color.colorUnSelected));
        }
    }
}
