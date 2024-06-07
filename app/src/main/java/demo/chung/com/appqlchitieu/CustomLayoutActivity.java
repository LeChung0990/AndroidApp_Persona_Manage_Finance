package demo.chung.com.appqlchitieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class CustomLayoutActivity extends AppCompatActivity {

    EditText Ngay, ChiTiet, SoTien;
    CheckBox dilai, yte, anuong, nhao, canhan,giaitri;

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
        Ngay = (EditText)findViewById(R.id.edtDate2);
        ChiTiet = (EditText)findViewById(R.id.edtChiTiet);
        SoTien = (EditText)findViewById(R.id.edtTien);

        dilai = (CheckBox)findViewById(R.id.chkDilai);
        yte = (CheckBox)findViewById(R.id.chkYte);
        anuong = (CheckBox)findViewById(R.id.chkAnuong);
        nhao = (CheckBox)findViewById(R.id.chkNhao);
        canhan = (CheckBox)findViewById(R.id.chkCanhan);
        giaitri = (CheckBox)findViewById(R.id.chkGiaitri);


//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayDanhMuc);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        DanhMuc.setAdapter(arrayAdapter);



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
