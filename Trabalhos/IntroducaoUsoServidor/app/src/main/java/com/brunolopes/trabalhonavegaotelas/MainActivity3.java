package com.brunolopes.trabalhonavegaotelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    private Button buttoncancelar;
    private Button buttonSalvarEditar;
    private EditText editTitulo;
    private EditText editAutor;
    private EditText editAno;
    private EditText editId;

    Intent enviarDadosParaTelaPrincipal = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTitulo = findViewById(R.id.edtTitulo);
        editAutor = findViewById(R.id.edtAutor);
        editAno = findViewById(R.id.edtAno);
        editId = findViewById(R.id.edtID);

        if(getIntent().getExtras() != null){
            String titulo = (String)getIntent().getExtras().get("titulo");
            String autor = (String)getIntent().getExtras().get("autor");
            String ano = (String)getIntent().getExtras().get("ano");
            int id = (int)getIntent().getExtras().get("id");

            editTitulo.setText(titulo);
            editAutor.setText(autor);
            editAno.setText(ano);
            editId.setText(""+id);
        }

        cancelarEdit();// Ação para botão cancelar
        salvaEditLivro(); // Ação para botão salvar
    }

    public void cancelarEdit(){
        buttoncancelar = findViewById(R.id.cancelarEditITem);
        buttoncancelar.setOnClickListener(v -> {
            setResult(Constants.RESULT_CANCEL);
            finish();
        });
    }

    public void salvaEditLivro(){

        buttonSalvarEditar = findViewById(R.id.salvarEditItem);
        buttonSalvarEditar.setOnClickListener(v->{

            String nome = editTitulo.getText().toString();
            String autor = editAutor.getText().toString();
            String ano = editAno.getText().toString();

            int id = (int)getIntent().getExtras().get("id");
            enviarDadosParaTelaPrincipal.putExtra("nome", nome);
            enviarDadosParaTelaPrincipal.putExtra("autor", autor);
            enviarDadosParaTelaPrincipal.putExtra("ano", ano);
            enviarDadosParaTelaPrincipal.putExtra("id", id);

            setResult(Constants.RESULT_EDIT, enviarDadosParaTelaPrincipal);
            finish();
        });
    }
}