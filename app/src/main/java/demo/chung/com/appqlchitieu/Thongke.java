package demo.chung.com.appqlchitieu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

public class Thongke extends AppCompatActivity {
    ListView Dis_Exp, Dis_Inc;
    ImageButton Ref, Ref2;
    TextView Exp, Inc, Remain;
    EditText date_exp, date_inc;

    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist2;
    ArrayAdapter<String> myadapter2;

    SQLiteDatabase mydatabase;
    long LongExp = 0, LongInc = 0, LongRemain = 0;;

    private static final  String TABLE_CHITIEU = "TABLE_EXPENSE";
    private static final  String TABLE_THUNHAP = "TABLE_INCOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        init();
    }

    public void init(){
        Dis_Exp = (ListView)findViewById(R.id.listView1);
        Dis_Inc = (ListView)findViewById(R.id.listView2);
        date_exp   = (EditText)findViewById(R.id.editText);
        date_inc = (EditText)findViewById(R.id.editText2);
        Ref = (ImageButton)findViewById(R.id.imgRefesh);
        Ref2 = (ImageButton)findViewById(R.id.imgRefesh2);
        Exp = (TextView)findViewById(R.id.textView7);
        Inc = (TextView)findViewById(R.id.textView9);
        Remain = (TextView)findViewById(R.id.textView10);

        date_exp.setText(getDataTime());
        date_inc.setText(getDataTime());

        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist);

        mylist2 = new ArrayList<>();
        myadapter2 =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist2);


        Ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dis_Exp.setAdapter(myadapter);
                mydatabase = openOrCreateDatabase("ctmanager.db", MODE_PRIVATE, null);
                mylist.clear();
                String selectQuery = "SELECT * FROM " + TABLE_CHITIEU + " WHERE paydates LIKE '%" + date_exp.getText().toString().trim() + "%'"+ " ORDER BY paydates DESC,id DESC";
                Cursor c = mydatabase.rawQuery(selectQuery, null);
                String data = "";
                LongExp = 0;
                if (c.moveToFirst()) {
                    do {
                        data = "[" + c.getString(c.getColumnIndex("id")) + "]" +
                                c.getString(c.getColumnIndex("types")) +
                                "(" + c.getString(c.getColumnIndex("detail")) + ")" +
                                "\n" + c.getString(c.getColumnIndex("paymoney")) + " VND" +
                                " \t\t" + c.getString(c.getColumnIndex("paydates"))
                        ;
                        mylist.add(data);
                        LongExp = LongExp + Integer.parseInt(c.getString((c.getColumnIndex("paymoney"))));
                    }
                    while (c.moveToNext()); // tro den ket thuc
                } else {
                    mylist.add("Không có dữ liệu");
                }
                c.close();
                myadapter.notifyDataSetChanged();
                Exp.setText(SwitchCurrency(LongExp));
                LongRemain = LongInc - LongExp;
                Remain.setText(SwitchCurrency(LongRemain));
            }
        });


        Ref2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dis_Inc.setAdapter(myadapter2);
                mydatabase = openOrCreateDatabase("tnmanager.db", MODE_PRIVATE, null );
//                Dis_Inc.setAdapter(myadapter);
//                Log.d("chung", "setAdapter thanh cong");
//                mydatabase = openOrCreateDatabase("ctmanager.db", MODE_PRIVATE, null);
                mylist2.clear();
                String selectQuery = "SELECT * FROM "+TABLE_THUNHAP + " WHERE paydates LIKE '%" + date_inc.getText().toString().trim() + "%'" + "ORDER BY paydates DESC,id DESC";
                Cursor c = mydatabase.rawQuery(selectQuery, null);
                String data = "";
                LongInc = 0;
                if (c.moveToFirst()){
                    do{
                        data = "[" + c.getString(c.getColumnIndex("id")) + "]" +
                                c.getString(c.getColumnIndex("types")) +
                                "(" + c.getString(c.getColumnIndex("detail")) + ")" +
                                "\n" + c.getString(c.getColumnIndex("paymoney")) + " VND" +
                                " \t\t" + c.getString(c.getColumnIndex("paydates"))
                        ;
                        mylist2.add(data);
                        LongInc = LongInc + Integer.parseInt(c.getString(c.getColumnIndex("paymoney")));
                    }
                    while(c.moveToNext()); // tro den ket thuc

                } else {
                    mylist2.add("Không có dữ liệu");
                }
                c.close();
                //thay doi du lieu tai listView
                myadapter2.notifyDataSetChanged();
                Inc.setText(SwitchCurrency(LongInc));
                LongRemain = LongInc - LongExp;
                Remain.setText(SwitchCurrency(LongRemain));
            }
        });
    }

    public String SwitchCurrency(long Money ){
        DecimalFormatSymbols symbol = new DecimalFormatSymbols();
        symbol.setGroupingSeparator(',');
        symbol.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat(" #,##0 VND", symbol);
        String StrMoney = decimalFormat.format(Money);
        return StrMoney;
    }
    public String getDataTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-yyyy", Locale.getDefault()
        );
        Date date = new Date();
        return dateFormat.format(date);
    }
}
