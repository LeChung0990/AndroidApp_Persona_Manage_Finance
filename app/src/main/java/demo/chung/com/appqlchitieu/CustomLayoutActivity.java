package demo.chung.com.appqlchitieu;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomLayoutActivity extends AppCompatActivity {

    EditText Ngay, ChiTiet, SoTien, NumIDDel;
    CheckBox dilai, yte, anuong, nhao, canhan,giaitri;
    Button HthiDsach;
    ImageButton Add_Exp, Del_Exp;

    ListView hthi;
    String DanhMuc = "";
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    private static final  String TABLE_CHITIEU = "TABLE_EXPENSE";
    SQLiteDatabase mydatabase;

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
        NumIDDel = (EditText)findViewById(R.id.edtID_Exp);
//        Them = (Button)findViewById(R.id.btnThem);
        HthiDsach = (Button)findViewById(R.id.btnHthi);
        hthi = (ListView)findViewById(R.id.lvhienthi);
        Add_Exp = (ImageButton)findViewById(R.id.imgThem);
        Del_Exp = (ImageButton)findViewById(R.id.imgXoa);


        dilai = (CheckBox)findViewById(R.id.chkDilai);
        yte = (CheckBox)findViewById(R.id.chkYte);
        anuong = (CheckBox)findViewById(R.id.chkAnuong);
        nhao = (CheckBox)findViewById(R.id.chkNhao);
        canhan = (CheckBox)findViewById(R.id.chkCanhan);
        giaitri = (CheckBox)findViewById(R.id.chkGiaitri);
        //Xoá hoặc chèn bảng hiện tại
//        mydatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_CHITIEU);

        Ngay.setText(getDataTime());
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, mylist );
        hthi.setAdapter(myadapter);
        //Tạo sổ dữ liệu SQLite
        mydatabase = openOrCreateDatabase("ctmanager.db", MODE_PRIVATE, null );
        //Tạo bảng để chứa dữ liệu
        try{
            String sql = "CREATE TABLE "+TABLE_CHITIEU+"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
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

        Add_Exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Ngay.getText().toString().trim().length() <= 0) {
                    Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Nhập Ngày Tháng Năm", Toast.LENGTH_SHORT).show();
                } else if(SoTien.getText().toString().trim().length() <= 0) {
                    Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Nhập Số Tiền", Toast.LENGTH_SHORT).show();
                } else if(!dilai.isChecked() && !yte.isChecked() && !anuong.isChecked() && !nhao.isChecked() && !canhan.isChecked()
                        && !giaitri.isChecked() ) {
                    Toast.makeText(CustomLayoutActivity.this, "Vui Lòng Chọn Ít Nhất 1 Danh Mục", Toast.LENGTH_SHORT).show();
                } else {
                    String StrCtiet = ChiTiet.getText().toString().trim();
                    if(StrCtiet.length() <= 0){
                        StrCtiet = "";
                    }
                    if (dilai.isChecked()) {
                        DanhMuc = DanhMuc + "Đi lại, ";
                    }
                    if (yte.isChecked()) {
                        DanhMuc = DanhMuc + "Y tế, ";
                    }
                    if (anuong.isChecked()) {
                        DanhMuc = DanhMuc + "Ăn uống, ";
                    }
                    if (nhao.isChecked()) {
                        DanhMuc = DanhMuc + "Nhà ở, ";
                    }
                    if (canhan.isChecked()) {
                        DanhMuc = DanhMuc + "Cá nhân, ";
                    }
                    if (giaitri.isChecked()) {
                        DanhMuc = DanhMuc + "Giải trí, ";
                    }

                    String Budget = SoTien.getText().toString().trim();
                    int IntTien = Integer.parseInt(Budget);

                    ContentValues myvalue = new ContentValues();
                    myvalue.put("paymoney", IntTien);
                    myvalue.put("types", deleteLastCharacter(DanhMuc));
                    myvalue.put("paydates", Ngay.getText().toString().trim());
                    myvalue.put("detail", StrCtiet);
                    String msg = "";
                    if (mydatabase.insert(TABLE_CHITIEU, null, myvalue) == -1) {
                        Toast.makeText(CustomLayoutActivity.this, "Thêm vào chưa thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CustomLayoutActivity.this, "Thêm vào thành công!", Toast.LENGTH_SHORT).show();
                    }
                    DanhMuc = "";
                }
            }
        });

        HthiDsach.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mylist.clear();
                     String selectQuery = "SELECT * FROM " + TABLE_CHITIEU + " ORDER BY id DESC";
                     Cursor c = mydatabase.rawQuery(selectQuery, null);
                     //ham nay tra ve con tro, khong phai tra ve du lieu
                     //doc con tro
                     String data = "";
//                 while(c.isAfterLast() == false) // tro den ket thuc
                     //Kiem tra moveToNext
//                     Toast.makeText(CustomLayoutActivity.this, String.valueOf(c.moveToNext()), Toast.LENGTH_SHORT).show();
                     if (c.moveToFirst()) {
                         do {
                             data =  "[" + c.getString(c.getColumnIndex("id")) + "]" +
                                     "\t\t" + c.getString(c.getColumnIndex("types")) +
                                     "\n" + c.getString(c.getColumnIndex("paymoney")) + " VND"
                             ;
                             mylist.add(data);
                         }
                         while (c.moveToNext()); // tro den ket thuc
                     }
                     c.close();
                     //thay doi du lieu tai listView
                     myadapter.notifyDataSetChanged();
                 }
             }
        );

        Del_Exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NumIDDel.getText().toString().trim().length() <= 0){
                    Toast.makeText(CustomLayoutActivity.this, "Vui lòng nhập ID để xoá!", Toast.LENGTH_SHORT).show();
                }else{
                    int state = delete(NumIDDel.getText().toString().trim());
                    if(state == 0){
                        Toast.makeText(CustomLayoutActivity.this, "ID không tồn tại"+"\nVui lòng nhập lại ID", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CustomLayoutActivity.this, "Xoá thành công "+state+" ID", Toast.LENGTH_SHORT).show();
                    }
                    myadapter.notifyDataSetChanged();
                }
            }
        });

        hthi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectQuery = "SELECT * FROM "+TABLE_CHITIEU ;
//                Cursor c = mydatabase.rawQuery(selectQuery, null);

                Toast.makeText(CustomLayoutActivity.this, mylist.get(position)+"", Toast.LENGTH_SHORT).show();
//                delete(String.valueOf(position+1));
                return false;
            }
        });

    }
    public int delete(String id){
       int state_del = mydatabase.delete(TABLE_CHITIEU,"id = "+id, null);
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
