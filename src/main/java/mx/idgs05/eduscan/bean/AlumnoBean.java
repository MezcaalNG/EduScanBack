package mx.idgs05.eduscan.bean;

public class AlumnoBean {
    private DatosAlumnoBean alumno;
    private DatosContactoBean contacto;
    private DatosAcademicosBean academico;

    public AlumnoBean() {
        this.alumno = new DatosAlumnoBean();
        this.contacto = new DatosContactoBean();
        this.academico = new DatosAcademicosBean();
    }

    public DatosAlumnoBean getAlumno() {
        return alumno;
    }

    public void setAlumno(DatosAlumnoBean alumno) {
        this.alumno = alumno;
    }

    public DatosContactoBean getContacto() {
        return contacto;
    }

    public void setContacto(DatosContactoBean contacto) {
        this.contacto = contacto;
    }

    public DatosAcademicosBean getAcademico() {
        return academico;
    }

    public void setAcademico(DatosAcademicosBean academico) {
        this.academico = academico;
    }
}
