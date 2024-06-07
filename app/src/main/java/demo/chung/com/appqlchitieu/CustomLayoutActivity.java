package demo.chung.com.appqlchitieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomLayoutActivity extends AppCompatActivity {

    EditText Ngay, ChiTiet, SoTien;
    CheckBox dilai, yte, anuong, nhao, canhan,giaitri;
    Button  NhapData;
//    Spinner DanhMuc;

//    Button nhaptt;
//    SharedPreferences sp;
//    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//    String savedData = sharedPreferences.getString("key", "");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);

        init();

    }

    public void init(){
        Ngay = (EditText)findViewById(R.id.edtDate);
        ChiTiet = (EditText)findViewById(R.id.edtChiTiet);
        SoTien = (EditText)findViewById(R.id.edtTien);

        dilai = (CheckBox)findViewById(R.id.chkDilai);
        yte = (CheckBox)findViewById(R.id.chkYte);
        anuong = (CheckBox)findViewById(R.id.chkAnuong);
        nhao = (CheckBox)findViewById(R.id.chkNhao);
        canhan = (CheckBox)findViewById(R.id.chkCanhan);
        giaitri = (CheckBox)findViewById(R.id.chkGiaitri);
        NhapData = (Button)findViewById(R.id.btnAdd);

//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayDanhMuc);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        DanhMuc.setAdapter(arrayAdapter);



        NhapData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuly();

//                save();
            }
        });
    }
    public void xuly(){
        if(Ngay.getText().toString().trim().length() <= 0 ) {
            Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Nhập Ngày Tháng Năm", Toast.LENGTH_SHORT).show();
        } else if(SoTien.getText().toString().trim().length() <= 0){
            Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
        } else if(!dilai.isChecked() && !yte.isChecked() && !anuong.isChecked() && !nhao.isChecked() && !canhan.isChecked()
                && !giaitri.isChecked() ){
            Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Chọn Ít Nhất 1 Danh Mục", Toast.LENGTH_SHORT).show();
        }
    }
//    public void save(){
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("thunhat", muc1.getText().toString().trim());
//        editor.putInt("thuhai", Integer.parseInt(muc2.getText().toString().trim()));
//        editor.commit();
//    }
    public void load(){

    }
}
