package com.example.appmobile.Vue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.appmobile.R;
import com.example.appmobile.controleur.Controle;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class FragmentSem1 extends Fragment implements RecyclerViewClickInterface{
    private Controle controle;
    private RecyclerView recyclerView;
    private ListadptRecycler listadptRecycler;
    private ArrayList<ArrayList> listesmodule;
    private ArrayList<String> nom_module,moyenne;
    private ImageButton buttonajouter;
    private ImageButton calculer;
    private ArcProgress arcProgress;
    float moyenne_gen=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
    View view=inflater.inflate(R.layout.fragment_sem1, container, false);
        buttonajouter=view.findViewById(R.id.ajouter);
        calculer=view.findViewById(R.id.calculer);
        arcProgress=view.findViewById(R.id.arc_progress);

        buttonajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ActivityCalculMoyenneModule.class));
                getActivity().finish();

            }
        });
        controle= Controle.Instance(inflater.getContext());

        //on recupere nos modules dans une Liste (listesmodule)
        listesmodule=controle.recuperer_modules();
        for (int i = 0; i < listesmodule.size(); i++) {
            System.out.println(listesmodule.get(0));
        }
        ArrayList modules_s1=new ArrayList();
        nom_module=new ArrayList<>();
         moyenne=new ArrayList<>();
        for (int i = 0; i < listesmodule.size(); i++) {
            //la colonne 8 c la colonne des semestre donc on mets une codition si il fait partie du s1
            // en l'ajoute a notre Liste qu'on donnera a notre liste adapteur
            if (listesmodule.get(i).get(7).equals("1")) {
                modules_s1.add(listesmodule.get(i));
                //
                nom_module.add(listesmodule.get(i).get(0) + "");
                moyenne.add(listesmodule.get(i).get(4) + "");
            }
        }

        calculer.setOnClickListener(new View.OnClickListener() {
            float i=0;
            @Override
            public void onClick(View v) {
                float somme_moyenne=0;
                float somme_coef=0;
                for (int i = 0; i < modules_s1.size(); i++) {
                    somme_moyenne+=Float.parseFloat((((ArrayList)modules_s1.get(i)).get(4))+"")*Float.parseFloat(((ArrayList)modules_s1.get(i)).get(5)+"");

                System.out.println("+++++++++++++++++++++++++||||+++++++++++++++"+(((ArrayList)(ArrayList)modules_s1.get(i)).get(3))+"");
                    somme_coef +=Float.parseFloat(((ArrayList)modules_s1.get(i)).get(5)+"");
                }
                System.out.println(""+(somme_moyenne));
                System.out.println("                       "+(somme_moyenne/somme_coef));
                System.out.println(""+(somme_coef));

                moyenne_gen=somme_moyenne/somme_coef;
                System.out.println("++++++++++++++++++++++++++++++++++++++++"+moyenne_gen);
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i>=moyenne_gen)timer.cancel();
                        arcProgress.setSuffixText("55");
//                        arcProgress.setProgress(i);
//                        i+=0.9;

                    }
                },500,50);
            }
        });
        recyclerView=view.findViewById(R.id.rcv1);
        listadptRecycler=new ListadptRecycler(getActivity(), modules_s1, nom_module, moyenne, new RecyclerViewClickInterface() {
            @Override
            public void onitemclick(int position) {

            }
        });
        recyclerView.setAdapter(listadptRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position=viewHolder.getAdapterPosition();
            String nom=((ArrayList)modules_s1.get(position)).get(0)+"";
            System.out.println(nom+"--------->supprimé");
            //on accede a la bd a dupuis la class controle
            controle.supprimer(nom);
            modules_s1.remove(position);

            listadptRecycler.notifyItemRemoved(position);

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onitemclick(int position) {
    }
}