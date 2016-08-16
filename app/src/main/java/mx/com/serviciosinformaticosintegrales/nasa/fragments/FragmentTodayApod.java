package mx.com.serviciosinformaticosintegrales.nasa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.serviciosinformaticosintegrales.nasa.BuildConfig;
import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.data.ApodService;
import mx.com.serviciosinformaticosintegrales.nasa.data.Data;
import mx.com.serviciosinformaticosintegrales.nasa.model.Apod;
import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;
import mx.com.serviciosinformaticosintegrales.nasa.sql.ItemDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class FragmentTodayApod extends Fragment {
    ImageView img;
    TextView txvTitulo;
    TextView txvDescripcion;
    TextView txvFecha;
    TextView txvCopyright;
    String imgURL;
    //View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_apod_fragment,container,false);
        txvTitulo=(TextView) view.findViewById(R.id.titulo);
        txvDescripcion=(TextView) view.findViewById(R.id.descripcion);
        txvFecha = (TextView) view.findViewById(R.id.fecha);
        txvCopyright = (TextView) view.findViewById(R.id.copyright);
        img = (ImageView)view.findViewById(R.id.imagen);

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("K41viBmlCl1h6q5XdIg85oTJF6bcBangI5jWrdwe");
        callApodService.enqueue(new Callback<Apod>()
        {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response)
            {
                txvTitulo.setText(response.body().getTitle());
                txvDescripcion.setText(response.body().getExplanation());
                txvFecha.setText(response.body().getDate());
                if(response.body().getCopyright()==null)
                {
                    txvCopyright.setText("");
                    txvCopyright.setVisibility(View.GONE);
                }
                else
                {
                    txvCopyright.setText("by " + response.body().getCopyright());
                }

                Picasso.with(getActivity()).load(response.body().getUrl()).into(img);
                imgURL = response.body().getHdurl();
                //Log.d("APOD", response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t)
            {

            }
        });




        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share_today_apod:
                //Snackbar.make(getView(), "Se agregó a favoritos", Snackbar.LENGTH_SHORT).show();
                shareText("Hola Mundo " + imgURL);
                return true;
            case R.id.favorites_today_apod:
                saveFavorities();
                Snackbar.make(getView(), "Se agregó a favoritos", Snackbar.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText(String text)
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "Compartir"));
    }

    private void saveFavorities(){
        Favorities favorities = new Favorities();
        favorities.setTitulo(txvTitulo.getText().toString());
        favorities.setFoto(imgURL);
        ItemDataSource itemDataSource = new ItemDataSource(getActivity());
        itemDataSource.saveItem(favorities);
    }



}
