package com.linzch3.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*UI组件相关*/
    private EditText newPassword;
    private EditText confirmPassword;
    private Button okButton;
    private Button clearButton;
    /*SharedPreferences相关*/
    private SharedPreferences passwordSP;
    private SharedPreferences.Editor passwordSaver;
    final static String SP_key = "PASSWORD";
    private boolean haveBeenSavedPassword = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkPasswordStatus();
        setupButton();

    }

    void setupButton(){
        /*设置按钮的相关逻辑*/
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPasswordText = newPassword.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();
                if(haveBeenSavedPassword){
                    if(confirmPasswordText.length()==0){
                        Toast.makeText(MainActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    }else if(confirmPasswordText.equals(passwordSP.getString(SP_key, null))){
                        startActivity(new Intent(MainActivity.this, FileEdit.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(newPasswordText.length()==0 || confirmPasswordText.length()==0){
                        Toast.makeText(MainActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    }else if(newPasswordText.equals(confirmPasswordText)){
                            passwordSaver.putString(SP_key, newPasswordText);
                            passwordSaver.commit();
                            startActivity(new Intent(MainActivity.this, FileEdit.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassword.setText("");
                confirmPassword.setText("");
            }
        });
    }

    void checkPasswordStatus(){
        /*检查是否已经成功保存过密码*/
        if(passwordSP.getString(SP_key, null)!=null){
            haveBeenSavedPassword = true;
            newPassword.setVisibility(View.GONE);
            confirmPassword.setHint("Password");
        }
    }
    void init() {
        newPassword = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);
        okButton = findViewById(R.id.ok_button);
        clearButton = findViewById(R.id.clear_button);

        passwordSP = this.getSharedPreferences(SP_key, MODE_PRIVATE);
        passwordSaver = passwordSP.edit();
    }
}
