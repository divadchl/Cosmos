package mx.com.serviciosinformaticosintegrales.nasa.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.adapters.FavoritiesAdapter;
import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;
import mx.com.serviciosinformaticosintegrales.nasa.sql.ItemDataSource;


public class RecyclerViewFavoritiesFragment extends Fragment {

    private RecyclerView rvFavorities;
    List<Favorities> favoritiesList;

    public RecyclerViewFavoritiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_favorities, container, false);

        rvFavorities = (RecyclerView) view.findViewById(R.id.rvFavorities);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvFavorities.setLayoutManager(llm);
        //llamar a la bd
        ItemDataSource itemDataSource = new ItemDataSource(getActivity());
        favoritiesList = itemDataSource.getAllItems();
        startAdapter();


        return view;
    }

    public FavoritiesAdapter favoritiesAdapter;
    public void startAdapter()
    {
        favoritiesAdapter = new FavoritiesAdapter(favoritiesList, getActivity());
        rvFavorities.setAdapter(favoritiesAdapter);
    }

}
