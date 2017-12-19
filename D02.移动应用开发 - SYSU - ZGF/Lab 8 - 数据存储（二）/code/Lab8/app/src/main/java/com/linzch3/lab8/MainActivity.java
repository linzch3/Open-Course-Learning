package com.linzch3.lab8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button additemButton;
    private ListView contactsListView;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, Object>> contactsListData = new ArrayList();
    private DataBaseManager mDataBaseManager = new DataBaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupButton();
        setupListViewData();
        setupListView();
    }

    String getAssociatedPhoneNumber(String name){
        String [] columnsToQuery = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String whereStatement = String.format(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = '%s'", name);
        Cursor cursor = MainActivity.this.getContentResolver()
                                         .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                 columnsToQuery,
                                                 whereStatement,
                                                 null, null);
        if(cursor!=null){
            String personalPhoneNumber = "无";
            while(cursor.moveToNext()){
                personalPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //Log.e("debug", personalPhoneNumber);
                break;
            }
            cursor.close();
            return personalPhoneNumber;
        }else return "无";
    }
    void setupListView(){
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialoglayout, null);

                TextView name = dialogView.findViewById(R.id.dialog_name);
                final EditText birthday = dialogView.findViewById(R.id.dialog_birthday);
                final EditText gift = dialogView.findViewById(R.id.dialog_gift);
                TextView phoneNumber = dialogView.findViewById(R.id.dialog_phone_number);

                final Map<String, Object> itemData_in_pos_i = contactsListData.get(i);
                name.setText(itemData_in_pos_i.get("name").toString());
                birthday.setText(itemData_in_pos_i.get("birthday").toString());
                gift.setText(itemData_in_pos_i.get("gift").toString());
                phoneNumber.setText(getAssociatedPhoneNumber(itemData_in_pos_i.get("name").toString()));

                new AlertDialog.Builder(MainActivity.this)
                        .setView(dialogView)
                        .setTitle("╭(●｀∀´●)╯╰(●’◡’●)╮")
                        .setNegativeButton("放弃修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "修改已放弃", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("保存修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = itemData_in_pos_i.get("name").toString();
                                String birthday_new = birthday.getText().toString();
                                String gift_new = gift.getText().toString();

                                mDataBaseManager.updateContact(name, birthday_new, gift_new);

                                Map<String, Object> temp = new LinkedHashMap<>();
                                temp.put("name", name);
                                temp.put("birthday", birthday_new);
                                temp.put("gift", gift_new);

                                contactsListData.set(i, temp);
                                mSimpleAdapter.notifyDataSetChanged();
                            }
                        })
                        .create().show();

            }
        });
        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("是否删除?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "不删除联系人", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDataBaseManager.deleteContact(contactsListData.get(i).get("name").toString());

                                contactsListData.remove(i);
                                mSimpleAdapter.notifyDataSetChanged();
                            }
                        })
                        .create().show();

                return true;
            }
        });
    }

    void setupListViewData(){
        contactsListData.clear();
        Cursor cursor = mDataBaseManager.queryAllContact();
        while(cursor.moveToNext()){
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("name", cursor.getString(cursor.getColumnIndex("name")));
            temp.put("birthday", cursor.getString(cursor.getColumnIndex("birthday")));
            temp.put("gift", cursor.getString(cursor.getColumnIndex("gift")));
            contactsListData.add(temp);
        }
        mSimpleAdapter = new SimpleAdapter(this, contactsListData, R.layout.contacts_list_item,
                new String[] {"name", "birthday", "gift"},
                new int[] {R.id.name, R.id.birthday, R.id.gift});
        contactsListView.setAdapter(mSimpleAdapter);
        cursor.close();
    }

    void setupButton(){
        additemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdditemActivity.class));
            }
        });
    }

    void init(){
        additemButton = findViewById(R.id.additem_button);
        contactsListView = findViewById(R.id.contacts_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupListViewData();
    }
}
