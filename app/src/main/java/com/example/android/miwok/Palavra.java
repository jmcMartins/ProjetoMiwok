package com.example.android.miwok;

public class Palavra {

    private String mPalavraPadrao;
    private String mPalavraMiwok;
    private int mReferenciaImagem = SEM_IMAGEM_FORNECIDA;
    private int mReferenciaAudio;
    private static final int SEM_IMAGEM_FORNECIDA = -1;

    public Palavra (String traducaoPadrao, String traducaoMiwok,
                    int referenciaAudio)
    {
        mPalavraMiwok = traducaoMiwok;
        mPalavraPadrao = traducaoPadrao;
        mReferenciaAudio = referenciaAudio;
    }

    public Palavra (String traducaoPadrao, String traducaoMiwok,
                    int referenciaImagem, int referenciaAudio)
    {
        mPalavraMiwok = traducaoMiwok;
        mPalavraPadrao = traducaoPadrao;
        mReferenciaImagem = referenciaImagem;
        mReferenciaAudio = referenciaAudio;
    }

    public boolean hasImagem() {
        return mReferenciaImagem != SEM_IMAGEM_FORNECIDA;
    }
    public String getTraducaoPadrao(){
        return mPalavraPadrao;
    }
    public String getTraducaoMiwok() {
        return mPalavraMiwok;
    }
    public int getReferenciaImagem() {return mReferenciaImagem;}
    public int getReferenciaAudio() {return mReferenciaAudio;}
}