package com.example.administrator.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.DBConn.MyDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TwoFragment extends Fragment {
    private MyDBHelper myDBHelper;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    private int oldPosition = 0;
    private int[] imageIds = new int[]{R.drawable.book0, R.drawable.book1, R.drawable.book2};
    private int[] dotIds = new int[]{R.id.dot_0,R.id.dot_1,R.id.dot_2};
    private int[] textViewIds = new int[]{R.id.tv_book_name,R.id.tv_book_author,R.id.tv_book_pub,R.id.tv_book_isbn,R.id.tv_book_tpye};
    private int[] fengmians=new int[]{R.drawable.fengmian0,R.drawable.fengmian1,R.drawable.fengmian2,R.drawable.fengmian3,R.drawable.fengmian4};

    private TextView[] tvs=new TextView[5];
    private ImageView iv_search,bfengmian;
    private EditText et_bookname;
    //存放图片的标题
    private String[]  titles = new String[]{"生活，就是生下来，活下去", "彩云之南—大美云南", "沐然回首，那人不在阑珊处",};
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_two, container, false);
        // 为ViewPager设置图片和小点
        mViewPaper=(ViewPager)root.findViewById(R.id.vp);
        et_bookname=(EditText)root.findViewById(R.id.et_bookname);//找到搜索框控件
        bfengmian=(ImageView)root.findViewById(R.id.fengmian);
        for(int j=0;j<textViewIds.length;j++)
        {
            tvs[j]=new TextView(getContext());
            tvs[j]=(TextView)root.findViewById(textViewIds[j]);
        }
        //找到搜索按钮并设置单击事件
        root.findViewById(R.id.iv_serach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookname=et_bookname.getText().toString();
                myDBHelper=new MyDBHelper(getContext());
                myDBHelper.openDataBase();
                SQLiteDatabase db=myDBHelper.getWritableDatabase();
                Cursor cursor=db.rawQuery("select * from book join booktype on" +
                        " book.btno=booktype.btno where bname=?",new String[]{bookname});
                if (cursor.moveToFirst())
                {
                    switch (cursor.getString(cursor.getColumnIndex("bname")))
                    {
                        case "SiWeiDaoTu":bfengmian.setImageResource(fengmians[0]);break;
                        case "ZhongGuoTongShi":bfengmian.setImageResource(fengmians[1]);break;
                        case "MinGuoZhengZhi":bfengmian.setImageResource(fengmians[2]);break;
                        case "RenJianCiHua":bfengmian.setImageResource(fengmians[3]);break;
                        case "BianCheng":bfengmian.setImageResource(fengmians[4]);break;
                    }
                    tvs[0].setText(cursor.getString(cursor.getColumnIndex("bname")));
                    tvs[1].setText(cursor.getString(cursor.getColumnIndex("bauthor")));
                    tvs[2].setText(cursor.getString(cursor.getColumnIndex("bpublish")));
                    tvs[3].setText(cursor.getString(cursor.getColumnIndex("ISBN")));
                    tvs[4].setText(cursor.getString(cursor.getColumnIndex("btname")));
                }
                else
                    Toast.makeText(getContext(),"不好意思，查无此书",Toast.LENGTH_LONG).show();
            }
        });
        images = new ArrayList<ImageView>();
        dots = new ArrayList<View>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
            dots.add(root.findViewById(dotIds[i]));
        }
        title=(TextView)root.findViewById(R.id.photoTitle);
        title.setText(titles[0]);
        adapter=new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        //设置轮播事件
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        return root;
    }
    //自定义Adapter
    private class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 2, 2, TimeUnit.SECONDS);
    }
    private class ViewPageTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
}
