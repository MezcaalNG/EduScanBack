package mx.idgs05.eduscan.bean;

public class DatosAlumnoBean {

    private String nombre;
    private String apellidop;
    private String apellidom;
    private String direccion;
    private String nacimiento;
    private String nss;
    private String tiposangre;
    private int idtiposangre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getTiposangre() {
        return tiposangre;
    }

    public void setTiposangre(String tiposangre) {
        this.tiposangre = tiposangre;
    }

    public int getIdtiposangre() {
        return idtiposangre;
    }

    public void setIdtiposangre(int idtiposangre) {
        this.idtiposangre = idtiposangre;
    }
}
