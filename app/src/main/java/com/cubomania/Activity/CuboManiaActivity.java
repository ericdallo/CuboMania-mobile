package com.cubomania.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cubomania.Cube.Cube;
import com.cubomania.CustomViewPagerAdapter;
import com.cubomania.Fragment.CartFragment;
import com.cubomania.Image.FileCache;
import com.cubomania.Image.MemoryCache;
import com.cubomania.Interface.AddedToCart;
import com.cubomania.R;

public class CuboManiaActivity extends AppCompatActivity implements AddedToCart{

    private CartFragment cartFragment;
    private CustomViewPagerAdapter customViewPagerAcapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setPageTransformer(false, (page, position) -> {
            //page.setRotation(position * -30);
            final float normalizedPostion = Math.abs(Math.abs(position) - 1);
            page.setScaleX(normalizedPostion / 2 + 0.5f);
            page.setScaleY(normalizedPostion / 2 + 0.5f);
            page.setAlpha(normalizedPostion);
        });

        customViewPagerAcapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(customViewPagerAcapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            MemoryCache cache = new MemoryCache();
            cache.clear();
            FileCache fileCache = new FileCache(this);
            fileCache.clear();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addToCart(Cube currentCube) {
        cartFragment = customViewPagerAcapter.getCartFrag();
        cartFragment.addCube(currentCube,viewPager);

    }
}
