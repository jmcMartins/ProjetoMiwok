package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**Classe no geral ira trabalhar controlando as abas de categorias do sistema*/
public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    /**Contrutor padrão da classe, passa o fragmento para sua super classe e salva o Context na classe*/
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**Retorna o fragmento solicitado*/
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    /**Retorna o titulo de cada fragmento do sistema*/
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.categoria_numeros);
        } else if (position == 1) {
            return mContext.getString(R.string.categoria_familia);
        } else if (position == 2) {
            return mContext.getString(R.string.categoria_cores);
        } else {
            return mContext.getString(R.string.categoria_frases);
        }
    }

    /**Retorna a quantidade de fragmentos que o sistema vai possuit*/
    @Override
    public int getCount() {
        return 4;
    }
}