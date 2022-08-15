package com.codepathjamesonf.flixterrefined;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepathjamesonf.flixterrefined.adapters.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    List<Movie> movies = new ArrayList<>();
    RecyclerView rvMovies;

    public static final String API_KEY = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lookup the recyclerview in activity layout
        rvMovies = findViewById(R.id.rvMovies);

        // Create adapter passing in the sample user data
        MovieAdapter adapter = new MovieAdapter(this, movies);

        // Attach the adapter to the recyclerview to populate items
        rvMovies.setAdapter(adapter);

        // Set layout manager to position the items
        rvMovies.setLayoutManager(new LinearLayoutManager(this));


        // Api network call
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "results" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    adapter.notifyDataSetChanged();
                    Log.i(TAG, "movies " + movies.size());

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i(TAG, "onSuccess" + json.toString());


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }

}