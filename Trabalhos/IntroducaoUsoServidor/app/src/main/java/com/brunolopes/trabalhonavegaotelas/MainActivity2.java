package com.brunolopes.trabalhonavegaotelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private Button buttoncancelar;
    private Button buttonadicionar;
    private EditText addTitulo;
    private EditText addAutor;
    private EditText addAno;
    private EditText addID;
    private int ID;

    Intent enviarDadosParaTelaPrincipal = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(getIntent().getExtras() != null){
            ID = (int)getIntent().getExtras().get("id");
        }
        addTitulo = findViewById(R.id.addTitulo);
        addAutor = findViewById(R.id.addAutor);
        addAno = findViewById(R.id.addAno);
        addID = findViewById(R.id.addID);
        addID.setText(""+ID);

        cancelarAdd();// Ação para botão cancelar
        adicionarNovoLivro();// Ação para botão adicionar
    }

    public void cancelarAdd(){
        buttoncancelar = findViewById(R.id.cancelarAdd);
        buttoncancelar.setOnClickListener(v -> {
            setResult(Constants.RESULT_CANCEL);
            finish();
        });
    }

    public void adicionarNovoLivro(){

        buttonadicionar = findViewById(R.id.addNovoItem);
        buttonadicionar.setOnClickListener(v->{

            String nome = addTitulo.getText().toString();
            String autor = addAutor.getText().toString();
            String ano = addAno.getText().toString();

            enviarDadosParaTelaPrincipal.putExtra("nome", nome);
            enviarDadosParaTelaPrincipal.putExtra("autor", autor);
            enviarDadosParaTelaPrincipal.putExtra("ano", ano);
            enviarDadosParaTelaPrincipal.putExtra("id", ID);

            setResult(Constants.RESULT_ADD, enviarDadosParaTelaPrincipal);
            finish();
        });
    }


}