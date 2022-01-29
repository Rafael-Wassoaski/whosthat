package com.midnight.whosthat.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.midnight.whosthat.R;
import com.midnight.whosthat.entities.Pokemon;
import com.midnight.whosthat.util.GetPokemons;

import java.util.Locale;

public class WhosThatController extends AppCompatActivity {
    private Pokemon pokemon;
    private GetPokemons getPokemons = new GetPokemons();
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.whos_that_interface);

        getPokemons = new GetPokemons();

        try {
            setPokemon();
        }catch (Exception e){
            Log.d("POKEMON", e.getMessage());
        }
    }

    private void setPokemon() throws InterruptedException {
        Log.d("POKEMON", "antes");
        Thread thread = new Thread(getPokemons);
        thread.start();
        thread.join();
        this.pokemon = getPokemons.getPokemon();
        ImageView pokemonImageView = findViewById(R.id.pokemonImageView);

        Log.d("POKE", this.pokemon.getImageUrl());
        Glide.with(this).load(this.pokemon.getImageUrl()).into(pokemonImageView);
    }

    public void checkAnswer(View v) throws InterruptedException {
        EditText answerEditText = (EditText) findViewById(R.id.answer);
        String answer = answerEditText.getText().toString().toLowerCase();
        String pokemonName = this.pokemon.getName().toLowerCase();

        if(answer.equals(pokemonName)){
            this.showDialogMessage("Resposta correta", String.format("Parabéns você acertou! Este é um %s", this.pokemon.getName()));
            setPokemon();
        }else{
            this.showDialogMessage("Resposta incorreta", "Resposta errada :( ! Tente novamente");
        }
    }

    private void showDialogMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
