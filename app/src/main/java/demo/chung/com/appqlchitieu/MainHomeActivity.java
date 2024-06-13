package demo.chung.com.appqlchitieu;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

public class MainHomeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton Add_Exp, Add_Inc, Log_Out, Analytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        init();
    }
    public void init(){
        Add_Exp = (ImageButton)findViewById(R.id.imgBtnCtieu);
        Add_Inc =(ImageButton)findViewById(R.id.imgBtnTnhap);
        Log_Out = (ImageButton)findViewById(R.id.imgBtnLogOut);
        Analytics = (ImageButton)findViewById(R.id.imgBtnTke);

        Log_Out.setOnClickListener(this);
        Add_Exp.setOnClickListener(this);
        Add_Inc.setOnClickListener(this);
        Analytics.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBtnCtieu:
                Intent intentIMG1 = new Intent(MainHomeActivity.this, CustomLayoutActivity.class);
                startActivity(intentIMG1);
                break;
            case R.id.imgBtnTnhap:
                Intent intentIMG2 = new Intent(MainHomeActivity.this, activity_custom_layout2.class);
                startActivity(intentIMG2);
                break;
            case R.id.imgBtnLogOut:
                Intent intent2 = new Intent(MainHomeActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.imgBtnTke:
                Intent intent3 = new Intent(MainHomeActivity.this, Thongke.class);
                startActivity(intent3);
                break;
        }
    }
}
