package mx.com.serviciosinformaticosintegrales.nasa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.model.Apod;
import mx.com.serviciosinformaticosintegrales.nasa.model.Photo;

public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder>
{
    private List<Photo> marsPhotos;
    private OnItemClickListener onItemClickListener;

    public NasaApodAdapter(){};

    public NasaApodAdapter(List<Photo> apods) {
        this.marsPhotos = apods;
    }

    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nasa_apod_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position)
    {
        Photo photo = marsPhotos.get(position);
        holder.itemApodImage.setImageURI(photo.getImgSrc());
        //Picasso.with(holder.itemApodImage.getContext())
          //      .load(photo.getImgSrc()).into(holder.itemApodImage);
        holder.itemApodText.setText(photo.getCamera().getFullName());
        holder.setItemClick(photo, onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(List<Photo> marsPhotos)
    {
        this.marsPhotos = marsPhotos;
    }

    @Override
    public int getItemCount() {
        return marsPhotos !=null? marsPhotos.size():0;
    }

    public interface OnItemClickListener
    {
        void onItemClick(Photo photo);
    }
}
