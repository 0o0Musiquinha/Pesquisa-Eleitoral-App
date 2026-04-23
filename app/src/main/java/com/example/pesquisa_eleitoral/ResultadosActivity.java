package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Candidato;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {

    HorizontalBarChart grafico_barraHorizontal_estimulada;

    Button btn_voltar;
    List<BarEntry> listEntradas;

    List<String> listNomes_candidatosEstimulada;

    List<Candidato> listCandidatos_estimulada;

    int totalVotos_estimulada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AppDatabase bd = AppDatabase.getInstance(this);
        grafico_barraHorizontal_estimulada = (HorizontalBarChart) findViewById(R.id.resultados_graficoBarraHorizontal_Estimulada);
        btn_voltar = findViewById(R.id.resultados_btn_voltar);

        listEntradas = new ArrayList<>();
        listNomes_candidatosEstimulada = new ArrayList<>();

        new Thread(()->{
            listCandidatos_estimulada = bd.candidatoDAO().getAll();
            totalVotos_estimulada = bd.eleitorDAO().countEleitor();

            if(totalVotos_estimulada != 0){
                for(int i = 0; i < listCandidatos_estimulada.size(); i++){
                    Candidato candidato = listCandidatos_estimulada.get(i);
                    System.out.println(candidato.getNome() + " - " + candidato.getQtdVotos());
                    listEntradas.add(new BarEntry(i, (((float) candidato.getQtdVotos() / totalVotos_estimulada) * 100)));
                    listNomes_candidatosEstimulada.add(String.valueOf(candidato.getNome()));
                }

                runOnUiThread(()->{
                    BarDataSet barDataSet = new BarDataSet(listEntradas, "Votos");
                    barDataSet.setValueTextSize(12f);
                    barDataSet.setDrawValues(true);

                    BarData barData = new BarData(barDataSet);
                    barData.setBarWidth(0.9f);

                    XAxis xAxis = grafico_barraHorizontal_estimulada.getXAxis();
                    xAxis.setGranularity(1f);
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setDrawGridLines(false);
                    xAxis.setCenterAxisLabels(false);
                    xAxis.setLabelCount(listNomes_candidatosEstimulada.size());

                    YAxis yAxis = grafico_barraHorizontal_estimulada.getAxisLeft();
                    yAxis.setAxisMinimum(0f);
                    yAxis.setAxisMaximum(100f);

                    grafico_barraHorizontal_estimulada.getAxisRight().setEnabled(false);
                    grafico_barraHorizontal_estimulada.setData(barData);
                    grafico_barraHorizontal_estimulada.setFitBars(true);
                    grafico_barraHorizontal_estimulada.invalidate();
                });
            }
        }).start();

        btn_voltar.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuResultadosActivity.class);
            startActivity(i);
            finish();
        });
    }
}