package mx.com.serviciosinformaticosintegrales.nasa.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.model.Favorities;

public class FavoritiesAdapter extends RecyclerView.Adapter<FavoritiesAdapter.FavoritiesViewHolder> {

    List<Favorities> favoritiesList;
    Activity activity;

    public FavoritiesAdapter(List<Favorities> favoritiesList, Activity activity) {
        this.favoritiesList = favoritiesList;
        this.activity = activity;
    }

    @Override
    public FavoritiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favorities, parent, false);
        return new FavoritiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritiesViewHolder holder, int position) {
        final Favorities favorities = favoritiesList.get(position);
        holder.sdvImageFavorities.setImageURI(favorities.getFoto());
        holder.txvTitleFavorities.setText(favorities.getTitulo());

        holder.sdvImageFavorities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = descargarImagen(favorities.getFoto());
                String ruta = guardarImagen(activity, favorities.getTitulo(), bitmap);
                Snackbar.make(v, ruta, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritiesList.size();
    }

    public static class FavoritiesViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView sdvImageFavorities;
        private TextView txvTitleFavorities;


        public FavoritiesViewHolder(View itemView) {
            super(itemView);

            sdvImageFavorities = (SimpleDraweeView)itemView.findViewById(R.id.sdwImagenFavoritos);
            txvTitleFavorities = (TextView)itemView.findViewById(R.id.txvTituloFavoritos);
        }
    }

    private Bitmap descargarImagen (String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return imagen;
    }

    private String guardarImagen (Context context, String nombre, Bitmap imagen){
        ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir("COSMOS", Context.MODE_APPEND);
        File myPath = new File(dirImages, nombre + ".jpg");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            imagen.compress(Bitmap.CompressFormat.JPEG, 10, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return myPath.getAbsolutePath();
    }
}
