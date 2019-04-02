package com.example.mercedes_benz.Vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mercedes_benz.Modele.Constant;
import com.example.mercedes_benz.Modele.Mercedes;
import com.example.mercedes_benz.R;
import com.google.gson.Gson;

public class AttributAfficheur extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficheur);

        TextView txt = findViewById(R.id.nommerco);

        Gson gson = new Gson();

        String sessionId= getIntent().getStringExtra(Constant.KEY);
        Mercedes attr = gson.fromJson(sessionId, Mercedes.class);
        txt.setText(Constant.fromHtml(attr.getName()));

    }

}
