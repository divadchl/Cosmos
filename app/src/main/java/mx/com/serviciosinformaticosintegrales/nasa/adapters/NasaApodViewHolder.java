package mx.com.serviciosinformaticosintegrales.nasa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.model.Photo;


public class NasaApodViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.item_apod_image)
    SimpleDraweeView itemApodImage;
    @BindView(R.id.item_apod_text) TextView itemApodText;

    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;

    public NasaApodViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener)
    {
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.item_apod_image)
    public void onViewClick(View view)
    {
        if(onItemListener != null)
        {
            onItemListener.onItemClick(photo);
        }
    }
}
