package com.linzch3.lab2;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private RadioGroup radioGroup;
    private Button login_button;
    private Button register_button;
    private TextInputLayout username;
    private TextInputLayout password;
    private int radio_checkedId;//记录哪个radioButton是当前被选中的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        login_button = (Button) findViewById(R.id.login_button);
        username = (TextInputLayout) findViewById(R.id.usernameWrapper);
        password = (TextInputLayout) findViewById(R.id.passwordWrapper);
        register_button = (Button) findViewById(R.id.register_button);
        radio_checkedId = R.id.student_radioButton;//默认情况

        image.setOnClickListener(new OnClickListener(){
            String [] uploadChoices = new String [] {"拍摄","从相册选择"};//上传图片的两个选择
            public void onClick(View v){
                new Builder(MainActivity.this)
                    .setTitle("上传头像")
                    .setItems(uploadChoices, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();//隐藏窗口
                            Toast.makeText(MainActivity.this, "您选择了["+uploadChoices[which]+"]", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "您选择了[取消]", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create().show();
            }
        });

        /*点击确认按钮后的监听器*/
        final View.OnClickListener confirmListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Snackbar的确认按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        };

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId){

                if(checkedId == R.id.student_radioButton){
                    Snackbar.make(radioGroup, "您选择了学生", Snackbar.LENGTH_SHORT)
                            .setAction("确认", confirmListener)
                            .setActionTextColor(getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    radio_checkedId = R.id.student_radioButton;//记录当前选中的单选按钮
                }else if(checkedId == R.id.teacher_radioButton){
                    Snackbar.make(radioGroup, "您选择了教职工", Snackbar.LENGTH_SHORT)
                            .setAction("确认",confirmListener)
                            .setActionTextColor(getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                    radio_checkedId = R.id.teacher_radioButton;
                }
            }
        });

        login_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = username.getEditText().getText().toString();
                String pw = password.getEditText().getText().toString();
                if(TextUtils.isEmpty(number)){
                    username.setErrorEnabled(true);
                    username.setError("学号不能为空");
                }else{
                    username.setErrorEnabled(false);//注意及时将报错信息关闭，下同
                    if(TextUtils.isEmpty(pw)){
                        password.setErrorEnabled(true);
                        password.setError("密码不能为空");
                    }else{
                        password.setErrorEnabled(false);
                        if(TextUtils.equals(number, "123456")&&TextUtils.equals(pw, "6666")) {
                            Snackbar.make(login_button, "登录成功", Snackbar.LENGTH_SHORT)
                                    .setAction("确认",confirmListener)
                                    .setActionTextColor(getColor(R.color.colorPrimary))
                                    .setDuration(5000)
                                    .show();
                        }else{
                            Snackbar.make(login_button, "学号或密码错误", Snackbar.LENGTH_SHORT)
                                    .setAction("确认",confirmListener)
                                    .setActionTextColor(getColor(R.color.colorPrimary))
                                    .setDuration(5000)
                                    .show();
                        }
                    }
                }
            }
        });

        register_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_checkedId == R.id.student_radioButton){
                    Snackbar.make(register_button, "学生注册功能尚未启用", Snackbar.LENGTH_SHORT)
                            .setAction("确认",confirmListener)
                            .setActionTextColor(getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }else if(radio_checkedId == R.id.teacher_radioButton){
                    Toast.makeText(MainActivity.this, "教职工注册功能尚未启用", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
