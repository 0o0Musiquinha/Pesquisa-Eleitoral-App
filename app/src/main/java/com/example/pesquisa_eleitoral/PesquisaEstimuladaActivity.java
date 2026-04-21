package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class PesquisaEstimuladaActivity extends AppCompatActivity {

    String[] nome = {"Naruto", "Cleito", "Bob Esponja", "Alanzoka", "Zeca Urubu", "Branco", "Nulo", "Não sei"};
    String[] partido = {"PSOL", "PT", "PL", "PCDUB", "MISSAO", "", "", ""};

    int[] teste_img= {R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan,R.drawable.sem_imagem, R.drawable.sem_imagem, R.drawable.sem_imagem};

    List<String> candidatos_nome = new ArrayList<>();
    List<String> candidatos_partido = new ArrayList<>();
    List<String> candidatos_img = new ArrayList<>();
    ListView listView;

    Button btn_proxima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa_estimulada);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_proxima = findViewById(R.id.pesquisaEstimulada_btn_confirmar);
        listView = findViewById(R.id.pesquisaEstimulada_lista);
        AppDatabase bd = AppDatabase.getInstance(this);
        /*preencherCandidatos();*/

        CandidatoAdapter candidatoAdapter = new CandidatoAdapter(getApplicationContext(), nome, partido, teste_img);
        /*CandidatoAdapter candidatoAdapter = new CandidatoAdapter(getApplicationContext(), candidatos_nome, candidatos_partido, candidatos_img);*/
        listView.setAdapter(candidatoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(candidatos_nome.get(position) + " - " + candidatos_partido.get(position) + " - " + position);
            }
        });

        btn_proxima.setOnClickListener(v -> {
            Intent i = new Intent(this, PesquisaProblemasActivity.class);
            startActivity(i);
            finish();
        });
    }
}