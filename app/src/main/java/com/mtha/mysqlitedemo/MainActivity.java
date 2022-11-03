package com.mtha.mysqlitedemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTaiKhoan;
    FloatingActionButton addTaiKhoan;
    //khai bao lop dbopenhelper thao tac voi csdl
    DbOpenHelper dbOpenHelper=new DbOpenHelper(MainActivity.this);
    ArrayList<String> lsTaiKhoan = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        //mo db va lay du lieu
       // dbOpenHelper = new DbOpenHelper(MainActivity.this);
        lsTaiKhoan = dbOpenHelper.getAllTaiKhoan();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                lsTaiKhoan);
        lvTaiKhoan.setAdapter(adapter);
        addTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
                insLauncher.launch(intent);
            }
        });
    }

    ActivityResultLauncher insLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //xu ly ket qua
                    if(result.getResultCode()==RESULT_OK){
                        //thuc hien lay ra doi tuong tai khoan va insert vao database
                        TaiKhoan tk =(TaiKhoan) result.getData().getExtras().get("tk");
                       if( dbOpenHelper.insTaiKhoan(tk)!=-1) {
                           Toast.makeText(MainActivity.this, "Success",
                                   Toast.LENGTH_SHORT).show();
                           //cap lai listview
                           lsTaiKhoan = dbOpenHelper.getAllTaiKhoan();
                           adapter.notifyDataSetChanged();
                       }
                       else
                           Toast.makeText(MainActivity.this, "Fail",
                                   Toast.LENGTH_SHORT).show();
                    }
                }
            });
    private void getViews(){
        lvTaiKhoan = findViewById(R.id.lvUser);
        addTaiKhoan = findViewById(R.id.addUser);
    }
    
    @Override
    protected void onPostResume() {
        super.onPostResume();
        lsTaiKhoan = dbOpenHelper.getAllTaiKhoan();
        adapter.notifyDataSetChanged();
        lvTaiKhoan.setAdapter(adapter);
    }
}