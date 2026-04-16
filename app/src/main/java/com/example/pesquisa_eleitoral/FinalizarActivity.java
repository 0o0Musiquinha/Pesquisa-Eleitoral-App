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

public class FinalizarActivity extends AppCompatActivity {

    Button btn_finalizar;

    EditText ed_nome, ed_celular;

    String nome, celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finalizar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed_celular = findViewById(R.id.finalizar_ed_celular);
        ed_nome = findViewById(R.id.finalizar_ed_nome);
        btn_finalizar = findViewById(R.id.finalizar_btn_finalizar);

        btn_finalizar.setOnClickListener(v -> {
            nome = String.valueOf(ed_nome.getText());
            celular = String.valueOf(ed_celular.getText());

            Intent i = new Intent(this, MenuPesquisasActivity.class);
            startActivity(i);
            finish();
        });
    }
}