package com.example.intentServiceCodingFlow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.EditTextId);
    }

    public void StartService(View view) {
        String input = editText.getText().toString();

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("input",input);
        ContextCompat.startForegroundService(this,intent);
    }
}
