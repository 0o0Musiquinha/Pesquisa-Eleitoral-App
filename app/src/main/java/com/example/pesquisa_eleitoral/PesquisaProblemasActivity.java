package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Adapter.ProblemaAdapter;
import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Problema;

import java.util.ArrayList;
import java.util.List;

public class PesquisaProblemasActivity extends AppCompatActivity {

    String candidato_espontanea;
    int id_estimulada;
    ListView listView;
    Button btn_proxima;
    List<Problema> listProblema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa_problemas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btn_proxima = findViewById(R.id.problemas_btn_confirmar);
        listView = findViewById(R.id.problemas_lista);
        id_estimulada = getIntent().getIntExtra("ID_ESTIMULADA", 0);
        candidato_espontanea = getIntent().getStringExtra("CANDIDATO_ESPONTANEA");
        AppDatabase bd = AppDatabase.getInstance(this);

        new Thread(()->{
            listProblema = bd.problemaDAO().getAll();

            if(listProblema.isEmpty()){
                preencherProblemas("Saneamento", 0, bd);
                preencherProblemas("Poluição", 0, bd);
                preencherProblemas("Segurança", 0, bd);
                preencherProblemas("Empregabilidade", 0, bd);
                preencherProblemas("Moradia", 0, bd);
                preencherProblemas("Urbanização", 0, bd);
                preencherProblemas("Corrupição", 0, bd);
                preencherProblemas("Escala", 0, bd);
                preencherProblemas("Sálario Mínimo", 0, bd);
                preencherProblemas("Insegurança Alimentar", 0, bd);
                listProblema = bd.problemaDAO().getAll();

                System.out.println("Inserção dos 'Problemas' base concluida!");
            }

            runOnUiThread(()->{
                listView = findViewById(R.id.problemas_lista);
                ProblemaAdapter problemaAdapter = new ProblemaAdapter(getApplicationContext(), listProblema);
                listView.setAdapter(problemaAdapter);
            });
        }).start();



        btn_proxima.setOnClickListener(v -> {
            if(!ProblemaAdapter.listSelectedProblema.isEmpty()){
                Intent i = new Intent(this, FinalizarActivity.class);
                i.putExtra("ID_ESTIMULADA",id_estimulada);
                i.putExtra("CANDIDATO_ESPONTANEA", candidato_espontanea);
                startActivity(i);
                finish();
            } else{
                CharSequence text = "Escolha pelo menos um dos concernimentos!!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this , text, duration);
                toast.show();
            }
        });
    }

    private void preencherProblemas(String nome, int qtdVotos, AppDatabase bd){
        Problema problema = new Problema();
        problema.setNome(nome);
        problema.setQtdVotos(qtdVotos);
        bd.problemaDAO().insert(problema);
    }
}