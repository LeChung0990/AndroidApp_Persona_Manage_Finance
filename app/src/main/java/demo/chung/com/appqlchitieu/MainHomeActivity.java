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
    Button Tchitieu, Tthunhap, LogOut, Thongke;
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
        Tthunhap = (Button)findViewById(R.id.btnThuNhap);
        Tchitieu = (Button)findViewById(R.id.btnChiTieu);
        Thongke = (Button)findViewById(R.id.btnThongKe);
        LogOut = (Button)findViewById(R.id.btnLogOut);
        hthi = (TextView)findViewById(R.id.hienthi);

        Tthunhap.setOnClickListener(this);
        Tchitieu.setOnClickListener(this);
        Thongke.setOnClickListener(this);
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
            case R.id.btnThuNhap:
                ThuNhapLayout();
                break;
            case R.id.btnChiTieu:
                ChiTieuLayout();
                break;
            case R.id.btnLogOut:
                Intent intent = new Intent(MainHomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }


    public void ThuNhapLayout(){


        LayoutInflater inflater = MainHomeActivity.this.getLayoutInflater();
        //Buoc2 lay duong dan Layout
        custom_view = inflater.inflate(R.layout.activity_activity_custom_layout2, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this)
                .setTitle("Thêm Thu Nhập")
                .setView(custom_view)
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
    }

    public void ChiTieuLayout(){
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


                        EditText muc1 = (EditText) custom_view.findViewById(R.id.edtDate);
                        EditText muc2 = (EditText) custom_view.findViewById(R.id.edtTien);
                        EditText muc3 = (EditText) custom_view.findViewById(R.id.edtChiTiet);

                        String Ngay = muc1.getText().toString();
                        String SoTien = muc2.getText().toString();
                        String ChiTiet = muc3.getText().toString();

//                        // Lưu dữ liệu vào SharedPreferences
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("Ngay", Ngay);
//                        editor.putString("SoTien", SoTien);
//                        editor.putString("ChiTiet", ChiTiet);
//                        editor.apply();

//                        Toast.makeText(MainHomeActivity.this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show();



                        if (muc1.getText().toString().trim().length() <= 0 || muc2.getText().toString().trim().length() <= 0) {
                            Toast.makeText(MainHomeActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainHomeActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            String firts = muc1.getText().toString().trim();
                            String second = muc2.getText().toString().trim();
                            hthi.setText("Muc1: " + firts + "\nMuc2: " + second);

                        }
                    }
                })
                        //Dong nay co tac dung gi
                .setCancelable(false);
        builder.create().show();
    }



}
