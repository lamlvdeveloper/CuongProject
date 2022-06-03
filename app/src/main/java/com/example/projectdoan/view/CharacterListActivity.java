package com.example.projectdoan.view;

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
import com.example.projectdoan.adapter.AnimalAdapter2;
import com.example.projectdoan.model.Animals;

import java.util.Locale;


public class CharacterListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private GridView livAnimal2;

    private Button btnHome2;
    private AnimalAdapter2 animalAdapter2;
    private Animals animalss;
    private TextToSpeech textToSpeech;

    private DatabaseManager databaseManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        anhxa();


    }

    private void anhxa() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        databaseManage = new DatabaseManager();
        databaseManage.getAnimalsesListFromDatabase();

        animalAdapter2 = new AnimalAdapter2(this);
        animalAdapter2.setAnimalses(databaseManage.getAnimalsesList());


        livAnimal2 = (GridView) findViewById(R.id.liv_animal2);
        livAnimal2.setAdapter(animalAdapter2);

        btnHome2 = (Button) findViewById(R.id.btn_home2);
        btnHome2.setOnClickListener( this);
      /*  animalses = new ArrayList<>();
        animalses.add(new Animals(R.drawable.b, "B"));
        animalses.add(new Animals(R.drawable.c, "C"));
        animalses.add(new Animals(R.drawable.d, "D"));
        animalses.add(new Animals(R.drawable.e, "E"));
        animalses.add(new Animals(R.drawable.f, "F"));
        animalses.add(new Animals(R.drawable.g, "G"));
        animalses.add(new Animals(R.drawable.h, "H"));
        animalses.add(new Animals(R.drawable.i, "I"));
        animalses.add(new Animals(R.drawable.j, "J"));
        animalses.add(new Animals(R.drawable.k, "K"));
        animalses.add(new Animals(R.drawable.l, "L"));
        animalses.add(new Animals(R.drawable.m, "M"));
        animalses.add(new Animals(R.drawable.n, "N"));
        animalses.add(new Animals(R.drawable.o, "O"));
        animalses.add(new Animals(R.drawable.p, "P"));
        animalses.add(new Animals(R.drawable.q, "Q"));
        animalses.add(new Animals(R.drawable.r, "R"));
        animalses.add(new Animals(R.drawable.s, "S"));
        animalses.add(new Animals(R.drawable.t, "T"));
        animalses.add(new Animals(R.drawable.u, "U"));
        animalses.add(new Animals(R.drawable.w, "W"));
        animalses.add(new Animals(R.drawable.x, "X"));
        animalses.add(new Animals(R.drawable.y, "Y"));
        animalses.add(new Animals(R.drawable.z, "Z"));
        animalAdapter2 = new AnimalAdapter2(this, animalses);
*/
       // livAnimal2.setAdapter(animalAdapter2);
        livAnimal2.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home2:
                finish();
                break;
            default:
                break;

        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       animalss = animalAdapter2.getItem(position);
        if (position % 2 == 0) {
            Toast.makeText(CharacterListActivity.this, "", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CharacterListActivity.this, "", Toast.LENGTH_SHORT).show();
        }
        String speak = animalss.getTxtname();
        textToSpeech.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
        MyDialog myDialog = new MyDialog(this, animalss.getId() , speak);
        myDialog.show();
    }
}


