package com.example.pesquisa_eleitoral;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesquisaEstimuladaActivity extends AppCompatActivity {

    String candidato[] = {"Cleber", "Jorginho", "Drummond", "Roberto", "Clovis"};
    String partido[] = {"PSOL", "PT", "PL", "PCDUB", "MISSAO"};
    int teste_img[]= {R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan, R.drawable.urnachan};

    ListView listView;
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
        listView = findViewById(R.id.pesquisaEstimulada_lista);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), candidato, partido, teste_img);
        listView.setAdapter(customBaseAdapter);
    }
}