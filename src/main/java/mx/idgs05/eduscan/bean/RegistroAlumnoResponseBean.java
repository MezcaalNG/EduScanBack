package mx.idgs05.eduscan.bean;

public class RegistroAlumnoResponseBean {
    private int returnCode;
    private String sqlCode;
    private String sqlMessage;
    private String matricula;

    public RegistroAlumnoResponseBean(){
        this.matricula="";
        this.sqlCode="";
        this.sqlMessage="";
        this.returnCode=0;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getSqlCode() {
        return sqlCode;
    }

    public void setSqlCode(String sqlCode) {
        this.sqlCode = sqlCode;
    }

    public String getSqlMessage() {
        return sqlMessage;
    }

    public void setSqlMessage(String sqlMessage) {
        this.sqlMessage = sqlMessage;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
