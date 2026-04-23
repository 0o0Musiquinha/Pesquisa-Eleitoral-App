package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Database.AppDatabase;

public class MenuResultadosActivity extends AppCompatActivity {

    TextView txt_qtdpesquisas;

    Button btn_eleitores, btn_resultados, btn_limpar, btn_voltar;

    int countEleitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_resultados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_qtdpesquisas = findViewById(R.id.menuResultado_txt_qtdPesquisas);

        btn_eleitores = findViewById(R.id.menuResultado_btn_eleitores);
        btn_resultados = findViewById(R.id.menuResultado_btn_resultados);
        btn_limpar = findViewById(R.id.menuResultado_btn_limpar);
        btn_voltar = findViewById(R.id.menuResultado_btn_voltar);

        AppDatabase bd = AppDatabase.getInstance(this);

        new Thread(()->{
            countEleitor = bd.eleitorDAO().countEleitor();

            runOnUiThread(()->{
                txt_qtdpesquisas.setText("Qtd. de pessoas entrevistadas: " + countEleitor);
            });
        }).start();

        btn_eleitores.setOnClickListener(v -> {
            Intent i = new Intent(this, EleitoresActivity.class);
            startActivity(i);
            finish();
        });

        btn_resultados.setOnClickListener(v -> {
            Intent i = new Intent(this, ResultadosActivity.class);
            startActivity(i);
            finish();
        });

        btn_limpar.setOnClickListener(v -> {
            new Thread(()->{
                bd.clearAllTables();

                runOnUiThread(()->{
                    CharSequence text = "Limpeza concluida!!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this , text, duration);
                    toast.show();
                });

            }).start();

        });

        btn_voltar.setOnClickListener(v -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });

    }
}