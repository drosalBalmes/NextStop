package com.example.a2223damp3grup01.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.objects.ParadaKilometre;

import org.w3c.dom.Text;

import java.util.List;

public class ParadesKilometresAdapter extends RecyclerView.Adapter<ParadesKilometresAdapter.MyViewHolder> {

    private List<ParadaKilometre> editParades;

    public ParadesKilometresAdapter(List<ParadaKilometre> editParades) {
        this.editParades = editParades;

    }


    @NonNull
    @Override
    public ParadesKilometresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate (R.layout.rv_parades_edittext_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParadesKilometresAdapter.MyViewHolder holder, int position){
        holder.bindData(editParades.get(position));
    }

    @Override
    public int getItemCount() {
        return editParades.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        EditText editText;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.EditTextParadesKM) ;
            textView = itemView.findViewById(R.id.numParadaRVET);

        }

        void bindData(final ParadaKilometre pk){
            textView.setText(pk.getNumParadaString());
            pk.setKmET(editText);

        }
    }
}
