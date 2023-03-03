package com.example.se2_einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private Button btnSend;
    private Button btnCalc;
    private TextView number;
    static private TextView answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btn_send);
        number = findViewById(R.id.inNumber);
        answer = findViewById(R.id.txtServer);
        btnCalc=findViewById(R.id.btnCalc);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ServerConnection client = new ServerConnection();
                client.execute(number.getText().toString());
            }
        });
        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calcStepTwo(number.getText().toString());
            }
        });
    }

    private void calcStepTwo(String s){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        String out ="";

        for(char c : s.toCharArray()){
            numbers.add(c-'0');
        }
        Collections.sort(numbers);

        for(int i: numbers){
            if(!(i ==2 || i == 3|| i==5||i==7)){
                out+=i;
            }
        }
        setAnswer(out);
    }
    protected static void setAnswer(String s){
        answer.setText(s);
    }
}