package org.thereachtrust.wallpaper;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.thereachtrust.wallpaper.Utils.Functions;
import org.thereachtrust.wallpaper.databinding.ActivityMainBinding;
import org.thereachtrust.wallpaper.ui.collections.CollectionsFragment;
import org.thereachtrust.wallpaper.ui.favorite.FavoriteFragment;
import org.thereachtrust.wallpaper.ui.photo.PhotoFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_photo, R.id.nav_collections, R.id.nav_favorite)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.main_container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //PhotoFragment photoFragment= new PhotoFragment();
        //Functions.changeMainFragment(MainActivity.this, photoFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.main_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle action bar item click
        int id= item.getItemId();
        if (id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        //Handle the navigation view of items

        int id= item.getItemId();
        if (id==R.id.nav_photo){
            //Handle the photo action
            PhotoFragment photoFragment= new PhotoFragment();
            Functions.changeMainFragment(MainActivity.this, photoFragment);
        }
        else if(id==R.id.nav_collections){
            //Handle the collections action
            CollectionsFragment collectionsFragment= new CollectionsFragment();
            Functions.changeMainFragment(MainActivity.this, collectionsFragment);
        }
        else if(id==R.id.nav_favorite){
            //Handle the favorite action
            FavoriteFragment favoriteFragment= new FavoriteFragment();
            Functions.changeMainFragment(MainActivity.this, favoriteFragment);
        }
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}