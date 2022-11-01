package com.example.appmobile.Vue;

import android.os.Bundle;

import com.example.appmobile.R;
import com.example.appmobile.controleur.Controle;
import com.example.appmobile.modele.Semestre;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityCalculMoyenneSemestre extends AppCompatActivity {
    private Controle controle;
    private LockableViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_moyenne_semestre);
        init();



    }
    private void init(){
        tabLayout= findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setSwipeable(false);
        PageAdapt pa=new PageAdapt(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        pa.addfragment(new FragmentSem1());
        pa.addfragment(new FragmentSem2());
        pa.addtitle("Sem1");
        pa.addtitle("Sem2");
        viewPager.setAdapter(pa);
        controle=Controle.Instance(this);


    }
}