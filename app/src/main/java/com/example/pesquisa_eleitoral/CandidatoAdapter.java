package com.example.pesquisa_eleitoral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CandidatoAdapter extends BaseAdapter {

    Context context;
    List<String> listCandidato;

    List<String> listPartido;
    List<String> listImg;
    LayoutInflater inflater;

    public CandidatoAdapter(Context context, List<String> listCandidato, List<String> listPartido, List<String> listImg){
        this.context = context;
        this.listCandidato = listCandidato;
        this.listPartido = listPartido;
        this.listImg = listImg;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCandidato.size();
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
        TextView candidato = convertView.findViewById(R.id.candidato_txt_nome);
        TextView partido = convertView.findViewById(R.id.candidato_txt_partido);
        ImageView img = convertView.findViewById(R.id.candidato_img);
        candidato.setText(listCandidato.get(position));
        partido.setText(listPartido.get(position));

        int resId = context.getResources().getIdentifier(
                listImg.get(position),
                "drawable",
                context.getPackageName()
        );
        img.setImageResource(resId);
        return convertView;
    }
}
