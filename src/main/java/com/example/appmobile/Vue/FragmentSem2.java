package com.example.appmobile.Vue;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appmobile.R;
import com.example.appmobile.controleur.Controle;

import java.util.ArrayList;


public class FragmentSem2 extends Fragment implements RecyclerViewClickInterface{
    private Controle controle;
    private RecyclerView recyclerView;
    private ListadptRecycler listadptRecycler;
    private ArrayList<ArrayList> listesmodules;
    private ArrayList<String> nom_modules2,moyenne;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_sem2, container, false);
        controle= Controle.Instance(getActivity());

        //on recupere nos modules dans une Liste (listesmodule)

        listesmodules =controle.recuperer_modules();
        ArrayList modules_s2=new ArrayList();
        nom_modules2 =new ArrayList<>();
        moyenne=new ArrayList<>();
        for (int i = 0; i < listesmodules.size(); i++) {
            //la colonne 8 c la colonne des semestre donc on mets une codition si il fait partie du s2
            // en l'ajoute a notre Liste qu'on donnera a notre liste adapteur
            if (listesmodules.get(i).get(7).equals("2")) {
                modules_s2.add(listesmodules.get(i));
                //
                nom_modules2.add(listesmodules.get(i).get(0) + "");
                moyenne.add(listesmodules.get(i).get(4) + "");
            }
        }

        recyclerView=view.findViewById(R.id.rcv2);
        listadptRecycler=new ListadptRecycler(getActivity(), modules_s2, nom_modules2, moyenne, this);
        recyclerView.setAdapter(listadptRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                String nom= nom_modules2.get(position);
                //on accede a la bd a dupuis la class controle
                controle.supprimer(nom);
                modules_s2.remove(position);

                listadptRecycler.notifyItemRemoved(position);

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;

    }

    @Override
    public void onitemclick(int position) {

    }
}