package Model;

/**
 *
 * @author Navarro Villa Emmanuel De Jesús
 */
public class EmpleadoDTO {
    private String nombre;
    private String paterno;
    private String materno;
    private double salario;
    private String puesto;
    private String fechaInicio;
    private String fechaFin;
    private int dept;

    public EmpleadoDTO() {
    }
    
    

    public EmpleadoDTO(String nombre, String paterno, String materno, float salario, String puesto, String fechaInicio, String fechaFin,int dept) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.salario = salario;
        this.puesto = puesto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dept = dept;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" + "nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", salario=" + salario + ", puesto=" + puesto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", dept=" + dept + '}';
    }

   
    
    
    
}
