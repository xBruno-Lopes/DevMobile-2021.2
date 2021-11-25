package com.brunolopes.trabalhonavegaotelas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button addItem;
    private Button deletarITem;
    private Button editarItem;
    private int identificador = -1;

    //Utilizando random para gerar um ID "único" para cada livro, imagine isso como um fake ISBN
    private Random rand = new Random();
    private int id = rand.nextInt(100000);


    ArrayList<LivrosModel> listaLivros;
    ListView viewLivros;
    LivrosAdapter adapter;

    //Aqui é onde inicia-se o laucher para comunicação da tela principal e da tela
    // adicionar novo item(MainActivivy2) ou editar item(MAinActivity3) e onde recemos os dados delas,
    //de acordo com o code recebido. Poderia estar no onCreate também,
    ActivityResultLauncher<Intent> gerenciadorLivros = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Constants.RESULT_ADD) {
                    if (result.getData() != null) {
                        Intent data = result.getData();
                        String titulo = (String) data.getExtras().get("nome");
                        String autor = (String) data.getExtras().get("autor");
                        String ano = (String) data.getExtras().get("ano");
                        int id = (int) data.getExtras().get("id");

                        listaLivros.add(new LivrosModel(titulo, autor, ano, id));
                        Toast.makeText(MainActivity.this, "Livro Adicionado", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        identificador = -1;
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao adicionar livro.", Toast.LENGTH_SHORT).show();
                    }
                } else if (result.getResultCode() == Constants.RESULT_EDIT) {
                    if (result.getData() != null) {
                        Intent data = result.getData();
                        String titulo = (String) data.getExtras().get("nome");
                        String autor = (String) data.getExtras().get("autor");
                        String ano = (String) data.getExtras().get("ano");
                        int id = (int) data.getExtras().get("id");

                        listaLivros.remove(identificador);
                        listaLivros.add(identificador, new LivrosModel(titulo, autor, ano, id));
                        Toast.makeText(MainActivity.this, "Livro Editado", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        identificador = -1;
                    }
                } else if (result.getResultCode() == Constants.RESULT_CANCEL) {
                    Toast.makeText(MainActivity.this, "Operação cancelada", Toast.LENGTH_SHORT).show();
                    identificador = -1;
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaLivros = new ArrayList<>();

        //iniciando o adpater customizado
        adapter = new LivrosAdapter(this, listaLivros);
        viewLivros = findViewById(R.id.listLivros);
        viewLivros.setAdapter(adapter);

        //Itens adicionados hardcoded para testes
        listaLivros.add(new LivrosModel("Dom Quixote", "Miguel de Cervantes", "1605", 150402));
        listaLivros.add(new LivrosModel("Guerra e Paz", "Liev Tolstói", "1869", 123506));
        listaLivros.add(new LivrosModel("A Montanha Mágica", "Thomas Mann", "1924", 994045));

        //método para escutar um click em um item da lista
        viewLivros.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(MainActivity.this, "" + listaLivros.get(position).toString(), Toast.LENGTH_SHORT).show();
            identificador = position;
        });

        //métodos para funcionamento do app sendo chamados no onCreate
        addNovoLivro();
        editarLivro();
        deletarLivro();

    }

    //Método para adicionar um novo livro a partir do botão "adcionar" da tela principal
    public void addNovoLivro() {
        addItem = findViewById(R.id.addItem);
        addItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("id", id);
            id = rand.nextInt(100000);
            gerenciadorLivros.launch(intent);
        });
    }

    //Método para editar um item da lista a partir do botão "editar" da tela principal
    public void editarLivro() {
        editarItem = findViewById(R.id.editarItem);
        editarItem.setOnClickListener(v -> {
            if (identificador >= 0) {
                Intent intent = new Intent(this, MainActivity3.class);
                LivrosModel livro = listaLivros.get(identificador);
                intent.putExtra("titulo", livro.getTitulo());
                intent.putExtra("autor", livro.getAutor());
                intent.putExtra("ano", livro.getAno());
                intent.putExtra("id", livro.getId());

                gerenciadorLivros.launch(intent);

            } else {
                Toast.makeText(MainActivity.this, "Clique em um livro para selecionar primeiro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Método para remover um item da lista a partir do botão "remover" da tela principal,
    // precisa primeiro clicar em um item para remove-lo.
    public void deletarLivro() {
        deletarITem = findViewById(R.id.deletarItem);
        deletarITem.setOnClickListener(v -> {
            if (identificador >= 0) {
                listaLivros.remove(identificador);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Livro deletado", Toast.LENGTH_SHORT).show();
                identificador = -1;
            } else {
                Toast.makeText(MainActivity.this, "Clique em um livro para selecionar primeiro", Toast.LENGTH_SHORT).show();
            }
        });
    }

}