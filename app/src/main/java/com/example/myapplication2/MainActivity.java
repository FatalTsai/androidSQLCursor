package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String db_name ="testDB";
    static  final  String tb_name="test";
    private ScrollView scrollView;

    SQLiteDatabase db;
    String str;
    TextView id,name,price,txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scrollView = (ScrollView) findViewById(R.id.scrollView);


        id =(TextView)findViewById(R.id.id);
        name =(TextView)findViewById(R.id.name);
        price =(TextView)findViewById(R.id.price);



        db  = openOrCreateDatabase(db_name, Context.MODE_PRIVATE,null);




        String CreateTable = "CREATE TABLE IF NOT EXISTS "+
                tb_name+
                "( id VARCHAR(32)," +
                "name VARCHAR(32),"+
                "price VARCHAR(64) )";
        db.execSQL(CreateTable);


        loadtext();
        //txv.setMovementMethod(ScrollingMovementMethod.getInstance());
}


    private  void  addData(String id ,String name,String price)
    {
        ContentValues cv =  new ContentValues(3);

        cv.put("id",id);
        cv.put("name",name);
        cv.put("price",price);

        Log.d("id",id);

        db.insert(tb_name,null,cv);
    }


    private  void  update(String id ,String name,String price)
    {
        ContentValues cv =  new ContentValues(3);

        cv.put("id",id);
        cv.put("name",name);
        cv.put("price",price);

        Log.d("id",id);

        db.update(tb_name,cv,"",null);
    }

    public void submit(View view) {
        addData(id.getText().toString(),name.getText().toString(),price.getText().toString());
        Log.d("fuck","fuck");
        loadtext();
    }


    public  void loadtext()
    {
        Cursor c=db.rawQuery("SELECT * FROM "+tb_name,null);

        Log.d("getcount",c.getCount()+"");
        if(c.getCount() == 0)
        {
            addData(":NTI20946.","上原亜衣MOODYZ出演全30タイトル12時間コンプリートBEST\t",
                    "8787");

            //addData("PCDIY Magazine","02-23214355",
                  //  "service@pcdiy.com.tw");
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
            txv.setMovementMethod(ScrollingMovementMethod.getInstance());

        }
    }





}

