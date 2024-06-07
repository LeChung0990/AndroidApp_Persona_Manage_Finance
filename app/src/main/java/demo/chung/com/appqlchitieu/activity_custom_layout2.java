package demo.chung.com.appqlchitieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class activity_custom_layout2 extends AppCompatActivity {
    EditText Ngay, ChiTiet, SoTien;
    CheckBox tkiem, tthuong, luong, khac1, khac2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_custom_layout2);

        init();
    }

    public void init(){
        Ngay = (EditText)findViewById(R.id.edtDate2);
        ChiTiet = (EditText)findViewById(R.id.edtChitiet2);
        SoTien = (EditText)findViewById(R.id.edtTien2);

        tkiem = (CheckBox)findViewById(R.id.chkTietkiem);
        tthuong = (CheckBox)findViewById(R.id.chkTienthuong);
        luong = (CheckBox)findViewById(R.id.chkLuong);
        khac1 = (CheckBox)findViewById(R.id.chk1Khac);
        khac2 = (CheckBox)findViewById(R.id.chk2Khac);
    }
}
