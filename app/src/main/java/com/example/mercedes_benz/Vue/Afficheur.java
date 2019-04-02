package com.example.mercedes_benz.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.mercedes_benz.Modele.Constant;
import com.example.mercedes_benz.Modele.Mercedes;
import com.example.mercedes_benz.Modele.MercedesRestApi;
import com.example.mercedes_benz.Modele.RestMercedesResponse;
import com.example.mercedes_benz.R;
import com.example.mercedes_benz.controller.MyAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Afficheur extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficheur);

        
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercedes-benz.com/image/v1/vehicles")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MercedesRestApi MercedesRestApi = retrofit.create(MercedesRestApi.class);

        retrofit2.Call<RestMercedesResponse> call = MercedesRestApi.getListMercedes();
        call.enqueue(new Callback<RestMercedesResponse>() {

            @Override
            public void onResponse(retrofit2.Call<RestMercedesResponse> call, Response<RestMercedesResponse> response) {
                RestMercedesResponse restMercedesResponse = response.body();
                List<Mercedes> listMercedes = restMercedesResponse.getResults();
                showList(listMercedes);
            }

            @Override
            public void onFailure(Call<RestMercedesResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    private void showList(List<Mercedes> list) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(list, getListener());
        recyclerView.setAdapter(mAdapter);
    }

    private MyAdapter.OnItemClickListener getListener() {
        MyAdapter.OnItemClickListener onItemClickListener = new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }

            public void onItemClick(Mercedes item) {
                Gson gson = new Gson();

                Intent intent = new Intent(getBaseContext(), Afficheur.class);
                intent.putExtra(Constant.KEY, gson.toJson(item));
                startActivity(intent);
            }
        };
        return onItemClickListener;
    }

}
