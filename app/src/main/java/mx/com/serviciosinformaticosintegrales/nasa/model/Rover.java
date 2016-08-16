
package mx.com.serviciosinformaticosintegrales.nasa.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rover {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("landing_date")
    @Expose
    private String landingDate;
    @SerializedName("max_sol")
    @Expose
    private Integer maxSol;
    @SerializedName("max_date")
    @Expose
    private String maxDate;
    @SerializedName("total_photos")
    @Expose
    private Integer totalPhotos;
    @SerializedName("cameras")
    @Expose
    private List<Camera_> cameras = new ArrayList<Camera_>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The landingDate
     */
    public String getLandingDate() {
        return landingDate;
    }

    /**
     * 
     * @param landingDate
     *     The landing_date
     */
    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    /**
     * 
     * @return
     *     The maxSol
     */
    public Integer getMaxSol() {
        return maxSol;
    }

    /**
     * 
     * @param maxSol
     *     The max_sol
     */
    public void setMaxSol(Integer maxSol) {
        this.maxSol = maxSol;
    }

    /**
     * 
     * @return
     *     The maxDate
     */
    public String getMaxDate() {
        return maxDate;
    }

    /**
     * 
     * @param maxDate
     *     The max_date
     */
    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * 
     * @return
     *     The totalPhotos
     */
    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    /**
     * 
     * @param totalPhotos
     *     The total_photos
     */
    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    /**
     * 
     * @return
     *     The cameras
     */
    public List<Camera_> getCameras() {
        return cameras;
    }

    /**
     * 
     * @param cameras
     *     The cameras
     */
    public void setCameras(List<Camera_> cameras) {
        this.cameras = cameras;
    }

}
