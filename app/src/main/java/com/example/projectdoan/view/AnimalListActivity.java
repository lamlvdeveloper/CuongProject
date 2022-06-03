package com.example.projectdoan.view;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdoan.DatabaseManager;
import com.example.projectdoan.R;
import com.example.projectdoan.adapter.AnimalAdapter;
import com.example.projectdoan.model.Animal;
import com.example.projectdoan.model.Animals;

import java.util.ArrayList;
import java.util.Locale;

public class AnimalListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener , View.OnClickListener{

    private GridView livAnimal;
    private ArrayList<Animal> animals;

    private ArrayList<Animals> animalses;
    private Button btnHome;
    private AnimalAdapter animalAdapter;
    private Animal animal;
    private TextToSpeech textToSpeech;

    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);
        initializeComponents();
    }

    private void initializeComponents() {

        databaseManager = new DatabaseManager();
        databaseManager.getAnimalListFromDatabase();

        animalAdapter = new AnimalAdapter(this);
        animalAdapter.setAnimals(databaseManager.getAnimals());

        livAnimal = (GridView) findViewById(R.id.liv_animal);
        livAnimal.setAdapter(animalAdapter);

        btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setOnClickListener(this);
    /*    animals = new ArrayList<>();
        animals.add(new Animal(R.drawable.a, "A"));
        animals.add(new Animal(R.drawable.b, "B"));
        animals.add(new Animal(R.drawable.c, "C"));
        animals.add(new Animal(R.drawable.d, "D"));
        animals.add(new Animal(R.drawable.e, "E"));
        animals.add(new Animal(R.drawable.f, "F"));
        animals.add(new Animal(R.drawable.g, "G"));
        animals.add(new Animal(R.drawable.h, "H"));
        animals.add(new Animal(R.drawable.i, "I"));
        animals.add(new Animal(R.drawable.j, "J"));
        animals.add(new Animal(R.drawable.k, "K"));
        animals.add(new Animal(R.drawable.l, "L"));
        animals.add(new Animal(R.drawable.m, "M"));
        animals.add(new Animal(R.drawable.n, "N"));
        animals.add(new Animal(R.drawable.o, "O"));
        animals.add(new Animal(R.drawable.p, "P"));
        animals.add(new Animal(R.drawable.q, "Q"));
        animals.add(new Animal(R.drawable.r, "R"));
        animals.add(new Animal(R.drawable.s, "S"));
        animals.add(new Animal(R.drawable.t, "T"));
        animals.add(new Animal(R.drawable.u, "U"));
        animals.add(new Animal(R.drawable.v, "V"));
        animals.add(new Animal(R.drawable.w, "W"));
        animals.add(new Animal(R.drawable.x, "X"));
        animals.add(new Animal(R.drawable.y, "Y"));
        animals.add(new Animal(R.drawable.z, "Z"));
        animalAdapter = new AnimalAdapter(this, animals);

        livAnimal.setAdapter(animalAdapter);*/
        livAnimal.setOnItemClickListener(this);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                Intent i = new Intent(AnimalListActivity.this, HomeActivity.class);
                startActivity(i);
                break;
            default:
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        animal = animalAdapter.getItem(position);

        if (position % 2 == 0) {
            Toast.makeText(AnimalListActivity.this, "", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AnimalListActivity.this, "", Toast.LENGTH_SHORT).show();
        }
        String speak = animal.getName();

        textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
        MyDialog myDialog = new MyDialog(this, animal.getId(), speak);
        myDialog.show();
    }

}
