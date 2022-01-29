package com.midnight.whosthat.util;

import android.util.Log;

import com.midnight.whosthat.entities.Pokemon;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPokemons {
    public Pokemon getRandomPokemon() {
        Pokemon pokemon = new Pokemon("teste", "teste");

        new Thread() {
            @Override
            public void run() {
                try {
                    String pokemonString = requestApiPokemon(1);
                    JSONObject pokemon = new JSONObject(pokemonString);
                    String name = pokemon.getString("name");

                } catch (Exception e) {
                    Log.d("POKEMON", e.toString());

                }

            }

        }.start();

        return pokemon;
    }

    private String requestApiPokemon(int pokemonId) throws IOException {
        Log.d("POKEMON", "123");

        URL pokeApiUrl = new URL("https://pokeapi.co/api/v2/pokemon/66");
        HttpURLConnection connection = (HttpURLConnection) pokeApiUrl.openConnection();
        connection.setRequestMethod("GET");
        StringBuffer buffer = readConnectionBuffer(connection);

        return buffer.toString();
    }

    private StringBuffer readConnectionBuffer(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        String response;
        StringBuffer buffer = new StringBuffer();
        while ((response = reader.readLine()) != null) {
            buffer.append(response);
        }

        return buffer;
    }


}
