package com.midnight.whosthat.util;

import android.util.Log;

import com.midnight.whosthat.entities.Pokemon;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class GetPokemons implements Runnable {
    private Pokemon pokemon = new Pokemon("", "");

    @Override
    public void run() {

        try {
            Random rand = new Random();
            int pokemonId = rand.nextInt(1118) + 1;
            Log.d("POKEMON", ""+pokemonId);
            String pokemonString = requestApiPokemon(pokemonId);
            JSONObject pokemonJson = new JSONObject(pokemonString);
            String name = pokemonJson.getString("name");

            JSONObject sprites = new JSONObject(pokemonJson.getString("sprites"));
            JSONObject other = new JSONObject(sprites.getString("other"));
            JSONObject officialArtWork = new JSONObject(other.getString("official-artwork"));

            String frontDefault = officialArtWork.getString("front_default");
            Log.d("POKEMON", name);
            pokemon.setName(name);
            pokemon.setImageUrl(frontDefault);
        } catch (Exception e) {
            Log.d("POKEMON", e.toString());
        }
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    private String requestApiPokemon(int pokemonId) throws IOException {
        URL pokeApiUrl = new URL("https://pokeapi.co/api/v2/pokemon/" + pokemonId);
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
