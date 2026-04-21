package com.example.pesquisa_eleitoral.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pesquisa_eleitoral.DAO.CandidatoDAO;
import com.example.pesquisa_eleitoral.DAO.Candidato_EspontaneoDAO;
import com.example.pesquisa_eleitoral.DAO.EleitorDAO;
import com.example.pesquisa_eleitoral.DAO.ProblemaDAO;
import com.example.pesquisa_eleitoral.DAO.UsuarioDAO;
import com.example.pesquisa_eleitoral.models.Candidato;
import com.example.pesquisa_eleitoral.models.Candidato_Espontaneo;
import com.example.pesquisa_eleitoral.models.Eleitor;
import com.example.pesquisa_eleitoral.models.Problema;
import com.example.pesquisa_eleitoral.models.Usuario;

@Database(entities = {
        Candidato.class,
        Candidato_Espontaneo.class,
        Usuario.class,
        Eleitor.class,
        Problema.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract CandidatoDAO candidatoDAO();
    public abstract Candidato_EspontaneoDAO candidato_espontaneoDAO();
    public abstract UsuarioDAO usuarioDAO();
    public abstract EleitorDAO eleitorDAO();
    public abstract ProblemaDAO problemaDAO();

    public static AppDatabase getInstance(final Context context){
        synchronized (AppDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "Pesquisa_Eleitoral")
                        .fallbackToDestructiveMigration(true)
                        .build();
            }

        }
        return INSTANCE;
    }


}
