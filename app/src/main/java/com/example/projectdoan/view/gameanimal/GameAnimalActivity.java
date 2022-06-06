package com.example.projectdoan.view.gameanimal;

import android.os.Bundle;

import com.example.projectdoan.R;
import com.example.projectdoan.database.DatabaseManager;
import com.example.projectdoan.database.SharedPrefUtils;
import com.example.projectdoan.model.Animal;
import com.example.projectdoan.model.ButtonCharacter;
import com.example.projectdoan.view.game.GameActivity;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class GameAnimalActivity extends AppCompatActivity implements QuestionAdapter.ItemClickListener, AnswerAdapter.ItemClickListener {
    private Button btnHome;
    private Button btnTiep;
    private TextView txtPoint;
    private ImageView imgQuestion;
    private RecyclerView recyclerQuestion;
    private RecyclerView recyclerAnswer;

    private int point;
    private static final String KEY_POINT = "KEY_POINT";

    private AnswerAdapter answerAdapter;
    private QuestionAdapter questionAdapter;
    private DatabaseManager databaseManager;
    private Random random = new Random();

    private ArrayList<Animal> animals;

    private ArrayList<ButtonCharacter> questions = new ArrayList<>();
    private ArrayList<ButtonCharacter> answers = new ArrayList<>();
    private String answerString = "";
    private String questionString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_animal);

        initViews();
        setupData();
    }

    private void setupData() {
        point = SharedPrefUtils.getIntData(this, KEY_POINT);
        txtPoint.setText(point + "");
        Animal animal = animals.get(random.nextInt(animals.size() - 1));

        answerString = "";
        questionString = "";

        questions.clear();
        imgQuestion.setImageBitmap(animal.getId());
        for (Character character : animal.getName().toCharArray()) {
            questions.add(new ButtonCharacter(character.toString(), false));
            questionString += character;
        }
        questionAdapter.setCharacters(questions);
        questionAdapter.notifyDataSetChanged();

        answers.clear();
        for (Character character : animal.getName().toCharArray()) {
            answers.add(new ButtonCharacter(character.toString(), false));

            String randomValue = String.valueOf("QWERTYUIOPASDFGHJKJLZXCVBNM".charAt(random.nextInt(13)));
            answers.add(new ButtonCharacter(randomValue, false));
        }
        answerAdapter.setCharacters(answers);
        answerAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        btnHome = findViewById(R.id.btn_home);
        btnTiep = findViewById(R.id.btn_tiep);
        txtPoint = findViewById(R.id.txt_point_animal);
        imgQuestion = findViewById(R.id.imgQuestion);
        recyclerQuestion = findViewById(R.id.recycler_question);
        recyclerAnswer = findViewById(R.id.recycler_answer);

        databaseManager = new DatabaseManager();
        databaseManager.getAnimalListFromDatabase();
        animals = databaseManager.getAnimals();
        questionAdapter = new QuestionAdapter(this);
        answerAdapter = new AnswerAdapter(this);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);

        FlexboxLayoutManager layoutManager2 = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        layoutManager.setAlignItems(AlignItems.CENTER);

        recyclerQuestion.setAdapter(questionAdapter);
        recyclerQuestion.setLayoutManager(layoutManager);
        recyclerAnswer.setAdapter(answerAdapter);
        recyclerAnswer.setLayoutManager(layoutManager2);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupData();
            }
        });
    }

    @Override
    public void itemQuestionClicked(ButtonCharacter buttonCharacter, int position) {
        if (!buttonCharacter.isShow() && point >= 100) {
            buttonCharacter.setShow(true);
            questionAdapter.notifyItemChanged(position);
            point -= 100;
            SharedPrefUtils.saveData(this, KEY_POINT, point);
            txtPoint.setText(point + "");
        } else {
            Toast.makeText(this, "Bạn không đủ điểm để mở ô!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemAnswerClicked(ButtonCharacter buttonCharacter) {
        answerString += buttonCharacter.getName();
        questions.get(answerString.length() - 1).setShow(true);
        questionAdapter.notifyDataSetChanged();
        if (answerString.length() == questions.size() && answerString.equals(questionString)) {
            Toast.makeText(this, "Bạn Đúng Là Thiên Tài !", Toast.LENGTH_SHORT).show();
            point += 200;
            txtPoint.setText(point + "");
            SharedPrefUtils.saveData(this, KEY_POINT, point);
        } else if (answerString.length() == questions.size()) {
            Toast.makeText(this, "Rất tiếc bạn đã trả lời sai!", Toast.LENGTH_SHORT).show();
        }
    }
}