package mx.com.serviciosinformaticosintegrales.nasa;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mx.com.serviciosinformaticosintegrales.nasa.adapters.NasaApodAdapter;
import mx.com.serviciosinformaticosintegrales.nasa.data.ApodService;
import mx.com.serviciosinformaticosintegrales.nasa.data.Data;
import mx.com.serviciosinformaticosintegrales.nasa.fragments.FragmentRecyclerview;
import mx.com.serviciosinformaticosintegrales.nasa.fragments.FragmentTodayApod;
import mx.com.serviciosinformaticosintegrales.nasa.fragments.RecyclerViewFavoritiesFragment;
import mx.com.serviciosinformaticosintegrales.nasa.model.Apod;
import mx.com.serviciosinformaticosintegrales.nasa.model.MarsRoverResponse;
import mx.com.serviciosinformaticosintegrales.nasa.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class RecyclerviewActivity extends AppCompatActivity
{
    //private RecyclerView lista;

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        toolbar = (Toolbar) findViewById(R.id.action_bar2);
        navigationView = (NavigationView)findViewById(R.id.listing_navigation_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.listing_navigation_drawer);

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "mx.com.serviciosinformaticosintegrales.old",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
        getFBUserInfo();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId())
                {
                    case R.id.today_apod_item:
                        changeFragmentTodayApod();
                        //Snackbar.make(findViewById(android.R.id.content), "Today Apod Item", Snackbar.LENGTH_SHORT).show();
                        return true;
                    case R.id.mars_rover_item:
                        changeFragmentRecyclerview();
                        //Snackbar.make(findViewById(android.R.id.content), "Mars Rover Item", Snackbar.LENGTH_SHORT).show();
                        return true;
                    case R.id.favorite_item:
                        changeFragmentFavorities();
                        //Snackbar.make(findViewById(android.R.id.content), "Favorite Item", Snackbar.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,
                toolbar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        changeFragmentTodayApod();
    }

    private void changeFragmentTodayApod() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentTodayApod()).commit();
    }

    private void changeFragmentRecyclerview() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new FragmentRecyclerview()).commit();
    }

    private void changeFragmentFavorities() {
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new RecyclerViewFavoritiesFragment()).commit();
    }


    private void getFBUserInfo()
    {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    SimpleDraweeView userImage = (SimpleDraweeView)findViewById(R.id.user_image);
                    TextView userName = (TextView)findViewById(R.id.user_name);
                    userImage.setImageURI("http://graph.facebook.com/" + object.getString("id") + "/picture?type=large");
                    userName.setText(object.getString("name"));
                    Log.d("nombre", object.getString("name"));
                    Log.d("id2", object.getString("id"));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
}
