package Controller;

import DAO.EmpleadosDAO;
import Model.EmpleadoDTO;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class VistaEmpleadosController implements Initializable {

    @FXML
    private Button btnEjecutar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPaterno;
    @FXML
    private TextField txtMaterno;
    @FXML
    private TextField txtSalario;
    @FXML
    private DatePicker txtDateInicio;
    @FXML
    private DatePicker txtDateFin;
    @FXML
    private TableView<EmpleadoDTO> tblEmpleados;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPaterno;
    @FXML
    private TableColumn colMaterno;
    @FXML
    private TableColumn colSalario;
    @FXML
    private TableColumn colPuesto;
    @FXML
    private TableColumn colFechaInicio;
    @FXML
    private TableColumn colFechaFin;
    @FXML
    private TableColumn colDept;
    @FXML
    private ComboBox<String> cmbPuesto;
    @FXML
    private ComboBox<String> cmbDepartamento;
    
    private ObservableList<EmpleadoDTO> lista;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPaterno.setCellValueFactory(new PropertyValueFactory("paterno"));
        this.colMaterno.setCellValueFactory(new PropertyValueFactory("materno"));
        this.colSalario.setCellValueFactory(new PropertyValueFactory("salario"));
        this.colPuesto.setCellValueFactory(new PropertyValueFactory("puesto"));
        this.colFechaInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        this.colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
        this.colDept.setCellValueFactory(new PropertyValueFactory("dept"));
        
        
    }    

    @FXML
    private void ejecutar(ActionEvent event) {
        EmpleadoDTO emp = new EmpleadoDTO();
        
        String nombre = txtNombre.getText();
        String paterno = txtPaterno.getText();
        String materno = txtMaterno.getText();
        String salario = txtSalario.getText();
        String puesto = cmbPuesto.getValue();
        String dept = cmbDepartamento.getValue();
        LocalDate fechaInicio = txtDateInicio.getValue();
        LocalDate fechaFin = txtDateFin.getValue();
        
        
        if(!nombre.equals("")){
            emp.setNombre(nombre);
        }
        
        if(!paterno.equals("")){
            emp.setPaterno(paterno);
        }
        
         if(!materno.equals("")){
            emp.setMaterno(materno);
        }
         
        if(!salario.equals("")){
            emp.setSalario(Double.valueOf(salario));
        }
        
        if(puesto != null){
            emp.setPuesto(puesto);
        }
        
        if(dept !=  null){
            emp.setDept(Integer.parseInt(dept));
        }
        
      
        if(fechaInicio != null && fechaFin != null){
            emp.setFechaInicio(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            emp.setFechaFin(fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
       
        System.out.println(emp);
        EmpleadosDAO e = new EmpleadosDAO();
        
        this.tblEmpleados.setItems(e.getEmpleados(emp));
        /*
        if (!(this.lista.contains(emp))) {
                this.lista.add(emp);
                this.tblEmpleados.setItems(lista);
        }*/
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtSalario.setText("");
        txtDateInicio.setValue(null);
        txtDateFin.setValue(null);
        this.tblEmpleados.setItems(null);
        cmbPuesto.setItems(null);
        cmbDepartamento.setItems(null);
       
    }
    
   

    @FXML
    private void llenarComboBoxPuesto(MouseEvent event) {
        EmpleadosDAO emp = new EmpleadosDAO();
        ObservableList<String> lista;
        lista = emp.getPuestos();
        Collections.sort(lista);//ordeno la lista alfabeticamente
        cmbPuesto.setItems(lista);
    }

    @FXML
    private void llenarComBoxDepartamento(MouseEvent event) {
        EmpleadosDAO emp = new EmpleadosDAO();
        ObservableList<String> lista;
        lista = emp.getDepartamentos();
        Collections.sort(lista);//ordeno la lista de menor a mayor
        cmbDepartamento.setItems(lista);
    }
    
}
