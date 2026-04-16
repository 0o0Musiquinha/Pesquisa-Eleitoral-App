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

public class PesquisaProblemasActivity extends AppCompatActivity {

    String[] problema = {"Saneamento", "Poluição", "Segurança", "Empregabilidade", "Moradia", "Urbanização", "Corrupição", "Escala", "Sálario Mínimo", "Insegurança Alimentar"};

    ListView listView;

    Button btn_proxima;

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
        ProblemaAdapter problemaAdapter = new ProblemaAdapter(getApplicationContext(), problema);
        listView.setAdapter(problemaAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(problema[position] + " - " + position);
            }
        });

        btn_proxima.setOnClickListener(v -> {
            Intent i = new Intent(this, FinalizarActivity.class);
            startActivity(i);
            finish();
        });
    }
}