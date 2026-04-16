package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisaEspontaneaActivity extends AppCompatActivity {

    EditText ed_politico;

    Button btn_confirmar;

    String candidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa_espontanea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed_politico = findViewById(R.id.pesquisaEspontanea_ed_candidato);
        btn_confirmar = findViewById(R.id.pesquisaEspontanea_btn_confirmar);

        btn_confirmar.setOnClickListener(v -> {
            candidato = String.valueOf(ed_politico.getText());

            System.out.println(candidato);

            Intent i = new Intent(this, FinalizarActivity.class);
            startActivity(i);
            finish();
        });
    }
}