package demo.chung.com.appqlchitieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class activity_custom_layout2 extends AppCompatActivity {
    EditText Ngay, ChiTiet, SoTien;
    CheckBox tkiem, tthuong, luong, khac1, khac2;
    Button NhapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_custom_layout2);

        init();
    }

    public void init(){
        Ngay = (EditText)findViewById(R.id.edtThoigian);
        ChiTiet = (EditText)findViewById(R.id.edtChiTiet);
        SoTien = (EditText)findViewById(R.id.edtTien);

        tkiem = (CheckBox)findViewById(R.id.chkTietkiem);
        tthuong = (CheckBox)findViewById(R.id.chkTienthuong);
        luong = (CheckBox)findViewById(R.id.chkLuong);
        khac1 = (CheckBox)findViewById(R.id.chkKhac1);
        khac2 = (CheckBox)findViewById(R.id.chkKhac2);
        NhapData = (Button)findViewById(R.id.btnAdd);



    }
}
