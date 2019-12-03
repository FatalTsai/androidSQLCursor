package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
                "( id VARCHAR(32)," +
                "name VARCHAR(32),"+
                "price VARCHAR(64) )";
        db.execSQL(CreateTable);


        Cursor c=db.rawQuery("SELECT * FROM "+tb_name,null);

        Log.d("test",c.getCount()+"");
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
                str += "id:" + c.getString(0) + "\n";
                str += "name:" + c.getString(1) + "\n";
                str += "price:" + c.getString(2) + "\n";
            } while (c.moveToNext());


            TextView txv = (TextView) findViewById(R.id.txv);
            txv.setText(str);
            db.close();
        }
}


    private  void  addData(String id ,String name,String price)
    {
        ContentValues cv =  new ContentValues(3);

        cv.put("id",id);
        cv.put("name",name);
        cv.put("price",price);

        db.insert(tb_name,null,cv);
    }
}

