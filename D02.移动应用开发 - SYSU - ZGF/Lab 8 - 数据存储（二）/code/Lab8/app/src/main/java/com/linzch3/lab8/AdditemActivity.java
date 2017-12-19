package com.linzch3.lab8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdditemActivity extends AppCompatActivity {

    private EditText name, birthday, gift;
    private Button additem_button;
    private DataBaseManager mDataBaseManager = new DataBaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        init();
        setupButton();
    }

    void setupButton(){
        additem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_input = name.getText().toString();
                if(name_input.equals("")){
                    Toast.makeText(AdditemActivity.this, "名字为空，请完善", Toast.LENGTH_SHORT).show();
                }else if(mDataBaseManager.queryIfNameExist(name_input)){
                    Toast.makeText(AdditemActivity.this, "名字重复，请检查", Toast.LENGTH_SHORT).show();
                }else{
                    String birthday_input = birthday.getText().toString();
                    String gift_input = gift.getText().toString();
                    mDataBaseManager.insertContact(name_input, birthday_input, gift_input);
                    finish();
                }

            }
        });
    }
    void init(){
        name = findViewById(R.id.name2);
        birthday = findViewById(R.id.birthday2);
        gift = findViewById(R.id.gift2);
        additem_button = findViewById(R.id.additem_button2);
    }
}
