package com.example.projectdoan.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.projectdoan.App;
import com.example.projectdoan.model.Animal;
import com.example.projectdoan.model.Animals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by anhch on 05/05/2017.
 */

public class DatabaseManager {

    private static final String DATABASE_NAME = "Animals.sqlite";
    private static final String PATH = App.getContext().getApplicationInfo().dataDir + "/database"; // câu lệnh này chỉ đến thư mục chỉ chứa file là SQLite

    private static final String ANIMALS_TABLE = "Font";
    private static final String ANIMAL_TABLE = "ImageAnimals";
    private static final String GET_ALL_ANIMALS_SQL = "SELECT * FROM " + ANIMALS_TABLE + " ORDER BY rowid DESC"; // tao méo biết DESC là gì :D
    private static final String GET_ALL_ANIMAL_SQL = "SELECT * FROM " + ANIMAL_TABLE + " ORDER BY rowid DESC"; // tao méo biết DESC là gì :D
    // cai order by id desc la sắp xếp theo thứ tự a b  c  d e f g  h okie

    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Animals> animalses;
    private ArrayList<Animal> animals;

    public DatabaseManager() {
        coppyDataBase();
    }

    private void coppyDataBase() {
        try {
            File file = new File(PATH + DATABASE_NAME);
            if (!file.exists()) {
                File folder = new File(PATH);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                InputStream input = App.getContext().getAssets().open(DATABASE_NAME);
                OutputStream output = new FileOutputStream(file);
                byte[] bytes = new byte[1024];

                int length;
                while ((length = input.read(bytes)) > 0) {
                    output.write(bytes, 0, length);
                }
                output.flush();
                input.close();
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Animals> getAnimalsesList() {
        return this.animalses;
    }

    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }


    private void openDB() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(
                    PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDB() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public boolean getAnimalsesListFromDatabase() {
        openDB();
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_ANIMALS_SQL, null);
        if (cursor == null || cursor.getCount() == 0) {
            return false;
        }
        animalses = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            byte[] bytes = cursor.getBlob(cursor.getColumnIndex("Images"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            animalses.add(new Animals(bitmap,
                    cursor.getString(cursor.getColumnIndex("Name"))
            ));
            cursor.moveToNext();
        }
        cursor.close();
        closeDB();
        return true;
    }

    public boolean getAnimalListFromDatabase() {
        openDB();
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_ANIMAL_SQL, null);
        if (cursor == null || cursor.getCount() == 0) {
            return false;
        }
        animals = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            byte[] bytes = cursor.getBlob(cursor.getColumnIndex("Images"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

            animals.add(new Animal(bitmap,
                    cursor.getString(cursor.getColumnIndex("Name"))
            ));
            cursor.moveToNext();
        }
        cursor.close();
        closeDB();
        return true;
    }

}
