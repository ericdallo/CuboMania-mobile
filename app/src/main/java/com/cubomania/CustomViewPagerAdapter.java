package com.cubomania;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cubomania.Fragment.CartFragment;
import com.cubomania.Fragment.CubesFragment;
import com.cubomania.Fragment.EmptyCartFragment;

public class CustomViewPagerAdapter extends FragmentStatePagerAdapter {

    private final static int NUM_ITEMS = 2;
    private CubesFragment cubesFragment;
    private CartFragment cartFragment;
    private EmptyCartFragment emptyCartFragment;

    public CustomViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                this.cubesFragment = new CubesFragment();
                return cubesFragment;
            case 1:
                this.cartFragment = new CartFragment();
                return cartFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    //TO SEE
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Cubos";
            case 1:
                return "Carrinho";
            default:
                return "CuboMania";
        }
    }

    public CartFragment getCartFrag(){
        return this.cartFragment;
    }

}
