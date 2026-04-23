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

import com.example.pesquisa_eleitoral.Adapter.EleitorAdapter;
import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Eleitor;

import java.util.List;

public class EleitoresActivity extends AppCompatActivity {

    ListView listView;

    List<Eleitor> listEleitores;

    Button btn_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eleitores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.eleitores_list);
        btn_voltar = findViewById(R.id.eleitores_btn_voltar);
        AppDatabase bd = AppDatabase.getInstance(this);

        new Thread(()->{
            listEleitores = bd.eleitorDAO().getAll();

            runOnUiThread(()->{
                if(listEleitores.isEmpty()){
                    CharSequence text = "Nenhuma pesquisa realizada até o momento!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this, text, duration);
                    toast.show();
                }

                EleitorAdapter eleitorAdapter = new EleitorAdapter(getApplicationContext(), listEleitores);
                listView.setAdapter(eleitorAdapter);
                System.out.println("Adapter passou!!!");
            });
        }).start();

        btn_voltar.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuResultadosActivity.class);
            startActivity(i);
            finish();
        });

    }
}