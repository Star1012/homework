package com.example.administrator.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.DBConn.MyDBHelper;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class OneFragment extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private Fragment[] fragments=new Fragment[4];
    private String[] tabTitles=new String[4];
    private MyDBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_one,container,false);
        initView(root);
        return root;
    }
    private void initView(View view)
    {
        tabLayout=(TabLayout)view.findViewById(R.id.tabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.tab_viewpage);
        //查询数据库书籍类别表，将书籍的种类查出来填充到TabLayout的表头中去
        dbHelper=new MyDBHelper(getContext());
        dbHelper.openDataBase();
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("booktype",null,null,null,null,null,null);
        for(int i=0;i<4;i++)
        {
            cursor.moveToPosition(i);
            tabTitles[i]=cursor.getString(cursor.getColumnIndex("btname"));
            tabLayout.addTab(tabLayout.newTab().setText(tabTitles[i]));

        }
        //配置适配器，并将TabLayout和ViewPager两个空间绑定
        adapter=new MyViewPagerAdapter(getFragmentManager(),tabTitles,getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private Fragment[] fragment;
        private String[] tabTitles;
        private Context mContext;
        public MyViewPagerAdapter(FragmentManager fm,String[] tabTitles,Context mContext) {
            super(fm);
            this.tabTitles=tabTitles;
            this.mContext=mContext;
        }
        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(position);
        }
        @Override
        public int getCount() {
            return tabTitles.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
