package mx.com.serviciosinformaticosintegrales.nasa.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;
import mx.com.serviciosinformaticosintegrales.nasa.sql.ItemDataSource;


public class FragmentDetail extends Fragment {

    SimpleDraweeView imgFoto;
    TextView txvTitle, txvID, txvTotalPhotos, txvfLanding, txvfTierra;
    private String foto;

    public FragmentDetail() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();

        imgFoto = (SimpleDraweeView) view.findViewById(R.id.imgFoto);
        txvTitle = (TextView) view.findViewById(R.id.txvTitulo);
        txvID = (TextView) view.findViewById(R.id.txvId);
        txvTotalPhotos = (TextView) view.findViewById(R.id.txvTotalPhotos);
        txvfLanding = (TextView) view.findViewById(R.id.txvfLanding);
        txvfTierra = (TextView) view.findViewById(R.id.txvfTierra);

        foto = bundle.getString("foto");
        String titulo = bundle.getString("titulo");
        int id = bundle.getInt("id");
        int totalFotos = bundle.getInt("totalFotos");
        String fTierra = bundle.getString("fTierra");
        String fAterrizaje = bundle.getString("fAterrizaje");

        imgFoto.setImageURI(foto);
        txvTitle.setText(" " + titulo);
        txvID.setText(" " + String.valueOf(id));
        txvTotalPhotos.setText(" " + String.valueOf(totalFotos));
        txvfLanding.setText(" " + fAterrizaje);
        txvfTierra.setText(" " + fTierra);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.favorites_today_apod:
                saveFavorities();
                Snackbar.make(getView(), "Se agregó a favoritos", Snackbar.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveFavorities(){
        Favorities favorities = new Favorities();
        favorities.setTitulo(txvTitle.getText().toString());
        favorities.setFoto(foto);
        ItemDataSource itemDataSource = new ItemDataSource(getActivity());
        itemDataSource.saveItem(favorities);
    }
}
