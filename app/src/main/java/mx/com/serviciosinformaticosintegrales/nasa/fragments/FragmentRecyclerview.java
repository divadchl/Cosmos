package mx.com.serviciosinformaticosintegrales.nasa.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.adapters.NasaApodAdapter;
import mx.com.serviciosinformaticosintegrales.nasa.data.ApodService;
import mx.com.serviciosinformaticosintegrales.nasa.data.Data;
import mx.com.serviciosinformaticosintegrales.nasa.model.MarsRoverResponse;
import mx.com.serviciosinformaticosintegrales.nasa.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRecyclerview extends Fragment {

    private RecyclerView lista;
    //private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_fragment,container,false);

        lista = (RecyclerView) view.findViewById(R.id.rvListado);

        //LinearLayoutManager llm = new LinearLayoutManager(this);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                10,StaggeredGridLayoutManager.VERTICAL);

        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        lista.setLayoutManager(glm);


        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                //Toast.makeText(getApplication(), "Diste click", Toast.LENGTH_SHORT ).show();
                Bundle bundle = new Bundle();

                bundle.putString("foto", photo.getImgSrc());
                bundle.putString("titulo", photo.getCamera().getFullName());
                bundle.putInt("id", photo.getId());
                bundle.putInt("totalFotos", photo.getRover().getTotalPhotos());
                bundle.putString("fAterrizaje", photo.getRover().getLandingDate());
                bundle.putString("fTierra", photo.getEarthDate());


                FragmentDetail fragmentDetail = new FragmentDetail();
                fragmentDetail.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, fragmentDetail).commit();

                /*Intent i = new Intent(getActivity(), FragmentDetail.class);

                i.putExtra("nombre", photo.getCamera().getName());
                i.putExtra("fTierra", photo.getEarthDate());
                i.putExtra("foto", photo.getImgSrc());
                i.putExtra("titulo", photo.getCamera().getFullName());
                startActivity(i);*/
            }
        });




        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        apodService.getListMarsRoverResponse();
        Call<MarsRoverResponse> callApodService = apodService.getListMarsRoverResponse();
        callApodService.enqueue(new Callback<MarsRoverResponse>()
        {
            @Override
            public void onResponse(Call<MarsRoverResponse> call, Response<MarsRoverResponse> response) {
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                lista.setAdapter(nasaApodAdapter);
                //Log.d("APOD", response.body().getPhotos());
            }

            @Override
            public void onFailure(Call<MarsRoverResponse> call, Throwable t) {

            }

        });
        return view;
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recyclerview_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share_recycler:
                Snackbar.make(getView(), "Se agregó a favoritos", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/
}
