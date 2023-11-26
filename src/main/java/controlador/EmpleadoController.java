package controlador;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Empleado;

import repositories.empleado.EmpleadoRepository;
import repositories.empleado.EmpleadoRepositoryImpl;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class EmpleadoController {

    private static final Logger logger = Logger.getLogger(EmpleadoController.class.getName());
    private static EmpleadoRepository empleadoRepository = new EmpleadoRepositoryImpl();
    EmpleadoRepositoryImpl empRepositoryImpl = new EmpleadoRepositoryImpl();

    public List<Empleado> getEmpleados(){
        logger.info("Obteniendo Empleados");
        return empleadoRepository.findAll();
    }

    public List<Empleado> getEmpleadosByNombre(String nombre){
        logger.info("Obteniendo Empleados que empiezan por " + nombre);
        return empleadoRepository.findByName(nombre);
    }

    public List<Empleado> getEmpleadosSinDepartamento() {
        logger.info("Obteniendo Empleados sin departamento asignado");
        return empRepositoryImpl.findAll();//cambiarlo por empleados sin departamento
    }


    public Empleado createEmpleado(Empleado empleado) {
        logger.info("Creando Empleado");
        return empleadoRepository.save(empleado);
    }

    public static Optional<Empleado> getEmpleadoId(Integer id) {
        logger.info("Obteniendo Empleado con id: " + id);
        return empleadoRepository.findById(id);
    }

    public Empleado updateEmpleado(Empleado empleado) {
        logger.info("Actualizando Empleado con id: " + empleado.getId());
        return empleadoRepository.save(empleado);
    }

    public Boolean deleteEmpleado(Empleado empleado) {
        logger.info("Eliminando Empleado con id: " + empleado.getId());
        return empleadoRepository.delete(empleado);
    }

}
