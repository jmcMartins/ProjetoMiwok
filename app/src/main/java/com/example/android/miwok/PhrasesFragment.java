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

/**Classe do Fragmento de Frases, que herda os atributos de sua super classe*/
public class PhrasesFragment extends Fragmento
{
    /**Metodo capaz de enviar o Array e a cor para sua super classe com o conteudo desejado em seu fragmento*/
    public PhrasesFragment()
    {
        ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra("Onde você vai?", "Minto wuksus", R.raw.phrase_where_are_you_going));
        palavras.add(new Palavra("Qual é o seu nome?", "Tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        palavras.add(new Palavra("Meu nome é...", "Oyaaset...", R.raw.phrase_my_name_is));
        palavras.add(new Palavra("Como você está se sentindo?", "Michәksәs?", R.raw.phrase_how_are_you_feeling));
        palavras.add(new Palavra("Eu estou me sentindo bem.", "Kuchi achit", R.raw.phrase_im_feeling_good));
        palavras.add(new Palavra("Você está vindo?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        palavras.add(new Palavra("Sim, estou chegando.", "Hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        palavras.add(new Palavra("Estou chegando.", "әәnәm", R.raw.phrase_im_coming));
        palavras.add(new Palavra("Vamos.", "Yoowutis", R.raw.phrase_lets_go));
        palavras.add(new Palavra("Venha aqui.", "әnni'nem", R.raw.phrase_come_here));

        mandarCor("frases");
        mandarArray(palavras);
    }
}
