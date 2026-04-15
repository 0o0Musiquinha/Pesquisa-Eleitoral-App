package com.example.pesquisa_eleitoral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CandidatoAdapter extends BaseAdapter {

    Context context;
    String listCandidato[];

    String listPartido[];
    int listImg[];
    LayoutInflater inflater;

    public CandidatoAdapter(Context context, String[] listCandidato, String[] listPartido, int[] listImg){
        this.context = context;
        this.listCandidato = listCandidato;
        this.listPartido = listPartido;
        this.listImg = listImg;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listCandidato.length;
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
        candidato.setText(listCandidato[position]);
        partido.setText(listPartido[position]);
        img.setImageResource(listImg[position]);
        return convertView;
    }
}
