package com.example.kursachv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Instruction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


    }

    public void onButtonClickMaintWindow (View v)
    {
        Intent intent = new Intent(Instruction.this, MainActivity.class);
        startActivity(intent);
    }
}