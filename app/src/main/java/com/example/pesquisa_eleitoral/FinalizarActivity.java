package com.example.pesquisa_eleitoral;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Adapter.ProblemaAdapter;
import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Candidato_Espontaneo;
import com.example.pesquisa_eleitoral.models.Eleitor;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class FinalizarActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    Button btn_finalizar;

    EditText ed_nome, ed_celular;

    String nome, celular, candidato_espontanea;

    int id_estimulada;

    List<String> problemas, remover_problemas;

    double latitude, longitude;

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

        requestPermissions();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        ed_celular = findViewById(R.id.finalizar_ed_celular);
        ed_nome = findViewById(R.id.finalizar_ed_nome);
        btn_finalizar = findViewById(R.id.finalizar_btn_finalizar);

        AppDatabase bd = AppDatabase.getInstance(this);

        problemas = ProblemaAdapter.listSelectedProblema;

        candidato_espontanea = getIntent().getStringExtra("CANDIDATO_ESPONTANEA");
        id_estimulada = getIntent().getIntExtra("ID_ESTIMULADA", 0);

        btn_finalizar.setOnClickListener(v -> {
            nome = String.valueOf(ed_nome.getText());
            celular = String.valueOf(ed_celular.getText());

            btn_finalizar.setEnabled(false);

            //Caso o entrevistado decida se manter anonimo, substitui os valores dos campos "nome" e "celular"
            if(nome.equals("")){
                nome = "Anônimo";
            }
            if(celular.equals("")){
                celular = "Anônimo";
            }

            new Thread(()->{
                //Salva os dados da pesquisa
                salvarPesquisa(nome, celular, latitude, longitude, candidato_espontanea, id_estimulada, problemas, bd);

                runOnUiThread(() ->{
                    System.out.println("Removido - " + problemas.get(0));

                    CharSequence text = "Pesquisa salva com sucesso!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this, text, duration);
                    toast.show();

                    Intent i = new Intent(this, MenuPesquisasActivity.class);
                    startActivity(i);
                    finish();
                });

            }).start();


        });
    }

    private void salvarPesquisa(
            String nome,
            String celular,
            double latitude,
            double longitude,
            String candidado_espontanea,
            int id_estimulada,
            List<String> problemas,
            AppDatabase bd
    ) {
        preencherEleitor(nome, celular, latitude, longitude, bd);

        Candidato_Espontaneo isStored = bd.candidato_espontaneoDAO().findByName(candidado_espontanea);
        //Verifica se o "Candidato" mencionado já existe
        if(isStored == null){
            //Se não existir, insere o candidato com 1 voto
            preencherCandidato_Espontanea(candidado_espontanea, bd);
        }else{
            //Se existir, aumenta um voto no registro do candidado mencionado na "Pesquisa Espontanea"
            bd.candidato_espontaneoDAO().updateVotos(isStored.getId());
        }

        //Aumenta 1 voto no registro do candidato escolhido na "Pesquisa Estimulada"
        bd.candidatoDAO().updateVotos(id_estimulada);

        //Aumenta 1 voto no(s) registro(s) do(s) problema(s) escolhido(s) na "Pesquisa de Concernimentos"
        for (int i = 0; i < problemas.size(); i++){
            bd.problemaDAO().updateVotos(problemas.get(i));
        }

        ProblemaAdapter.listSelectedProblema = new ArrayList<>();
    }

    private void preencherEleitor(String nome, String celular, double latitude, double longitude, AppDatabase bd){
        Eleitor eleitor = new Eleitor();
        eleitor.setNome(nome);
        eleitor.setCelular(celular);
        eleitor.setDataHora(System.currentTimeMillis());
        eleitor.setLatitude(latitude);
        eleitor.setLongitude(longitude);
        bd.eleitorDAO().insert(eleitor);
    }

    private void preencherCandidato_Espontanea(String nome, AppDatabase bd){
        Candidato_Espontaneo candidato_espontaneo = new Candidato_Espontaneo();
        candidato_espontaneo.setNome(nome);
        candidato_espontaneo.setQtdVotos(1);
        bd.candidato_espontaneoDAO().insert(candidato_espontaneo);
    }




    private void requestPermissions() {

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {

                            Boolean fineLocationGranted = null;
                            Boolean coarseLocationGranted = null;

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                fineLocationGranted = result.getOrDefault(
                                        Manifest.permission.ACCESS_FINE_LOCATION, false);
                                coarseLocationGranted = result.getOrDefault(
                                        Manifest.permission.ACCESS_COARSE_LOCATION,false);
                            }

                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                                getLocation();
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                                getLocation();
                            } else {
                                // No location access granted.
                                CharSequence text = "Localização necessária para salvar os dados!!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(FinalizarActivity.this, text, duration);
                                toast.show();
                            }
                        }
                );

        // ...

        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        } else{
                            CharSequence text = "Localização retornando vazia!!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(FinalizarActivity.this, text, duration);
                            toast.show();
                        }
                    }
                });
    }
}