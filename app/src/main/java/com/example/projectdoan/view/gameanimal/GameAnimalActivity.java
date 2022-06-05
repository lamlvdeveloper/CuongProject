package com.example.projectdoan.view.gameanimal;

import android.os.Bundle;

import com.example.projectdoan.R;
import com.example.projectdoan.database.DatabaseManager;
import com.example.projectdoan.database.SharedPrefUtils;
import com.example.projectdoan.model.Animal;
import com.example.projectdoan.model.ButtonCharacter;
import com.example.projectdoan.view.game.GameActivity;

import androidx.appcompat.app.AppCompatActivity;
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

        Animal animal = animals.get(random.nextInt(animals.size() - 1));

        answerString = "";
        questionString = "";

        questions.clear();
        imgQuestion.setImageBitmap(animal.getId());
        for (String character : Arrays.asList(animal.getName())) {
            questions.add(new ButtonCharacter(character, false));
            questionString += character;
        }
        questionAdapter.setCharacters(questions);

        answers.clear();
        for (String character : Arrays.asList(animal.getName())) {
            answers.add(new ButtonCharacter(character, false));
            answers.add(new ButtonCharacter(Arrays.asList("QWERTYUIOPASDFGHJKJLZXCVBNM").get(random.nextInt(13)), false));
        }
        answerAdapter.setCharacters(answers);
    }

    private void initViews() {
        btnHome = findViewById(R.id.btn_home);
        btnTiep = findViewById(R.id.btn_tiep);
        txtPoint = findViewById(R.id.txt_point);
        imgQuestion = findViewById(R.id.imgQuestion);
        recyclerQuestion = findViewById(R.id.recycler_question);
        recyclerAnswer = findViewById(R.id.recycler_answer);

        databaseManager = new DatabaseManager();
        databaseManager.getAnimalListFromDatabase();
        animals = databaseManager.getAnimals();
        questionAdapter = new QuestionAdapter(this);
        answerAdapter = new AnswerAdapter(this);

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
        } else {
            Toast.makeText(this, "Bạn không đủ điểm để mở ô!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemAnswerClicked(ButtonCharacter buttonCharacter) {
        if (answerString.length() == questions.size() && answerString.equals(questionString)) {
            Toast.makeText(this, "Bạn Đúng Là Thiên Tài !", Toast.LENGTH_SHORT).show();
            point += 200;
            txtPoint.setText(point);
            SharedPrefUtils.saveData(this, KEY_POINT, point);
        } else if (answerString.length() == questions.size()) {
            Toast.makeText(this, "Rất tiếc bạn đã trả lời sai!", Toast.LENGTH_SHORT).show();
        } else {
            answerString += buttonCharacter.getName();
        }
    }
}