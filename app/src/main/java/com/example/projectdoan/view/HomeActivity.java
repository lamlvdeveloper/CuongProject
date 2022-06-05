package com.example.projectdoan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdoan.R;
import com.example.projectdoan.view.animal.AnimalListActivity;
import com.example.projectdoan.view.character.CharacterListActivity;
import com.example.projectdoan.view.game.GameActivity;
import com.example.projectdoan.view.gameanimal.GameAnimalActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnStart2;
    private Button btnGame;
    private Button btnGameAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart2 = (Button) findViewById(R.id.btn_start2);
        btnGame = (Button) findViewById(R.id.btn_game);
        btnGameAnimal = (Button) findViewById(R.id.btn_game_animal);

        btnStart.setOnClickListener(this);
        btnStart2.setOnClickListener(this);
        btnGame.setOnClickListener(this);
        btnGameAnimal.setOnClickListener(this);
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
            case R.id.btn_game_animal:
                Intent intent3 = new Intent(HomeActivity.this, GameAnimalActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}

