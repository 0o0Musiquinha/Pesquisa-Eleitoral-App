package com.example.pesquisa_eleitoral.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pesquisa_eleitoral.R;
import com.example.pesquisa_eleitoral.models.Eleitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EleitorAdapter extends BaseAdapter {
    Context context;

    List<Eleitor> listEleitores;

    LayoutInflater inflater;

    public EleitorAdapter(Context context, List<Eleitor> listEleitores) {
        this.context = context;
        this.listEleitores = listEleitores;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listEleitores.size();
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
        convertView = inflater.inflate(R.layout.eleitor, null);
        Eleitor eleitor = listEleitores.get(position);

        TextView txt_titulo = convertView.findViewById(R.id.eleitor_txt_id);
        TextView txt_nome = convertView.findViewById(R.id.eleitor_txt_nome);
        TextView txt_celular = convertView.findViewById(R.id.eleitor_txt_celular);
        TextView txt_datahora = convertView.findViewById(R.id.eleitor_txt_datahora);
        TextView txt_latitude = convertView.findViewById(R.id.eleitor_txt_latitude);
        TextView txt_longitude = convertView.findViewById(R.id.eleitor_txt_longitude);

        txt_titulo.setText("Eleitor " + eleitor.getId());
        txt_nome.setText("Nome: " + eleitor.getNome());
        txt_celular.setText("Celular: " + eleitor.getCelular());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String datahora_formatado = formato.format(new Date(eleitor.getDataHora()));
        txt_datahora.setText("Data/Hora: " + datahora_formatado);

        txt_latitude.setText("Latitude: " + eleitor.getLatitude());
        txt_longitude.setText("Longitude: " + eleitor.getLongitude());


        return convertView;
    }
}
