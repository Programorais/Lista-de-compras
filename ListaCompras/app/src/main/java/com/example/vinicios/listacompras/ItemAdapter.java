package com.example.vinicios.listacompras;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
        implements View.OnClickListener{

    private List<Item> mData;
    private View.OnClickListener listener;

    public ItemAdapter(List<Item> myData) {
        mData = myData;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idImportante;
        public TextView nomeDoItem;
        public TextView comentario;

        public ViewHolder(View v) {
            super(v);
            idImportante = (TextView) v.findViewById(R.id.id_importante);
            nomeDoItem = (TextView)v.findViewById(R.id.nome_do_item);
            comentario = (TextView)v.findViewById(R.id.comentario);
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_view, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.idImportante.setText(mData.get(position).isImportante() ? "Importante" : "");
        holder.nomeDoItem.setText(mData.get(position).getNome());
        holder.comentario.setText(mData.get(position).getComentario());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

}
