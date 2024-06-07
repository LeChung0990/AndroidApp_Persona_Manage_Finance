package demo.chung.com.appqlchitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainHomeActivity extends AppCompatActivity implements View.OnClickListener{
    Button Tchitieu, Tthunhap, LogOut, Thongke;
    TextView hthi;
    //tao bien cong khai
    View custom_view;

    public ListView listView;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> dataList;
    public SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "DataListPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        init();
        restoreData();

    }

    public void init(){
        Tthunhap = (Button)findViewById(R.id.btnThuNhap);
        Tchitieu = (Button)findViewById(R.id.btnChiTieu);
        Thongke = (Button)findViewById(R.id.btnThongKe);
        LogOut = (Button)findViewById(R.id.btnLogOut);
//        hthi = (TextView)findViewById(R.id.hienthi);

        Tthunhap.setOnClickListener(this);
        Tchitieu.setOnClickListener(this);
        Thongke.setOnClickListener(this);
        LogOut.setOnClickListener(this);

//         Khởi tạo danh sách dữ liệu và Adapter
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, dataList);

        // Ánh xạ ListView từ layout
        listView = findViewById(R.id.lvhienthi);

        // Set Adapter cho ListView
        listView.setAdapter(adapter);


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
        //Buoc 1: Tao Layout
        LayoutInflater inflater = MainHomeActivity.this.getLayoutInflater();
        //Buoc 2: Lay duong dan Layout
        custom_view = inflater.inflate(R.layout.activity_activity_custom_layout2, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this)
                .setTitle("Thêm Thu Nhập")
                .setView(custom_view)
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText muc1 = (EditText) custom_view.findViewById(R.id.edtDate2);
                        EditText muc2 = (EditText) custom_view.findViewById(R.id.edtTien2);
                        EditText muc3 = (EditText) custom_view.findViewById(R.id.edtChitiet2);
                        CheckBox tkiem = (CheckBox) custom_view.findViewById(R.id.chkTietkiem);
                        CheckBox tthuong = (CheckBox) custom_view.findViewById(R.id.chkTienthuong);
                        CheckBox luong = (CheckBox) custom_view.findViewById(R.id.chkLuong);
                        CheckBox khac1 = (CheckBox) custom_view.findViewById(R.id.chk1Khac);
                        CheckBox khac2 = (CheckBox) custom_view.findViewById(R.id.chk2Khac);

                        String Ngay = muc1.getText().toString();
                        String SoTien = muc2.getText().toString();
                        String ChiTiet = muc3.getText().toString();
                        String DanhMuc = "";

                        if (Ngay.trim().length() <= 0) {
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Nhập Ngày Tháng Năm", Toast.LENGTH_SHORT).show();
                        } else if(SoTien.trim().length() <= 0) {
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                        } else if(!tkiem.isChecked() && !tthuong.isChecked() && !luong.isChecked() && !khac1.isChecked() && !khac2.isChecked() ){
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Chọn Ít Nhất 1 Danh Mục", Toast.LENGTH_SHORT).show();
                        } else {
                            if (tkiem.isChecked()) {
                                DanhMuc = DanhMuc + "Tiết kiệm,";
                            }
                            if (tthuong.isChecked()) {
                                DanhMuc = DanhMuc + "Tiền thưởng,";
                            }
                            if (luong.isChecked()) {
                                DanhMuc = DanhMuc + "Lương,";
                            }
                            if (khac1.isChecked()) {
                                DanhMuc = DanhMuc + "Khác 1,";
                            }
                            if (khac2.isChecked()) {
                                DanhMuc = DanhMuc + "Khác 2,";
                            }
                                Toast.makeText(MainHomeActivity.this, "Ngày: " + Ngay + "\nDanh mục: " + deleteLastCharacter(DanhMuc) + "\nChi tiết: " + ChiTiet + "\nSố tiền: " + SoTien + "\n Thêm thành công", Toast.LENGTH_SHORT).show();
                            }
                            DanhMuc = "";
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
        //Buoc 1: Tao Layout
        LayoutInflater inflater = MainHomeActivity.this.getLayoutInflater();
        //Buoc 2: Lay duong dan Layout
        custom_view = inflater.inflate(R.layout.activity_custom_layout, null);
        //Buoc3 Gan vao Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainHomeActivity.this)
                .setTitle("Thêm Chi Tiêu")
                .setView(custom_view)
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText muc1 = (EditText) custom_view.findViewById(R.id.edtDate2);
                        EditText muc2 = (EditText) custom_view.findViewById(R.id.edtTien);
                        EditText muc3 = (EditText) custom_view.findViewById(R.id.edtChiTiet);
                        CheckBox dilai = (CheckBox) custom_view.findViewById(R.id.chkDilai);
                        CheckBox yte = (CheckBox) custom_view.findViewById(R.id.chkYte);
                        CheckBox anuong = (CheckBox) custom_view.findViewById(R.id.chkAnuong);
                        CheckBox nhao = (CheckBox) custom_view.findViewById(R.id.chkNhao);
                        CheckBox canhan = (CheckBox) custom_view.findViewById(R.id.chkCanhan);
                        CheckBox giaitri = (CheckBox) custom_view.findViewById(R.id.chkGiaitri);


                        String Ngay = muc1.getText().toString();
                        String SoTien = muc2.getText().toString();
                        String ChiTiet = muc3.getText().toString();
                        String DanhMuc = "";

//                        // Lưu dữ liệu vào SharedPreferences
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("Ngay", Ngay);
//                        editor.putString("SoTien", SoTien);
//                        editor.putString("ChiTiet", ChiTiet);
//                        editor.apply();

//                        Toast.makeText(MainHomeActivity.this, "Dữ liệu đã được lưu", Toast.LENGTH_SHORT).show();


                        if (Ngay.trim().length() <= 0) {
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Nhập Ngày Tháng Năm", Toast.LENGTH_SHORT).show();
                        } else if(SoTien.trim().length() <= 0) {
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                        } else if(!dilai.isChecked() && !yte.isChecked() && !anuong.isChecked() && !nhao.isChecked() && !canhan.isChecked()
                                && !giaitri.isChecked() ){
                            Toast.makeText(MainHomeActivity.this, "Vui Lòng Chọn Ít Nhất 1 Danh Mục", Toast.LENGTH_SHORT).show();
                        } else {

                            if (dilai.isChecked()) {
                                DanhMuc = DanhMuc + "Đi lại,";
                            }
                            if (yte.isChecked()) {
                                DanhMuc = DanhMuc + "Y tế,";
                            }
                            if (anuong.isChecked()) {
                                DanhMuc = DanhMuc + "Ăn uống,";
                            }
                            if (nhao.isChecked()) {
                                DanhMuc = DanhMuc + "Nhà ở,";
                            }
                            if (canhan.isChecked()) {
                                DanhMuc = DanhMuc + "Cá nhân,";
                            }
                            if (giaitri.isChecked()) {
                                DanhMuc = DanhMuc + "Giải trí,";
                            }
                            Toast.makeText(MainHomeActivity.this, "Ngày: " + Ngay + "\nDanh mục: " + deleteLastCharacter(DanhMuc) + "\nChi tiết: " + ChiTiet + "\nSố tiền: " + SoTien + "\n Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                        //Dong nay co tac dung gi
                .setCancelable(false);
        builder.create().show();
    }

    public String deleteLastCharacter (String str){
        if(str.trim().length() > 0){
            str = str.substring(0, str.length()-2);
        }
        return str;
    }
    public void restoreData() {
        Set<String> dataSet = sharedPreferences.getStringSet("dataSet", null);
        if (dataSet != null) {
            dataList.addAll(dataSet);
            adapter.notifyDataSetChanged();
        }
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> dataSet = new HashSet<>(dataList);
        editor.putStringSet("dataSet", dataSet);
        editor.apply();
    }



}
