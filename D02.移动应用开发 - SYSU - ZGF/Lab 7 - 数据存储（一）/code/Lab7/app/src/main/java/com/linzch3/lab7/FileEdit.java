package com.linzch3.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileEdit extends AppCompatActivity {

    private EditText fileNameEdit;
    private EditText fileContentEdit;
    private Button saveButton;
    private Button loadButton;
    private Button clearButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_edit);

        init();
        setupButton();
    }

    void setupButton(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存文件
                String fileName = fileNameEdit.getText().toString();
                String fileContent = fileContentEdit.getText().toString();
                try {
                    FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                    fileOutputStream.write(fileContent.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(FileEdit.this, "Save succesfully", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(FileEdit.this, "Fail to save file", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(FileEdit.this, "Fail to save file", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加载文件
                String fileName = fileNameEdit.getText().toString();
                try {
                    FileInputStream fileInputStream = openFileInput(fileName);
                    byte[] contents = new byte[fileInputStream.available()];
                    fileInputStream.read(contents);
                    fileInputStream.close();
                    fileContentEdit.setText(new String(contents));
                    Toast.makeText(FileEdit.this, "Load succesfully", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    Toast.makeText(FileEdit.this, "Fail to load file", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(FileEdit.this, "Fail to load file", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空已填的所有信息
                fileContentEdit.setText("");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除已保存的某个文件
                String fileName = fileNameEdit.getText().toString();
                deleteFile(fileName);
                Toast.makeText(FileEdit.this, "Delete succesfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void init(){
        fileNameEdit = findViewById(R.id.file_name_edit);
        fileContentEdit = findViewById(R.id.file_content_edit);
        saveButton = findViewById(R.id.save_button);
        loadButton = findViewById(R.id.load_button);
        clearButton = findViewById(R.id.clear_button2);
        deleteButton = findViewById(R.id.delete_button);
    }
}
