package com.example.vinicios.listacompras;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CustomDialog.OnChooseOption, AddDialog.OnChooseOption{

    private static final String TAG = "MainActivity";//para testes Log.i
    private RecyclerView recycler;
    private Button btMostrarImportante;
    private Button btOrdenar;
    private Button btAdd;
    private int ij;
    private List<Item> lista;
    private ItemAdapter adapter;
    private boolean voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ij = 0;
        setUI();
        setRecyclerView();
        setActions();
    }

    private void setUI(){
        recycler = (RecyclerView) findViewById(R.id.itens_recycler_view);
        btMostrarImportante = (Button) findViewById(R.id.bt_mostrar_importante);
        btOrdenar = (Button) findViewById(R.id.bt_ordenar);
        btAdd = (Button) findViewById(R.id.bt_add);
    }

    private void setRecyclerView(){
        lista = new ArrayList<>();
        lista.add(new Item("Pães", "10 pães franceses", false));
        lista.add(new Item("DICA!", "Toque DUAS VEZES em um item para mais opções.", true));


        adapter = new ItemAdapter(lista);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);

        setting();

        recycler.setAdapter(adapter);
    }

    private void setting(){
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = recycler.getChildLayoutPosition(view);

                ij++ ;
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        ij = 0;
                    }
                };
                if(ij==1){
                    handler.postDelayed(r, 250);
                }else if(ij == 2){
                    Dialog dialog = new CustomDialog().showDialog(MainActivity.this, position,
                            lista.get(position).isImportante());
                    dialog.show();
                }


            }
        });
    }

    private void setActions(){
        btMostrarImportante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!voltar){
                    List<Item> tmpLista = new ArrayList<>();

                    for(Item item : lista){
                        if(item.isImportante()){
                            tmpLista.add(item);
                        }
                    }

                    adapter = new ItemAdapter(tmpLista);
                    recycler.setAdapter(adapter);
                    voltar = true;
                    btMostrarImportante.setText(getString(R.string.s_voltar));
                }else{
                    adapter = new ItemAdapter(lista);
                    setting();
                    recycler.setAdapter(adapter);
                    voltar =false;
                    btMostrarImportante.setText(getString(R.string.s_important));
                }

            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voltar){
                    adapter = new ItemAdapter(lista);
                    setting();
                    recycler.setAdapter(adapter);
                    voltar = false;
                }

                Dialog Add = new AddDialog().showDialog(MainActivity.this);
                Add.show();

            }
        });

        btOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sorting
                Collections.sort(lista, new Comparator<Item>() {
                    @Override
                    public int compare(Item item2, Item item1)
                    {
                        return  item2.getNome().compareTo(item1.getNome());
                    }
                });

                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onImportante(int position) {
        lista.get(position).setImportante(!lista.get(position).isImportante());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onExcluir(int position) {
        lista.remove(position);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onConfirma(String nome, String coment) {
        lista.add(new Item(nome, coment, false));
        adapter.notifyDataSetChanged();
    }
}

