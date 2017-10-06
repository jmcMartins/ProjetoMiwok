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

import static com.example.android.miwok.R.id.miwok;

public class NumbersFragment extends Fragmento
{
    public NumbersFragment()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra("Um", "Lutti", R.drawable.number_one, R.raw.number_one));
        palavras.add(new Palavra("Dois", "Otiiko", R.drawable.number_two, R.raw.number_two));
        palavras.add(new Palavra("Três", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        palavras.add(new Palavra("Quatro", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        palavras.add(new Palavra("Cinco", "Massokka", R.drawable.number_five, R.raw.number_five));
        palavras.add(new Palavra("Seis", "Temmokka", R.drawable.number_six, R.raw.number_six));
        palavras.add(new Palavra("Sete", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        palavras.add(new Palavra("Oito", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        palavras.add(new Palavra("Nove", "Wo’e", R.drawable.number_nine, R.raw.number_nine));
        palavras.add(new Palavra("Dez", "Na’aacha", R.drawable.number_ten, R.raw.number_ten));

        mandarCor("numeros");
        mandarArray(palavras);
    }
}