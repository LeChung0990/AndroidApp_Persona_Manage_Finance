package demo.chung.com.appqlchitieu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class activity_custom_layout2 extends AppCompatActivity {
    EditText Ngay, ChiTiet, SoTien, NumIDDel2;
    CheckBox tkiem, tthuong, luong, khac1, khac2;
    ListView hthi2;
    Button  HThiThuNhap;
    ImageButton Add_Inc, Del_Inc;

    String DanhMuc = "";
    ArrayList<String> mylist2;
    ArrayAdapter<String> myadapter2;

    private static final  String TABLE_THUNHAP = "TABLE_INCOME";

    SQLiteDatabase mydatabase;

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
        NumIDDel2 = (EditText)findViewById(R.id.edtID_Inc);
        hthi2 = (ListView)findViewById(R.id.lvhthi2);

        tkiem = (CheckBox)findViewById(R.id.chkTietkiem);
        tthuong = (CheckBox)findViewById(R.id.chkTienthuong);
        luong = (CheckBox)findViewById(R.id.chkLuong);
        khac1 = (CheckBox)findViewById(R.id.chk1Khac);
        khac2 = (CheckBox)findViewById(R.id.chk2Khac);

        HThiThuNhap = (Button)findViewById(R.id.btnHthi2);
        Add_Inc = (ImageButton)findViewById(R.id.imgThem2);
        Del_Inc =(ImageButton)findViewById(R.id.imgXoa2);

        Ngay.setText(getDataTime());

        mylist2 = new ArrayList<>();
        myadapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist2 );
        hthi2.setAdapter(myadapter2); //so du lieu SQLite
        mydatabase = openOrCreateDatabase("tnmanager.db", MODE_PRIVATE, null );
        //Tao Table de chua du lieu
        try{
            String sql = "CREATE TABLE "+TABLE_THUNHAP+"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                    "paydates TEXT," +
                    "paymoney INTERGER," +
                    "detail TEXT," +
                    "types TEXT)";
            mydatabase.execSQL(sql);
        }
        //Tao va mo co
        catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }

        Add_Inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Ngay.getText().toString().trim().length() <= 0) {
                    Toast.makeText(activity_custom_layout2.this, "Vui Lòng Nhập Ngày Tháng Năm", Toast.LENGTH_SHORT).show();
                } else if(SoTien.getText().toString().trim().length() <= 0) {
                    Toast.makeText(activity_custom_layout2.this, "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                } else if(!tkiem.isChecked() && !tthuong.isChecked() && !luong.isChecked() && !khac1.isChecked() && !khac2.isChecked() ) {
                    Toast.makeText(activity_custom_layout2.this, "Vui Lòng Chọn Ít Nhất 1 Danh Mục", Toast.LENGTH_SHORT).show();
                } else {
                    String StrCtiet = ChiTiet.getText().toString().trim();
                    if(StrCtiet.length() <= 0){
                        StrCtiet = "";
                    }
                    if (tkiem.isChecked()) {
                        DanhMuc = DanhMuc + "Tiết kiệm, ";
                    }
                    if (tthuong.isChecked()) {
                        DanhMuc = DanhMuc + "Tiền thưởng, ";
                    }
                    if (luong.isChecked()) {
                        DanhMuc = DanhMuc + "Lương, ";
                    }
                    if (khac1.isChecked()) {
                        DanhMuc = DanhMuc + "Khác 1, ";
                    }
                    if (khac2.isChecked()) {
                        DanhMuc = DanhMuc + "Khác 2, ";
                    }

                    String Budget = SoTien.getText().toString().trim();
                    int IntTien = Integer.parseInt(Budget);

                    ContentValues myvalue2 = new ContentValues();
                    myvalue2.put("paymoney", IntTien);
                    myvalue2.put("types", deleteLastCharacter(DanhMuc));
                    myvalue2.put("paydates", Ngay.getText().toString().trim());
                    myvalue2.put("detail", StrCtiet);

                    String msg = "";
                    if (mydatabase.insert(TABLE_THUNHAP, null, myvalue2) == -1) {
                        Toast.makeText(activity_custom_layout2.this, "Thêm vào chưa thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity_custom_layout2.this, "Thêm vào thành công!", Toast.LENGTH_SHORT).show();
                    }
                    DanhMuc = "";
                }
            }
        });

        HThiThuNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist2.clear();
                String selectQuery = "SELECT * FROM "+TABLE_THUNHAP + " ORDER BY id DESC";
//                SQLiteDatabase mydatabase = this.getRead
                Cursor c = mydatabase.rawQuery(selectQuery, null);
                String data = "";
//                Toast.makeText(activity_custom_layout2.this, String.valueOf(c.moveToNext()), Toast.LENGTH_SHORT).show();
                if (c.moveToFirst()){
                    do{
                        data =  "[" + c.getString(c.getColumnIndex("id")) + "]" +
                                "\t\t" + c.getString(c.getColumnIndex("types")) +
                                "\n" + c.getString(c.getColumnIndex("paymoney")) + " VND"
                        ;
                        mylist2.add(data);
                    }
                    while(c.moveToNext()); // tro den ket thuc
                }
                c.close();
                //thay doi du lieu tai listView
                myadapter2.notifyDataSetChanged();
            }
        });
        Del_Inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NumIDDel2.getText().toString().trim().length() <= 0){
                    Toast.makeText(activity_custom_layout2.this, "Vui lòng nhập ID để xoá!", Toast.LENGTH_SHORT).show();
                }else {
                    int state2 = delete(NumIDDel2.getText().toString().trim());
                    if(state2 == 0){
                        Toast.makeText(activity_custom_layout2.this, "ID không tồn tại\"+\"\n" + "Vui lòng nhập lại ID", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity_custom_layout2.this, "Xoá thành công "+state2+" ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int delete(String id) {
        int state_del = mydatabase.delete(TABLE_THUNHAP, "id = " + id, null);
        return state_del;
    }
    public String deleteLastCharacter (String str){
        if(str.trim().length() > 0){
            str = str.substring(0, str.length()-2);
        }
        return str;
    }
    public String getDataTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault()
        );
        Date date = new Date();
        return dateFormat.format(date);
    }
}
