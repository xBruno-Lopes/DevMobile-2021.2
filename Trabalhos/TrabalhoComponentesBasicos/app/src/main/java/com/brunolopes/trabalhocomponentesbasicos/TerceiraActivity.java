package com.brunolopes.trabalhocomponentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.brunolopes.trabalhocomponentesbasicos.databinding.ActivityTerceiraBinding;

public class TerceiraActivity extends AppCompatActivity { //# Item 16

    ActivityTerceiraBinding bindig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bindig = ActivityTerceiraBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(bindig.getRoot());
        gridCarros();
    }

    public void gridCarros(){
        String[] carrosNome = {"Carro 1", "Carro 2", "Carro 3", "Carro 4", "Carro 5", "Carro 6",
                                "Carro 7", "Carro 8", "Carro 9"};
        int[] carrosImg = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
                            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9};
        GridAdapter gridAdapter = new GridAdapter(TerceiraActivity.this, carrosNome, carrosImg);
        bindig.gridViewr.setAdapter(gridAdapter);

        bindig.gridViewr.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(TerceiraActivity.this,
                        "Você clicou em: " + carrosNome[position], Toast.LENGTH_SHORT).show());
    }
}