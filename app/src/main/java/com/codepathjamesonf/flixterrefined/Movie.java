package com.codepathjamesonf.flixterrefined;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String title;
    String overview;
    String posterPath;

    public Movie(JSONObject jsonObject) throws JSONException {

        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        posterPath = jsonObject.getString("poster_path");
    }

        public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i=0; i< movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
            return movies;

        }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterpath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
}



