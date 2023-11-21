package db;

import controlador.DepartamentoController;
import controlador.EmpleadoController;
import controlador.ProyectoController;
import model.Departamento;
import model.Empleado;
import model.Proyecto;

import java.time.LocalDate;
import java.util.List;

public final class DataDB {
    private static EmpleadoController dao = new EmpleadoController();

    public static void init() {
        EmpleadoController dao = new EmpleadoController();
        DepartamentoController daoD = new DepartamentoController();
        ProyectoController daoP = new ProyectoController();
        List<Departamento> departs = getDepartamentosInit();
        List<Empleado> empleados = getEmpleadosInit();
        List<Proyecto> proyectos = getProyectosInit();
        for (int i = 0; i < empleados.size(); i++) {
            dao.createEmpleado(empleados.get(i));
        }
        for (int i = 0; i < departs.size(); i++) {
            daoD.crearDepartamento(departs.get(i));
        }

        for (int i = 0; i < proyectos.size(); i++) {
            daoP.createProyecto(proyectos.get(i));
        }
    }

    public static List<Empleado> getEmpleadosInit() {
        return List.of(
                Empleado.builder().nombre("Juan").salario(1000.0).fNacimiento(LocalDate.of(2000, 1, 1)).build()
        );
    }

    public static List<Departamento> getDepartamentosInit() {

        return List.of(
                Departamento.builder().nombre("Informatica").build()


        );

    }

    public static List<Proyecto> getProyectosInit() {
        return List.of(
                Proyecto.builder().nombre("Nombre").build()

        );
    }
}