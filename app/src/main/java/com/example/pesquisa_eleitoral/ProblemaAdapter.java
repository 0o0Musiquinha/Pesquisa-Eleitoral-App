package com.example.pesquisa_eleitoral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ProblemaAdapter extends BaseAdapter {

    Context context;
    String[] listProblema;

    String[] listSelectedProblema;
    LayoutInflater inflater;

    public ProblemaAdapter(Context context, String[] listProblema){
        this.context = context;
        this.listProblema = listProblema;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listProblema.length;
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
        convertView = inflater.inflate(R.layout.problema, null);
        CheckBox problema = convertView.findViewById(R.id.problema_checkbox_problema);
        problema.setText(listProblema[position]);
        return convertView;
    }
}
