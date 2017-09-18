package com.example.administrator.myapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.DBConn.MyDBHelper;

public class ThreeFragment extends Fragment {
    private ImageView addnote;
    private ImageView exit;
    private ImageView[] iv_biji=new ImageView[6];
    private TextView[] tv_shuming=new TextView[6];
    private TextView[] tv_riqi=new TextView[6];
    private ImageView[] iv_del=new ImageView[6];
    private int[] bijiIDS=new int[]{R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5,R.id.iv6};
    private int[] shumingIDS=new int[]{R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6};
    private int[] riqiIDS=new int[]{R.id.tv11,R.id.tv22,R.id.tv33,R.id.tv44,R.id.tv55,R.id.tv66};
    private int[] delIDS=new int[]{R.id.del1,R.id.del2,R.id.del3,R.id.del4,R.id.del5,R.id.del6};
    private MyDBHelper myDBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_three, container, false);
        addnote=(ImageView)root.findViewById(R.id.iv_addnote);
        exit=(ImageView)root.findViewById(R.id.iv_exit) ;
        addnote.setOnClickListener(new MyClickListener());
        exit.setOnClickListener(new MyClickListener());

        int k=0;
        myDBHelper=new MyDBHelper(getContext());
        myDBHelper.openDataBase();
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from notes join book on notes.bno=book.bno",null);
        while (cursor.moveToNext())
        {
            iv_biji[k]=(ImageView)root.findViewById(bijiIDS[k]);
            tv_shuming[k]=(TextView)root.findViewById(shumingIDS[k]);
            tv_riqi[k]=(TextView)root.findViewById(riqiIDS[k]);
            iv_del[k]=(ImageView)root.findViewById(delIDS[k]);
            iv_biji[k].setImageResource(R.drawable.notes);
            iv_del[k].setImageResource(R.drawable.delete);
            tv_shuming[k].setOnClickListener(new MyClickListener());
            tv_shuming[k].setText(cursor.getString(cursor.getColumnIndex("bname")));
            tv_riqi[k].setText(cursor.getString(cursor.getColumnIndex("riqi")));
            iv_del[k].setOnClickListener(new MyClickListener());
            k++;
        }
        return root;
    }

    private class MyClickListener implements View.OnClickListener{
        private MyDBHelper dbHelper;
        @Override
        public void onClick(View v) {
            NoteDetailFragment noteDetailFragment=new NoteDetailFragment();
            Bundle bundle=new Bundle();
            dbHelper=new MyDBHelper(getContext());
            dbHelper.openDataBase();
            SQLiteDatabase db1=dbHelper.getWritableDatabase();
            switch (v.getId())
            {
                case R.id.iv_addnote:
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_layout, new ReadNoteFragment()).commit();
                    break;
                case R.id.iv_exit:
                    getActivity().finish();
                    break;
                case R.id.tv1:
                    bundle.putString("key",tv_shuming[0].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.tv2:
                    bundle.putString("key",tv_shuming[1].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.tv3:
                    bundle.putString("key",tv_shuming[2].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.tv4:
                    bundle.putString("key",tv_shuming[3].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.tv5:
                    bundle.putString("key",tv_shuming[4].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.tv6:
                    bundle.putString("key",tv_shuming[5].getText().toString());
                    noteDetailFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.content_layout,noteDetailFragment).commit();
                    break;
                case R.id.del1:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[0].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.del2:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[1].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.del3:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[2].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.del4:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[3].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.del5:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[4].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.del6:
                    db1.execSQL("delete from notes where bno=?",new Object[]{getBno(tv_shuming[5].getText().toString())});
                    Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
        private int getBno(String bname)
        {
            int k=0;
            MyDBHelper helper=new MyDBHelper(getContext());
            helper.openDataBase();
            SQLiteDatabase db2=helper.getWritableDatabase();
            Cursor cursor2=db2.rawQuery("select * from book where bname=?",new String[]{bname});
            if (cursor2.moveToFirst())
                k=cursor2.getInt(cursor2.getColumnIndex("bno"));
            return k;
        }
    }
}
