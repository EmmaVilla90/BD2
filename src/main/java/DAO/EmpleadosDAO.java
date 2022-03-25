/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.EmpleadoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class EmpleadosDAO {
    
    private Connection conexion;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;
    private CallableStatement stmt;
    
    public String getScript(EmpleadoDTO emp) {
           
            String script = null;
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "{call detallesEmpleados(?,?,?,?,?,?,?,?,?)}";
            
            
            
            //preparo la consulta por medio de CallableStatement 
            stmt = conexion.prepareCall(sql);
            
            //parametrizo primero los parametros de entrada (IN) del procedimiento almacenado
            stmt.setString(1,emp.getNombre());
            stmt.setString(2,emp.getPaterno());
            stmt.setString(3,emp.getMaterno());
            stmt.setString(4,emp.getFechaInicio());
            stmt.setString(5,emp.getFechaFin());
            stmt.setString(6,emp.getPuesto());
            stmt.setDouble(7,emp.getSalario());
            stmt.setInt(8,emp.getDept());
            
            // Registro noveno parametro que es de salida; pero debe registrarse
            stmt.registerOutParameter(9, java.sql.Types.VARCHAR);
            
            //ejecuto el procedimiento o sentencia sql que invoca al procedimiento
            stmt.execute();
            
            //Recupera el texto del mensaje y lo imprime en la consola
            script = stmt.getString(9);
            
            return script;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                
                if(stmt!=null) stmt.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
        
    }
    
    
    public ObservableList<EmpleadoDTO> getEmpleados(EmpleadoDTO e) {
           ObservableList<EmpleadoDTO> lista = FXCollections.observableArrayList();
           EmpleadoDTO emp; 
           
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
    
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(getScript(e));
           
            //devuelve las tuplas de la consulta sql en uno bjeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
                
                emp = new EmpleadoDTO();
                emp.setNombre(rs.getString(1));
                emp.setPaterno(rs.getString(2));
                emp.setMaterno(rs.getString(3));
                emp.setPuesto(rs.getString(4));
                emp.setSalario(rs.getDouble(5));
                emp.setFechaInicio(rs.getDate(6)+"");
                emp.setFechaFin(rs.getDate(7)+"");
                emp.setDept(rs.getInt(8));
                
                lista.add(emp);
                
            }
            
            return lista;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }finally{
            try {
                
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
             
            
        }
        
    } 
    
    
    public ObservableList<String> getPuestos(){
        ObservableList<String> lista = FXCollections.observableArrayList();
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT DISTINCT(puesto) ";
            sql += " FROM empleados; ";
            
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //devuelve las tuplas de la consulta sql en uno objeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
              
                lista.add(rs.getString(1));
                
            }
            
            return lista;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
    
    }
    
     public ObservableList<String> getDepartamentos(){
        ObservableList<String> lista = FXCollections.observableArrayList();
        
        try {
            
            //establezco la conexion de la base de datos con el metodo static de la clase Conexion
            //Conexion implementa patron singleton,es decir, una sola conexion 
            //y se cierra automaticamente cuando se cierra la aplicacion java
            //conexion=Conexion.getConexion();
            conexion = Conexion.getConnection();
            
            
            //declaro la consulta sql que se enviara por medio del PreparedStatement
            sql = "SELECT DISTINCT(id_dept) ";
            sql += " FROM empleados; ";
            
            
            //preparo la consulta por medio de PreparedStatement 
            pstm=conexion.prepareStatement(sql);
            
            //devuelve las tuplas de la consulta sql en uno objeto ResultSet 
            rs=pstm.executeQuery();
            
            while(rs.next()){
              
                lista.add(rs.getInt(1)+"");
                
            }
            
            return lista;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
                
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
             
            
        }
    
    }
}
