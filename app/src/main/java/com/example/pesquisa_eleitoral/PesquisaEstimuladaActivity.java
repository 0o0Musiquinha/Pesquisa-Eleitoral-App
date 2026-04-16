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

public class PesquisaEstimuladaActivity extends AppCompatActivity {

    String[] candidato = {"Cleber", "Jorginho", "Drummond", "Roberto", "Clovis", "Branco", "Nulo", "Não sei"};
    String[] partido = {"PSOL", "PT", "PL", "PCDUB", "MISSAO", "", "", ""};
    int[] teste_img= {R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan,R.drawable.sem_imagem, R.drawable.sem_imagem, R.drawable.sem_imagem};

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
        CandidatoAdapter candidatoAdapter = new CandidatoAdapter(getApplicationContext(), candidato, partido, teste_img);
        listView.setAdapter(candidatoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(partido[position] + " - " + candidato[position] + " - " + position);
            }
        });

        btn_proxima.setOnClickListener(v -> {
            Intent i = new Intent(PesquisaEstimuladaActivity.this, PesquisaProblemasActivity.class);
            startActivity(i);
            finish();
        });
    }
}