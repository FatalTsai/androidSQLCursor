package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String db_name ="testDB";
    static  final  String tb_name="test";
    SQLiteDatabase db;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db  = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);

        String CreateTable = "CREATE TABLE IF NOT EXISTS "+
                tb_name+
                "( name VARCHAR(32)," +
                "phone VARCHAR(32),"+
                "email VARCHAR(64) )";
        db.execSQL(CreateTable);


        Cursor c=db.rawQuery("SELECT * FROM "+tb_name,null);

        if(c.getCount() == 0)
        {
            addData("Flag Publsihing Co.","02-23963257",
                    "service@flag.com.tw");

            addData("PCDIY Magazine","02-23214355",
                    "service@pcdiy.com.tw");
        }
        if(c.moveToFirst()) {
            str = "There is " + c.getCount() + "datas\n";
            str += "-------------\n";

            do {
                str += "name:" + c.getString(0) + "\n";
                str += "phone:" + c.getString(1) + "\n";
                str += "email:" + c.getString(2) + "\n";
            } while (c.moveToNext());


            TextView txv = (TextView) findViewById(R.id.txv);
            txv.setText(str);
        db.close();
        }
}


    private  void  addData(String name ,String phone,String email)
    {
        ContentValues cv =  new ContentValues(3);

        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("email",email);

        db.insert(tb_name,null,cv);
    }
}

