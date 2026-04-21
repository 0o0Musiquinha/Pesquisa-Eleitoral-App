package com.example.pesquisa_eleitoral;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class ProblemaAdapter extends BaseAdapter {

    Context context;
    String[] listProblema;

    List<String> listSelectedProblema = new ArrayList<>();
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

        problema.setOnCheckedChangeListener(null);

        if(listSelectedProblema.size() >= 3){
            for(int i = 0; i < listSelectedProblema.size(); i++){
                for (int j = 0; j < listProblema.length; j++){
                    if (listSelectedProblema.get(i).equals(listProblema[j])){
                        problema.setEnabled(true);
                    } else {
                        problema.setEnabled(false);
                    }
                }
            }
        }
        problema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if(listSelectedProblema.size() < 3){
                        listSelectedProblema.add(String.valueOf(problema.getText()));
                    }
                } else{

                }
                notifyDataSetChanged();
                System.out.println(listProblema[position]);
            }
        });
        return convertView;
    }
}
