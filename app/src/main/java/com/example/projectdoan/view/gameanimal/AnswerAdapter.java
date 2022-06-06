package com.example.projectdoan.view.gameanimal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdoan.R;
import com.example.projectdoan.model.ButtonCharacter;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    private ArrayList<ButtonCharacter> characters;
    private AnswerAdapter.ItemClickListener itemClickListener;

    public AnswerAdapter(AnswerAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setCharacters(ArrayList<ButtonCharacter> characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_button_answer, parent, false);
        return new AnswerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerAdapter.ViewHolder holder, int position) {
        ButtonCharacter character = characters.get(position);
        holder.onBind(character);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layoutButton;
        private ImageView imgButton;
        private TextView txtText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgButton = itemView.findViewById(R.id.imgButton);
            txtText = itemView.findViewById(R.id.txtText);
            layoutButton = itemView.findViewById(R.id.layoutButton);

        }

        public void onBind(ButtonCharacter character) {
            txtText.setText(character.getName());
            layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.itemAnswerClicked(character);
                }
            });
        }
    }

    interface ItemClickListener {
        void itemAnswerClicked(ButtonCharacter buttonCharacter);
    }
}