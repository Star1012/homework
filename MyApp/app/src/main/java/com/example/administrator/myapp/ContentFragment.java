package com.example.administrator.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.DBConn.MyDBHelper;

public class ContentFragment extends Fragment {
    private static final String PAGE="page";
    private int mPage;
    private ImageView[] iv_book=new ImageView[4];
    private TextView[] tv_bookname=new TextView[3] ;
    private TextView[] tv_bookauthor=new TextView[3];
    private int[] book_id=new int[]{R.id.iv_type_fengmian1,R.id.iv_type_fengmian2,R.id.iv_type_fengmian3};
    private int[] book_photo=new int[]{R.drawable.fengmian0,R.drawable.fengmian1,R.drawable.fengmian2,R.drawable.fengmian3,R.drawable.fengmian4};
    private int[] bookname_id=new int[]{R.id.tv_type_name1,R.id.tv_type_name2,R.id.tv_type_name3};
    private int[] bookauthor_id=new int[]{R.id.tv_type_auth1,R.id.tv_type_auth2,R.id.tv_type_auth3};

    private MyDBHelper myDBHelper;
    public static ContentFragment newInstance(int page)
    {
        Bundle bundle=new Bundle();
        bundle.putInt(PAGE,page);
        ContentFragment contentFragment=new ContentFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage=getArguments().getInt(PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_content, container, false);
        myDBHelper=new MyDBHelper(getContext());
        myDBHelper.openDataBase();
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        Cursor cursor=null;
        int k=0;
        for (int i=0;i<book_id.length;i++)
        {
            iv_book[i]=(ImageView)root.findViewById(book_id[i]);
            tv_bookname[i]=(TextView)root.findViewById(bookname_id[i]);
            tv_bookauthor[i]=(TextView)root.findViewById(bookauthor_id[i]);
        }
        switch (mPage)
        {
            case 0:
                cursor=db.rawQuery("select book.bno,book.bname,book.bauthor from book join booktype on " +
                        "book.btno=booktype.btno where btname='社会科学'",null);
                while (cursor.moveToNext())
                {
                    iv_book[k].setImageResource(book_photo[cursor.getInt(cursor.getColumnIndex("bno"))-1]);
                    tv_bookname[k].setText(cursor.getString(cursor.getColumnIndex("bname")));
                    tv_bookauthor[k].setText(cursor.getString(cursor.getColumnIndex("bauthor")));
                    k++;
                }
                break;
            case 1:
                cursor=db.rawQuery("select book.bno,book.bname,book.bauthor from book join booktype on " +
                        "book.btno=booktype.btno where btname='历史地理'",null);
                while (cursor.moveToNext())
                {
                    iv_book[k].setImageResource(book_photo[cursor.getInt(cursor.getColumnIndex("bno"))-1]);
                    tv_bookname[k].setText(cursor.getString(cursor.getColumnIndex("bname")));
                    tv_bookauthor[k].setText(cursor.getString(cursor.getColumnIndex("bauthor")));
                    k++;
                }
                break;
            case 2:
                cursor=db.rawQuery("select book.bno,book.bname,book.bauthor from book join booktype on " +
                        "book.btno=booktype.btno where btname='政治法律'",null);
                while (cursor.moveToNext())
                {
                    iv_book[k].setImageResource(book_photo[cursor.getInt(cursor.getColumnIndex("bno"))-1]);
                    tv_bookname[k].setText(cursor.getString(cursor.getColumnIndex("bname")));
                    tv_bookauthor[k].setText(cursor.getString(cursor.getColumnIndex("bauthor")));
                    k++;
                }
                break;
            case 3:
                cursor=db.rawQuery("select book.bno,book.bname,book.bauthor from book join booktype on " +
                        "book.btno=booktype.btno where btname='文化教育'",null);
                while (cursor.moveToNext())
                {
                    iv_book[k].setImageResource(book_photo[cursor.getInt(cursor.getColumnIndex("bno"))-1]);
                    tv_bookname[k].setText(cursor.getString(cursor.getColumnIndex("bname")));
                    tv_bookauthor[k].setText(cursor.getString(cursor.getColumnIndex("bauthor")));
                    k++;
                }
                break;
        }
        return root;
    }
}
