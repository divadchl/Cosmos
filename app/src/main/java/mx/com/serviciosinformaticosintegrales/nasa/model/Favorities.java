package mx.com.serviciosinformaticosintegrales.nasa.model;


public class Favorities {
    private int id;
    private String titulo;
    private String foto;

    public Favorities() {
    }

    public Favorities(String titulo, String foto) {

        this.titulo = titulo;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
