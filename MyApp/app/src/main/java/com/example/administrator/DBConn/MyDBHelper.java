package com.example.administrator.DBConn;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class MyDBHelper extends SQLiteOpenHelper{

    private static String DB_PATH="/data/data/com.android.dbread/databases/";
    private static String DB_NAME="read.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public MyDBHelper(Context context)
    {
        super(context,DB_NAME,null,1);
        this.myContext=context;
    }
    public void createDataBase()throws IOException
    {
        //检查数据库是否已经存在
        SQLiteDatabase checkDB=null;
        String myPath=DB_PATH + DB_NAME;
        checkDB=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        //若数据库已存在则关闭，若不存在则将assets中的数据库复制到/data/data/<package_name>/databases
        if(checkDB!=null)
            checkDB.close();
        else
        {
            this.getReadableDatabase();
            InputStream myInput=myContext.getAssets().open(DB_NAME);
            OutputStream myOutput=new FileOutputStream(myPath);

            byte[] buffer=new byte[8192];
            int length;
            while ((length=myInput.read(buffer))>0)
                myOutput.write(buffer,0,length);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
    }
    public void openDataBase()throws SQLException
    {
        String myPath=DB_PATH + DB_NAME;
        myDataBase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }
    @Override
    public synchronized void close() {
        if(myDataBase!=null)
            myDataBase.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //创建读者表（reader）并插入两条数据
        db.execSQL("create table reader(rno int primary key,rname varchar(20),rpassword char(8));");
        db.execSQL("INSERT INTO `reader`(`rno`, `rname`, `rpassword`) VALUES ('1','huangxing','12345678');");
        db.execSQL("INSERT INTO `reader`(`rno`, `rname`, `rpassword`) VALUES ('2','zhuqing','87654321');");
        //创建书籍种类表（booktype）并插入四条数据
        db.execSQL("create table booktype(btno int primary key,btname varchar(40));");
        db.execSQL("INSERT INTO `booktype`(`btno`, `btname`) VALUES ('1','社会科学');");
        db.execSQL("INSERT INTO `booktype`(`btno`, `btname`) VALUES ('2','历史地理');");
        db.execSQL("INSERT INTO `booktype`(`btno`, `btname`) VALUES ('3','政治法律');");
        db.execSQL("INSERT INTO `booktype`(`btno`, `btname`) VALUES ('4','文化教育');");
        //创建书籍表（book）并插入五条数据
        db.execSQL("create table book(bno int primary key,bname varchar(40),bauthor varchar(40),bpublish varchar(50),ISBN varchar(40),btno int);");
        db.execSQL("INSERT INTO `book`(`bno`, `bname`, `bauthor`, `bpublish`, `ISBN`, `btno`) VALUES ('1','SiWeiDaoTu','胡雅茹','北京时代出版社','sk1234','1');");
        db.execSQL("INSERT INTO `book`(`bno`, `bname`, `bauthor`, `bpublish`, `ISBN`, `btno`) VALUES ('2','ZhongGuoTongShi','吕思勉','武汉出版社','ls1234','2');");
        db.execSQL("INSERT INTO `book`(`bno`, `bname`, `bauthor`, `bpublish`, `ISBN`, `btno`) VALUES ('3','MinGuoZhengZhi','周言','九州出版社','zf1234','3');");
        db.execSQL("INSERT INTO `book`(`bno`, `bname`, `bauthor`, `bpublish`, `ISBN`, `btno`) VALUES ('4','RenJianCiHua','王国维','中国华侨出版社','wh1234','4');");
        db.execSQL("INSERT INTO `book`(`bno`, `bname`, `bauthor`, `bpublish`, `ISBN`, `btno`) VALUES ('5','BianCheng','沈从文','武汉出版社','wh1234','4');");
        //创建笔记表（notes）并插入四条数据
        db.execSQL("create table notes (rno int,bno int,riqi  data primary key,content varchar(400));");
        db.execSQL("INSERT INTO `notes`(`rno`, `bno`, `riqi`, `content`) VALUES ('1','2','2016-10-20','中华上下五千年，具有丰富的历史瑰宝！');");
        db.execSQL("INSERT INTO `notes`(`rno`, `bno`, `riqi`, `content`) VALUES ('2','3','2016-10-22','王国维是中国近代史上一位悲剧性人物。');");
        db.execSQL("INSERT INTO `notes`(`rno`, `bno`, `riqi`, `content`) VALUES ('3','4','2016-10-24','王国维的人生三境界：昨夜西风凋碧树，独上西楼，望断天涯路。衣带渐宽终不悔，为伊消得人憔悴。众里寻他千百度，蓦然回首，那人却在灯火阑珊处。');");
        db.execSQL("INSERT INTO `notes`(`rno`, `bno`, `riqi`, `content`) VALUES ('4','1','2016-10-26','思维导图是英国著名学者东尼·博赞在19世纪70年代初期创立的一种新型笔记方法。');\n");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
