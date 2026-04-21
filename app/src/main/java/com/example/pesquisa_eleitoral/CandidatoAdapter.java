package com.example.pesquisa_eleitoral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pesquisa_eleitoral.models.Candidato;

import java.util.List;

public class CandidatoAdapter extends BaseAdapter {

    Context context;
    List<Candidato> listCandidatos;
    LayoutInflater inflater;

    public CandidatoAdapter(Context context, List<Candidato> listCandidatos){
        this.context = context;
        this.listCandidatos = listCandidatos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCandidatos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.candidato, null);
        Candidato candidato = listCandidatos.get(position);

        TextView candidato_nome = convertView.findViewById(R.id.candidato_txt_nome);
        TextView candidato_partido = convertView.findViewById(R.id.candidato_txt_partido);
        ImageView candidato_img = convertView.findViewById(R.id.candidato_img);

        candidato_nome.setText(candidato.getNome());
        candidato_partido.setText(candidato.getPartido());

        //Transformar a String imagem em um int de modelo legivel ao @drawable do AndroidStudio
        int img = context.getResources().getIdentifier(
                candidato.getImg(),
                "drawable",
                context.getPackageName()
        );
        candidato_img.setImageResource(img);
        return convertView;
    }
}
