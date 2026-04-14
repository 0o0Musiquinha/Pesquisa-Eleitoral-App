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

public class LoginActivity extends AppCompatActivity {
    EditText editText_email, editText_senha;
    Button btn_acessar, btn_encerrar;
    String email, senha;

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

        btn_acessar.setOnClickListener(v->{
            login();
        });

        btn_encerrar.setOnClickListener(v ->{
            finishAndRemoveTask();
        });
    }

    private void login(){
        email = String.valueOf(editText_email.getText());
        senha = String.valueOf((editText_senha.getText()));

        if (email.equals("admin@gmail.com") && senha.equals("admin")){
            Handler h = new Handler();
            h.postDelayed(() -> {
                Intent i = new Intent(LoginActivity.this, MenuResultadosActivity.class);
                startActivity(i);
                finish();
            }, 500);
        } else if(email.equals("entrevistador@gmail.com") && senha.equals("entevistador")){
            Handler h = new Handler();
            h.postDelayed(() -> {
                Intent i = new Intent(LoginActivity.this, MenuResultadosActivity.class);
                startActivity(i);
                finish();
            }, 500);
        }else{
            CharSequence text = "Credenciais Invalidas!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(LoginActivity.this , text, duration);
            toast.show();
        }
    }
}