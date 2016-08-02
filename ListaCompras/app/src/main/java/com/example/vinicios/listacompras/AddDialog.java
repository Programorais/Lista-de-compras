package com.example.vinicios.listacompras;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddDialog {

    private Dialog dialog;
    private OnChooseOption mListener;
    private String nome;
    private String comentario;

    public Dialog showDialog(final Context context){

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.layout_dialog_add, null);

        Button btCancela = (Button) view.findViewById(R.id.bt_cancelar);
        Button btConfirma = (Button) view.findViewById(R.id.bt_OK);
        final TextView TVnome = (TextView) view.findViewById(R.id.editText_nome);
        final TextView TVcomentario = (TextView) view.findViewById(R.id.editText_comentario);


        btCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener = (OnChooseOption) context;
                dialog.dismiss();
            }
        });

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome = TVnome.getText().toString();
                comentario = TVcomentario.getText().toString();
                mListener = (OnChooseOption) context;
                mListener.onConfirma(nome, comentario);
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.create();

        return dialog;

    }

    public interface OnChooseOption{
        void onConfirma(String nome, String coment);
    }

}
