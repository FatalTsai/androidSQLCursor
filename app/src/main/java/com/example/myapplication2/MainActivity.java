package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String db_name ="testDB";
    static  final  String tb_name="test";
    SQLiteDatabase db;

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

        addData("Flag Publsihing Co.","02-23963257",
                            "service@flag.com.tw");

        addData("PCDIY Magazine","02-23214355",
                "service@pcdiy.com.tw");


        TextView txv =(TextView)findViewById(R.id.txv);
        txv.setText("path : "+db.getPath()+"\n"+
                "page size : "+db.getPageSize()+" Bytes\n"+
                "max size                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           : "+db.getMaximumSize()+" Bytes\n" );
        db.close();
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

