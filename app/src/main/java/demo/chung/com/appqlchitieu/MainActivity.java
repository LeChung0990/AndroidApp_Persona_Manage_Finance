package demo.chung.com.appqlchitieu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
        Button login;
        EditText user, password;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                init();
        }

    public void init(){
        login = (Button)findViewById(R.id.loginButton);
        user = (EditText)findViewById(R.id.EdtUser);
        password = (EditText)findViewById(R.id.EdtPass);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                if (user.getText().toString().trim().length() <=0 || password.getText().toString().trim().length() <= 0){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if(user.getText().toString().equals("chung") &&  password.getText().toString().equals("123")) {
                    //chua the dung tinh nang dung user va password
                    String ten = user.getText().toString().trim();
                    String mk =  password.getText().toString().trim();
                    Toast.makeText(MainActivity.this, "Tên:" + ten + "\nMật khẩu:" + mk + "\nĐăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainHomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Sai mật khẩu hoặc tên đăng nhập", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
