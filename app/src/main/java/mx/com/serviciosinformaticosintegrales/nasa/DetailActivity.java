package mx.com.serviciosinformaticosintegrales.nasa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class DetailActivity extends AppCompatActivity {

    SimpleDraweeView imgFoto;
    TextView txvTitulo, txvfTierra, txvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgFoto = (SimpleDraweeView) findViewById(R.id.imgFoto);
        txvTitulo = (TextView) findViewById(R.id.txvTitulo);
        txvfTierra = (TextView) findViewById(R.id.txvfTierra);
        txvNombre = (TextView) findViewById(R.id.txvNombre);

        String foto = getIntent().getExtras().getString("foto");
        String titulo = getIntent().getExtras().getString("titulo");
        String fecha = getIntent().getExtras().getString("fTierra");
        String nombre = getIntent().getExtras().getString("nombre");

        imgFoto.setImageURI(foto);
        txvTitulo.setText(titulo);
        txvfTierra.setText(fecha);
        txvNombre.setText(nombre);
    }
}
