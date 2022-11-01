package com.example.appmobile.Vue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobile.R;
import com.example.appmobile.controleur.Controle;
import com.example.appmobile.modele.SaisieControle;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListadptRecycler extends RecyclerView.Adapter<ListadptRecycler.Myholder> implements RecyclerViewClickInterface{
    Context context;
    Controle controle;
    ArrayList nom_module;
    ArrayList moy_module;
    RecyclerViewClickInterface viewClickInterface;
    ArrayList<ArrayList> listmodules;
    ArrayList<ArrayList> s1;
    final float VIDE=-1;
    public ListadptRecycler(Context context,ArrayList<ArrayList> listmodules, ArrayList nom_module, ArrayList moy_module,RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.nom_module = nom_module;
        this.moy_module = moy_module;
        this.listmodules = listmodules;
        this.viewClickInterface=recyclerViewClickInterface;
        controle=Controle.Instance(context);

    }



    @NonNull
    @Override
    public ListadptRecycler.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_item,parent,false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadptRecycler.Myholder holder, int position) {
        try {

        holder.nom.setText(nom_module.get(position)+"");
        holder.moy.setText(moy_module.get(position)+"");
        holder.module.setText(listmodules.get(position).get(0)+"");
        holder.td.setText(listmodules.get(position).get(1)+"");
        holder.tp.setText(listmodules.get(position).get(2)+"");
        holder.emd.setText(listmodules.get(position).get(3)+"");
        holder.coef.setText(listmodules.get(position).get(5)+"");
        String mot=holder.nom.getText().toString().toUpperCase();
        holder.gmail.setText(mot.charAt(0)+"");
        }catch (Exception e){}
        holder.valide.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                try {
                    if (holder.module.getText().length() > 0) {
                        float note_td = VIDE;
                        float note_tp = VIDE;
                        String texttd = holder.td.getText().toString();
                        String texttp = holder.tp.getText().toString();
                        if (!texttd.equals("null")) {
                            note_td = Float.parseFloat(holder.td.getText() + "");
                        }
                        if (!texttp.equals("null")) {
                            note_tp = Float.parseFloat(holder.tp.getText() + "");
                        }
                        if (new SaisieControle().controle_de_saisie(context, note_td, note_tp, Float.parseFloat(holder.emd.getText() + "")) == true) {
                            String module_modifier = holder.module.getText() + "";
                            String td_modifier = note_td + "";
                            String tp_modifier = note_tp + "";
                            String emd_modifier = holder.emd.getText() + "";
                            String coef_modifier = holder.coef.getText() + "";
                            System.out.println(holder.nom.getText() + "");

//controle.modifier(module_modifier, 1+"", 1+"", emd_modifier, coef_modifier, holder.nom.getText() + "")+""
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println();

                            holder.moy.setText(controle.modifier(module_modifier, td_modifier, tp_modifier, emd_modifier, coef_modifier, holder.nom.getText() + "") + "");
                            holder.nom.setText(module_modifier);
                            holder.cl.setVisibility(View.GONE);
                        } else
                            Toast.makeText(context, "0<=Note<=20", 2000).show();
                    } else
                        Toast.makeText(context, "Saisie incorecte", 2000).show();
                }catch (Exception e){}

            }
        });

        holder.gmail.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(holder.cl, new AutoTransition());

                holder.cl.setVisibility((holder.cl.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);

            }
        });


    }


    @Override
    public int getItemCount() {
        return listmodules.size();
    }

    @Override
    public void onitemclick(int position) {

    }

    public class Myholder extends RecyclerView.ViewHolder{
        TextView nom,moy,gmail;
        EditText module,td,tp,emd,coef;
        ConstraintLayout cl,ln;
        ImageView valide;

        public Myholder(@NonNull View itemView) {

            super(itemView);
            nom = itemView.findViewById(R.id.nam);
            moy = itemView.findViewById(R.id.moy);
            module=itemView.findViewById(R.id.modulesc);
            td = itemView.findViewById(R.id.tdc);
            tp = itemView.findViewById(R.id.tpc);
            emd = itemView.findViewById(R.id.emdc);
            coef = itemView.findViewById(R.id.coefc);
            gmail = itemView.findViewById(R.id.gmail);
            ln = itemView.findViewById(R.id.ln);
            cl = itemView.findViewById(R.id.cl);
            valide = itemView.findViewById(R.id.validec);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewClickInterface.onitemclick(getAdapterPosition());
                }
            });
        }
    }

}
