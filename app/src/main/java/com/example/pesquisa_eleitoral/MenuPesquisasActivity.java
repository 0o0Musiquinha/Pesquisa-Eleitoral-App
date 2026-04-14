package com.example.pesquisa_eleitoral;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPesquisasActivity extends AppCompatActivity {

    Button btn_estimulada, btn_espontanea,btn_finalizar;

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

        btn_estimulada = findViewById(R.id.pesquisa_btn_estimulada);
        btn_estimulada = findViewById(R.id.pesquisa_btn_estimulada);
        btn_estimulada = findViewById(R.id.pesquisa_btn_estimulada);
    }
}