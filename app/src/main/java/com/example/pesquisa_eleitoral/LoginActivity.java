package com.example.pesquisa_eleitoral;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pesquisa_eleitoral.Database.AppDatabase;
import com.example.pesquisa_eleitoral.models.Usuario;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText editText_email, editText_senha;
    Button btn_acessar, btn_encerrar;
    String email, senha;

    List<Usuario> listUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText_email = findViewById(R.id.login_editText_email);
        editText_senha = findViewById(R.id.login_editPass_senha);

        btn_acessar = findViewById(R.id.login_btn_acessar);
        btn_encerrar = findViewById(R.id.login_btn_encerrar);

        AppDatabase bd = AppDatabase.getInstance(this);

        new Thread(()->{
            listUsuarios= bd.usuarioDAO().getAll();

            if (listUsuarios.isEmpty()){
                preencherUsuario("entrevistador@gmail.com", "entrevistador", 0, bd);
                preencherUsuario("admin@gmail.com", "admin", 1, bd);

                listUsuarios= bd.usuarioDAO().getAll();
                System.out.println("Inserção dos 'Usuarios' base concluida!");
            }

        }).start();

        btn_acessar.setOnClickListener(v->{
            email = String.valueOf(editText_email.getText());
            senha = String.valueOf((editText_senha.getText()));

            new Thread(()->{
                login(email, senha, bd);
            }).start();
        });

        btn_encerrar.setOnClickListener(v ->{
            finishAndRemoveTask();
        });
    }

    private void login(String email, String senha, AppDatabase bd){

        Usuario usuario = bd.usuarioDAO().verifyLogin(email,senha);

        runOnUiThread(()->{
            if (usuario != null) {
                int tipo = usuario.getTipo();
                Handler h = new Handler();

                if(tipo == 0){
                    h.postDelayed(() -> {
                        Intent i = new Intent(this, MenuPesquisasActivity.class);
                        startActivity(i);
                        finish();
                    }, 500);
                }

                if(tipo == 1){
                    h.postDelayed(() -> {
                        Intent i = new Intent(this, MenuResultadosActivity.class);
                        startActivity(i);
                        finish();
                    }, 500);
                }
            } else{

                    CharSequence text = "Credenciais Invalidas!!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(this , text, duration);
                    toast.show();
            }
        });
    }

    private void preencherUsuario(String email, String senha, int tipo, AppDatabase bd){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);
        bd.usuarioDAO().insert(usuario);
    }
}