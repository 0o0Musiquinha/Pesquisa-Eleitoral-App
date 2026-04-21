package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Candidato;

import java.util.ArrayList;
import java.util.List;

public class PesquisaEstimuladaActivity extends AppCompatActivity {

    int candidato_id = 0;
    ListView listView;
    List<Candidato> listCandidatos;
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

        new Thread(()->{

            listCandidatos = bd.candidatoDAO().getAll();

            if (listCandidatos.isEmpty()){
                preencherCandidato("Naruto", "Time 7", "naruto", 0, bd);
                preencherCandidato("Cleiton", "Olimpo", "cleiton", 0, bd);
                preencherCandidato("Bob Esponja", "Garry Lovers", "bob_esponja", 0, bd);
                preencherCandidato("Alanzoka", "EletronicDesire", "alanzoka", 0, bd);
                preencherCandidato("Zeca Urubu", "Mengão", "zeca_urubu", 0, bd);
                preencherCandidato("Branco", "", "sem_imagem", 1, bd);
                preencherCandidato("Nulo", "", "sem_imagem", 1, bd);
                preencherCandidato("Não sei", "", "sem_imagem", 1, bd);
                listCandidatos = bd.candidatoDAO().getAll();
                System.out.println("Banco de dados Passou");
            }

            runOnUiThread(()->{
                CandidatoAdapter candidatoAdapter = new CandidatoAdapter(getApplicationContext(), listCandidatos);
                listView.setAdapter(candidatoAdapter);
                System.out.println("Adapter Passou");
            });
        }).start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Candidato candidato = listCandidatos.get(position);
                System.out.println(candidato.getNome() + " - " + candidato.getPartido() + " - " + candidato.getId());
                candidato_id = candidato.getId();
            }
        });

        btn_proxima.setOnClickListener(v -> {
            if(candidato_id != 0){
                Intent i = new Intent(this, PesquisaProblemasActivity.class);
                i.putExtra("Id_Estimulada", candidato_id);
                startActivity(i);
                finish();
            } else{
                CharSequence text = "Escolha uma das opções para voto!!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this , text, duration);
                toast.show();
            }
        });
    }

    private void preencherCandidato(String nome, String partido, String img, int tipo, AppDatabase bd){
        Candidato candidato = new Candidato();
        candidato.setNome(nome);
        candidato.setPartido(partido);
        candidato.setImg(img);
        candidato.setQtdVotos(0);
        candidato.setTipo(tipo);
        bd.candidatoDAO().insert(candidato);
    }
}