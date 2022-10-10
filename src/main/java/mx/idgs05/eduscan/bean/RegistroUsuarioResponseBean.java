package mx.idgs05.eduscan.bean;

public class RegistroUsuarioResponseBean {
    private int returnCode;
    private String sqlCode;
    private String sqlMessage;

    public RegistroUsuarioResponseBean() {
        this.returnCode = 0;
        this.sqlCode = "";
        this.sqlMessage = "";
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
}
