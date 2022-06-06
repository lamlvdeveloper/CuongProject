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

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private ArrayList<ButtonCharacter> characters;
    private ItemClickListener itemClickListener;

    public QuestionAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setCharacters(ArrayList<ButtonCharacter> characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_button_question, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ButtonCharacter character = characters.get(position);
        holder.onBind(character, position);
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

        public void onBind(ButtonCharacter character, int position) {
            txtText.setText(character.getName());
            if (character.isShow()){
                txtText.setVisibility(View.VISIBLE);
            } else {
                txtText.setVisibility(View.INVISIBLE);
            }
            layoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.itemQuestionClicked(character, position);
                }
            });
        }
    }

    interface ItemClickListener {
        void itemQuestionClicked(ButtonCharacter buttonCharacter, int position);
    }
}
