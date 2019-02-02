package br.com.mrocigno.estalagemnerd.config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.localdata.Preferences;

public abstract class AbstractActivity extends AppCompatActivity {
    public String TAG = "TESTEEE";

    public FloatingActionButton fabBtn_Default;
    FrameLayout defaultContainer;
    Toolbar toolbar;
    ProgressBar pgrBar_Deafult;
    SearchView srvSearch_Default;
    ImageView imgLogo_Default;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        boolean darkTheme = Preferences.getInstace(getActivity()).isDarkTheme();
        if(darkTheme){
            this.setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);

        defaultContainer = findViewById(R.id.defaultContainer);
        fabBtn_Default = findViewById(R.id.fabBtn_Default);
        pgrBar_Deafult = findViewById(R.id.pgrBar_Deafult);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(showToolbar()){
            srvSearch_Default = findViewById(R.id.srvSearch_Default);
            imgLogo_Default = findViewById(R.id.imgLogo_Default);

            srvSearch_Default.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchViewClick();
                }
            });

            srvSearch_Default.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    onSearchViewTextSubmit(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    onSearchViewTextChange(s);
                    return false;
                }
            });

            srvSearch_Default.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    onSearchViewClose();
                    return false;
                }
            });
        }else{
            toolbar.setVisibility(View.GONE);
            pgrBar_Deafult.setVisibility(View.GONE);
        }

        View v = getLayoutInflater().inflate(getLayoutRes(), null);
        defaultContainer.addView(v);

        initVars();
        customCreate(savedInstanceState);
    }

    public void onSearchViewClose() {
        imgLogo_Default.setVisibility(View.VISIBLE);
        toolbar.inflateMenu(R.menu.main_menu);
    }

    public void onSearchViewClick() {
        imgLogo_Default.setVisibility(View.GONE);
        toolbar.getMenu().clear();
    }

    public void onSearchViewTextSubmit(String s){

    }

    public void onSearchViewTextChange(String s){

    }

    public abstract int getLayoutRes();

    public abstract void customCreate(Bundle savedInstanceState);

    public abstract void initVars();

    public Activity getActivity() {
        return this;
    }

    public void setProgressbarVisible(boolean visible){
        pgrBar_Deafult.setVisibility(visible? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if(Preferences.getInstace(getActivity()).isDarkTheme()){
            menu.getItem(0).setTitle("Tema claro");
        }
        return true;
    }

    public SearchView getSrvSearch_Default() {
        return srvSearch_Default;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuDarkTheme) {
            Preferences pref = Preferences.getInstace(getActivity());
            if(pref.isDarkTheme()){
                pref.setDarkTheme(false);
                item.setTitle("Tema escuro");
            }else{
                pref.setDarkTheme(true);
                item.setTitle("Tema claro");
            }
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSearchView(boolean show){
        srvSearch_Default.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setScrollFlag(){
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(srvSearch_Default != null && !srvSearch_Default.isIconified()){
            srvSearch_Default.setQuery("", false);
            srvSearch_Default.setIconified(true);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Runtime.getRuntime().gc();
        System.gc();
    }

    public boolean showToolbar(){
        return true;
    }

    @SuppressLint("RestrictedApi")
    public void showFab(boolean show, Drawable drawable){
        fabBtn_Default.setVisibility(show? View.VISIBLE: View.GONE);
        if(show){
            fabBtn_Default.setImageDrawable(drawable);
            fabBtn_Default.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickFab();
                }
            });
        }
    }

    public void onClickFab(){}

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId, String tag) {
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment, tag);
            transaction.commit();
        }
    }

    public static void showFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
//        if (!fragment.isAdded()) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
            if(fragmentManager.getFragments().get(i) != fragment) {
                transaction.hide(fragmentManager.getFragments().get(i));
            }
        }
        transaction.commit();
//        }
    }

    public static void removeFragmentToActivity(@NonNull FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
            transaction.remove(fragmentManager.getFragments().get(i));
        }
        transaction.commit();
    }

}
