package com.tom.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;
    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
        myDBHelper = new MyDBHelper(this, "expense.db", null, 1);
    }

    private void findViews() {
        edDate = (EditText) findViewById(R.id.ed_date);
        edInfo = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String date = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        /*String sql = "insert into exp(cdate,info,amount) values(" +
                "'"+date+"','"+info+"', "+amount+")";
        myDBHelper.getWritableDatabase().execSQL(sql);*/
        ContentValues values = new ContentValues();
        values.put("cdate", date);
        values.put("info", info);
        values.put("amount", amount);
        long id = myDBHelper.getWritableDatabase().insert("exp", null, values);
        Log.d("ADD", id+"");
        if (id > 0){
            Toast.makeText(this, "新增完成", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "新增失敗", Toast.LENGTH_SHORT).show();
        }
    }
}
