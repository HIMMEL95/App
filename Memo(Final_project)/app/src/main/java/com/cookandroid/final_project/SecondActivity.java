package com.cookandroid.final_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button saveBtn, backBtn;
    CalendarView CalView;
    EditText edtText;
    String fileName;
    Intent intent = getIntent();
    public String str = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
        String subName = intent.getExtras().getString("subName");
        setTitle(subName);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        backBtn = (Button) findViewById(R.id.backBtn);

        edtText = (EditText) findViewById(R.id.edtText);
        CalView = (CalendarView) findViewById(R.id.CalView);

        CalView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = getIntent();
                String subName = intent.getExtras().getString("subName");

                fileName = Integer.toString(year) + "_" + Integer.toString(month+1) + "_" + Integer.toString(dayOfMonth) + "_" + subName +".txt";

                String str = readText(fileName);
                edtText.setText(str);
                saveBtn.setEnabled(true);
            }
        });

//        # saveBtn 눌렀을 때 작동 코드
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtText.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName+"이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });

//        # backBtn을 눌렀을 경우 작동 방식
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    String readText(String fName) {
        String textStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[1000];
            inFs.read(txt);
            inFs.close();

            str = new String(txt);
            edtText.setText(str);

            textStr = (new String(txt)).trim();
            saveBtn.setText("Revision");
        } catch (IOException e) {
            edtText.setHint("Text None");
            saveBtn.setText("Save new");
        }
        return textStr;
    }
}
