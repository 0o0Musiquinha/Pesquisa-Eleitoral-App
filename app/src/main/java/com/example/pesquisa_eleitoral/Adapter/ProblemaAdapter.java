package com.example.pesquisa_eleitoral.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pesquisa_eleitoral.R;
import com.example.pesquisa_eleitoral.models.Problema;

import java.util.ArrayList;
import java.util.List;

public class ProblemaAdapter extends BaseAdapter {

    Context context;

    List<Problema> listProblema;

    public static List<String> listSelectedProblema = new ArrayList<>();

    boolean isSelecionado;

    LayoutInflater inflater;

    public ProblemaAdapter(Context context, List<Problema> listProblema){
        this.context = context;
        this.listProblema = listProblema;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listProblema.size();
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
        Problema problema = listProblema.get(position);

        convertView = inflater.inflate(R.layout.problema, null);
        CheckBox problema_check = convertView.findViewById(R.id.problema_checkbox_problema);
        TextView problema_txt = convertView.findViewById(R.id.problema_txt_nome);

        problema_txt.setText(problema.getNome());

        problema_check.setOnCheckedChangeListener(null);

        isSelecionado = listSelectedProblema.contains(problema.getNome());
        problema_check.setChecked(isSelecionado);

        if(listSelectedProblema.size() >= 3){
            problema_check.setEnabled(isSelecionado);
        }
        problema_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if(listSelectedProblema.size() < 3){
                        listSelectedProblema.add(problema.getNome());
                        System.out.println(problema.getNome() + " - Adicionado");
                    }
                } else{
                    listSelectedProblema.remove(problema.getNome());
                    System.out.println(problema.getNome() + " - Removido");
                }
                notifyDataSetChanged();
            }
        });


        return convertView;
    }
}
