package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragmento {

    public FamilyFragment()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra("Pai", "әpә", R.drawable.family_father, R.raw.family_father));
        palavras.add(new Palavra("Mãe", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        palavras.add(new Palavra("Filho", "Angsi", R.drawable.family_son, R.raw.family_son));
        palavras.add(new Palavra("Filha", "Tune", R.drawable.family_daughter, R.raw.family_daughter));
        palavras.add(new Palavra("Irmão mais velho", "Taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        palavras.add(new Palavra("Irmão mais novo", "Chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        palavras.add(new Palavra("Irmã mais velha", "Teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        palavras.add(new Palavra("Irmã mais nova", "Kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        palavras.add(new Palavra("Avó", "Ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        palavras.add(new Palavra("Avô", "Paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        mandarCor("familia");
        mandarArray(palavras);
    }
}
