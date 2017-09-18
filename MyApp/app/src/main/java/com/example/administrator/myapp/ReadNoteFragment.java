package com.example.administrator.myapp;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.DBConn.MyDBHelper;

import java.util.Calendar;

public class ReadNoteFragment extends Fragment {
    int mYear,mMonth,mDay;
    private Button btnsetdate;
    private Button btnsave;
    private EditText etGetDate;
    private EditText et_getShuming,et_getZuozhe,et_getContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_read_note, container, false);
        //获取当前时间
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        //设置点击事件
        btnsetdate=(Button)root.findViewById(R.id.btn_setdate);
        btnsave=(Button)root.findViewById(R.id.btn_savenode);
        et_getShuming=(EditText)root.findViewById(R.id.et_getShuming);
        et_getZuozhe=(EditText)root.findViewById(R.id.et_getZuozhe);
        et_getContent=(EditText)root.findViewById(R.id.et_getContent);
        etGetDate=(EditText)root.findViewById(R.id.et_getDate);
        btnsetdate.setOnClickListener(new MyListener());
        btnsave.setOnClickListener(new MyListener());
        return root;
    }
    class MyListener implements View.OnClickListener{
        private MyDBHelper myDBHelper;
        private int num=0;
        @Override
        public void onClick(View v) {
            myDBHelper=new MyDBHelper(getContext());
            myDBHelper.openDataBase();
            SQLiteDatabase db=myDBHelper.getWritableDatabase();
            switch (v.getId())
            {
                case R.id.btn_setdate:
                    DatePickerDialog dp=new DatePickerDialog(getActivity(), dateListener, mYear, mMonth, mDay);
                    dp.show();
                    break;
                case R.id.btn_savenode:
                    Cursor cursor=db.rawQuery("select * from book",null);
                    num=cursor.getCount();
                    db.execSQL("insert into book(bno,bname,bauthor) values(?,?,?)",new Object[]{num+1,et_getShuming.getText(),et_getZuozhe.getText()});
                    db.execSQL("insert into notes(bno,riqi,content) values(?,?,?)",new Object[]{num+1,etGetDate.getText(),et_getContent});
                    Toast.makeText(getContext(),"添加成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    private DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear=year;
            mMonth=month;
            mDay=dayOfMonth;
            etGetDate.setText(new StringBuffer().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
        }
    };
}
