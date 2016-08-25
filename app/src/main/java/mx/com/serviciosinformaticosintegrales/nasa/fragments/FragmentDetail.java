package mx.com.serviciosinformaticosintegrales.nasa.fragments;


import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;
import mx.com.serviciosinformaticosintegrales.nasa.sql.ItemDataSource;


public class FragmentDetail extends Fragment {

    SimpleDraweeView imgFoto;
    TextView txvTitle, txvID, txvTotalPhotos, txvfLanding, txvfTierra;
    private String foto;
    private Dialog customDialog = null;

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
                showDialog(getView());
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

    public void showDialog(View view)
    {
        // con este tema personalizado evitamos los bordes por defecto
        customDialog = new Dialog(getActivity(), R.style.Theme_Dialog_Translucent);
        //deshabilitamos el título por defecto
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //obligamos al usuario a pulsar los botones para cerrarlo
        customDialog.setCancelable(false);
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.dialog);

        TextView titulo = (TextView) customDialog.findViewById(R.id.titulo);
        titulo.setText(R.string.titulo_dialog);

        TextView contenido = (TextView) customDialog.findViewById(R.id.contenido);
        contenido.setText(R.string.message_favorites);

        ((Button) customDialog.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();
                saveFavorities();
                Snackbar.make(view, R.string.aceptar, Snackbar.LENGTH_SHORT).show();
            }
        });

        ((Button) customDialog.findViewById(R.id.cancelar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();
                Snackbar.make(view, R.string.cancelar, Snackbar.LENGTH_SHORT).show();

            }
        });

        customDialog.show();
    }
}
