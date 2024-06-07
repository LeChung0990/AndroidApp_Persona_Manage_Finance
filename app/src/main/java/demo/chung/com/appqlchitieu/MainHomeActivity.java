package demo.chung.com.appqlchitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainHomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button simple, layout, LogOut;
    TextView hthi;
    //tao bien cong khai
    View custom_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        init();
    }

    public void init(){
//        simple = (Button)findViewById(R.id.btnSimple);
        layout = (Button)findViewById(R.id.btnLayout);
        LogOut = (Button)findViewById(R.id.btnLogOut);
        hthi = (TextView)findViewById(R.id.hienthi);

//        simple.setOnClickListener(this);
        layout.setOnClickListener(this);
        LogOut.setOnClickListener(this);

//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            String thunhat = bundle.getString("thunhat");
//            int thuhai = bundle.getInt("thuhai");
//
//            hthi.setText("Muc1: " + thunhat + "\nMuc2: " + String.valueOf(thuhai));
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btnSimple:
//                dialogSimple();
//                break;
            case R.id.btnLayout:
                dialogLayout();
                break;
            case R.id.btnLogOut:
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }



//
//    public void dialogSimple(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this)
//                .setTitle("Thông Báo")
//                .setMessage("Xoá Dữ Liệu Thành Công")
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainHomeActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainHomeActivity.this, "Chua xoa", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        builder.create().show();
//    }

    public void dialogLayout(){
        //Buoc 1 taoj layout
        //Buoc2 lay duong dan Layout
        //Buoc3 Gan vao Dialog


        LayoutInflater inflater = MainHomeActivity.this.getLayoutInflater();
        //Buoc2 lay duong dan Layout
        custom_view = inflater.inflate(R.layout.activity_custom_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this)
                .setTitle("Thêm Chi Tiêu")
                .setView(custom_view)
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        EditText muc1 = (EditText) custom_view.findViewById(R.id.edt1);
//                        EditText muc2 = (EditText) custom_view.findViewById(R.id.edt2);
//                        if (muc1.getText().toString().trim().length() <= 0 || muc2.getText().toString().trim().length() <= 0) {
//                            Toast.makeText(MainHomeActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainHomeActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                            String firts = muc1.getText().toString().trim();
//                            String second = muc2.getText().toString().trim();
//                            hthi.setText("Muc1: " + firts + "\nMuc2: " + second);
//
//                        }
                    }
                })
                        //Dong nay co tac dung gi
                .setCancelable(false);
        builder.create().show();
    }



}
