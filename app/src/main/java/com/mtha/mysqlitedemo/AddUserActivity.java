package com.mtha.mysqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class AddUserActivity extends AppCompatActivity {

    EditText etTen, etDT, etEmail;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getViews();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("tk",getTaiKhoan());
                intent.putExtras(bundle);
                //set ket qua tra ve
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private TaiKhoan getTaiKhoan(){
        return new TaiKhoan(etTen.getText().toString(),
                etDT.getText().toString(),etEmail.getText().toString());
    }
    private void getViews(){
        etTen = findViewById(R.id.etTenUser);
        etDT = findViewById(R.id.etDienThoai);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.saveUser);
    }


}