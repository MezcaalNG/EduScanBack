package mx.idgs05.eduscan.dao.impl;

import mx.idgs05.eduscan.bean.RegistroUsuarioRequestBean;

import java.sql.*;

public class ScanEduDAO {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    protected void startConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://74.208.25.75:3306/SCANEDU",
                    "idgs05", "2022_Idgs05!");
            statement=null;
            statement = connection.createStatement();
        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    protected void closeConnection(){
        try{
            if(resultSet!=null){
                resultSet.close();
            }
            statement.close();
            connection.close();
        }catch(Exception exception){
            System.out.println(exception);
        }
    }

    public String hashQuery(String pass, String salt){
        String response ="";
        String query="SELECT SHA1(CONCAT('"+salt+"', '"+pass+"')) AS hash_value";
        try {
            startConnection();
            resultSet = statement.executeQuery(
                    query);
            while (resultSet.next()) {
                response = resultSet.getString("hash_value");
            }
            closeConnection();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
        }
        return response;
    }
}
