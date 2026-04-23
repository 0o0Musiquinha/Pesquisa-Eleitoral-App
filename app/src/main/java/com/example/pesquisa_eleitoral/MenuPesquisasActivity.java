package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPesquisasActivity extends AppCompatActivity {

    Button btn_iniciar, btn_fechar, btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_pesquisas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_iniciar = findViewById(R.id.pesquisa_btn_iniciar);
        btn_fechar = findViewById(R.id.pesquisa_btn_fechar);
        btn_voltar = findViewById(R.id.menuPesquisa_btn_voltar);

        btn_iniciar.setOnClickListener(v -> {
            Intent i = new Intent(this, PesquisaEspontaneaActivity.class);
            startActivity(i);
            finish();
        });

        btn_fechar.setOnClickListener(v -> {
            finishAndRemoveTask();
        });

        btn_voltar.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });

    }
}