package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragmento extends Fragment
{
    protected MediaPlayer mMediaPlayer;
    protected AudioManager mAudioManager;

    private ArrayList<Palavra> palavras = new ArrayList<>();
    private String corPadrao;

    @Override
    public void onStop()
    {
        super.onStop();
    }

    protected void mandarArray(ArrayList<Palavra> _array)
    {
        palavras = _array;
    }

    protected void mandarCor(String _corPadrao)
    {
        corPadrao = _corPadrao;
    }

    protected MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            releaseMediaPlayer();
        }
    };

    protected void releaseMediaPlayer()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    protected AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange)
        {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lista_palavras, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        PalavraAdapter  intensAdapter;
        if(corPadrao == "numeros")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_numeros);
        }
        else if(corPadrao == "cores")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_cores);
        }
        else if(corPadrao == "frases")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_frases);
        }
        else{
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_familia);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(intensAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Palavra palavraClicada = palavras.get(position);
                releaseMediaPlayer();

                int resultado = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener
                        , AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (resultado == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mMediaPlayer = MediaPlayer.create(getActivity(), palavraClicada.getReferenciaAudio());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }
}
