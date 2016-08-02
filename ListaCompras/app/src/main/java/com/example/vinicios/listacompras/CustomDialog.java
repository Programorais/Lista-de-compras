package com.example.vinicios.listacompras;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialog {

    private Dialog dialog;
    private OnChooseOption mListener;

    public Dialog showDialog(final Context context, final int position, boolean importante){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.layout_dialog, null);

        Button btImportante = (Button) view.findViewById(R.id.bt_importante);
        Button btDelete = (Button) view.findViewById(R.id.bt_delete);

        btImportante.setText(importante? context.getString(R.string.s_its_not_important) :
                context.getString(R.string.s_its_important));

        btImportante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener = (OnChooseOption) context;
                mListener.onImportante(position);
                dialog.dismiss();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener = (OnChooseOption) context;
                mListener.onExcluir(position);
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();

        return dialog;

    }



    public interface OnChooseOption{
        void onImportante(int position);
        void onExcluir(int position);
    }



}
