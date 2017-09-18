package com.example.administrator.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.DBConn.MyDBHelper;

public class NoteDetailFragment extends Fragment {
    private TextView[] textViews=new TextView[4];
    private int[] ids=new int[]{R.id.n_shuming,R.id.n_zuozhe,R.id.n_riqi,R.id.n_content};
    private String[] strings=new String[]{"bname","bauthor","riqi","content"};
    private MyDBHelper myDBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_note_detail, container, false);
        myDBHelper=new MyDBHelper(getContext());
        myDBHelper.openDataBase();
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        String str=getArguments().getString("key");
        Cursor cursor=db.rawQuery("select * from notes join book on notes.bno=book.bno where book.bname=?",new String[]{str});
        if(cursor.moveToFirst())
        {
            for (int i=0;i<ids.length;i++) {
                textViews[i] = (TextView) root.findViewById(ids[i]);
                textViews[i].setText(cursor.getString(cursor.getColumnIndex(strings[i])));
            }
        }
        return root;
    }
}
