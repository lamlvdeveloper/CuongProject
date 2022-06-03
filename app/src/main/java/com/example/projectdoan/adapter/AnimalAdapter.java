package com.example.projectdoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.projectdoan.R;
import com.example.projectdoan.model.Animal;

import java.util.ArrayList;


public class AnimalAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Animal> animals;

    public AnimalAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        animals = new ArrayList<>();
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Animal getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_animal, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgAnimal = (ImageView) convertView.findViewById(R.id.img_animal);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgAnimal.setImageBitmap(animals.get(position).getId());
        return convertView;
    }

    class ViewHolder {
        private ImageView imgAnimal;
    }
}
