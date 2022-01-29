package com.midnight.whosthat.controllers;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.midnight.whosthat.R;
import com.midnight.whosthat.entities.Pokemon;
import com.midnight.whosthat.util.GetPokemons;

public class WhosThatController extends AppCompatActivity {
    private Pokemon pokemon;
    private GetPokemons getPokemons;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        setContentView(R.layout.whos_that_interface);

        getPokemons = new GetPokemons();
        setPokemon();
    }

    private void setPokemon(){
        Log.d("POKEMON", "antes");
        getPokemons.getRandomPokemon();
    }

}
