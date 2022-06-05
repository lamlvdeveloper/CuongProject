package com.example.projectdoan.view.character;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.projectdoan.R;
import com.example.projectdoan.model.Animals;

import java.util.ArrayList;

/**
 * Created by cuongpc on 2/20/2017.
 */

public class AnimalAdapter2 extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Animals> animalses;

    public AnimalAdapter2(Context context){
        inflater = LayoutInflater.from(context);
        animalses = new ArrayList<>();
    }

    public void setAnimalses(ArrayList<Animals> animalses) {
        this.animalses = animalses;
    }

    @Override
    public int getCount() {
        return animalses.size();
    }

    @Override
    public Animals getItem(int position) {
        return animalses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_animal2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgAnimals = (ImageView) convertView.findViewById(R.id.img_animal2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgAnimals.setImageBitmap(animalses.get(position).getId());
        return convertView;
    }

    class ViewHolder {
        private ImageView imgAnimals;
    }
}
