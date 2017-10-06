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

public class ColorsFragment extends Fragmento
{
    public ColorsFragment()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra("Vermelho", "Wetetti", R.drawable.color_red, R.raw.color_red));
        palavras.add(new Palavra("Verde", "Chokokki", R.drawable.color_green, R.raw.color_green));
        palavras.add(new Palavra("Castanho", "Takaakki", R.drawable.color_brown, R.raw.color_brown));
        palavras.add(new Palavra("Cinzento", "Topoppi", R.drawable.color_gray, R.raw.color_gray));
        palavras.add(new Palavra("Preto", "Kululli", R.drawable.color_black, R.raw.color_black));
        palavras.add(new Palavra("Branco", "Kelelli", R.drawable.color_white, R.raw.color_white));
        palavras.add(new Palavra("Amarelo Empoeirado", "Topiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        palavras.add(new Palavra("Amarelo Mostarda", "Chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        mandarCor("cores");
        mandarArray(palavras);
    }
}
