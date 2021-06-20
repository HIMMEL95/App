package com.cookandroid.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button[] subBtn = new Button[8];
    Integer[] subBtnID = {R.id.Subject1, R.id.Subject2, R.id.Subject3, R.id.Subject4,
            R.id.Subject5, R.id.Subject6, R.id.Subject7, R.id.Subject8};
    final String subName[] = {"Android Progamming", "과목2", "과목3", "과목4", "과목5", "과목6", "과목7", "과목8"};

/*    int i;*/
    Button Subject1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("School Project");

//        # 버튼 누를 때마다 다른 화면으로 표현될 수 있게 하는 것
/*        for (int i=0; i< subBtnID.length; i++) {
            subBtn[i] = (Button) findViewById(subBtnID[i]);
        }*/

        for (int i=0; i < subBtnID.length; i++) {
            subBtn[i] = (Button) findViewById(subBtnID[i]);

            final int index;
            index = i;

            subBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), subName[index], Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("subName", subName[index]);
                    startActivity(intent);
                }
            });
        }
    }
}