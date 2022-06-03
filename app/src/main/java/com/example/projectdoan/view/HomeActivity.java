package com.example.projectdoan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdoan.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnStart2;
    private Button btnGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart2 = (Button) findViewById(R.id.btn_start2);
        btnGame = (Button) findViewById(R.id.btn_game);

        btnStart.setOnClickListener(this);
        btnStart2.setOnClickListener(this);
        btnGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(HomeActivity.this, AnimalListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_start2:
                Intent intent2 = new Intent(HomeActivity.this, CharacterListActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_game:
                Intent intent1 = new Intent(HomeActivity.this, GameActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}

