package view;


import controlador.EmpleadoController;
import controlador.ProyectoController;
import io.IO;
import model.Departamento;
import model.Empleado;
import model.Proyecto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class menuProyecto {
    public static void menuProyecto() {

        ProyectoController pcontroler = new ProyectoController();
        System.out.println("Bienvenido al menú de Proyecto");
        System.out.println("¿Qué quieres hacer?");
        List<String> option = List.of(
                "Mostrar",
                "buscar por Código",
                "buscar por Nombre",
                "moDificar",
                "Añadir",
                "añadiR empleado",
                "Eliminar",
                "Salir"
        );

        while (true) {
            IO.println("Indica la letra en mayusculas para elegir: " + option);
            switch (Character.toUpperCase(IO.readChar())){
                case 'M':
                    mostrar(pcontroler);
                    break;
                case 'C':
					buscarPorCodigo(pcontroler);
                    break;
                case 'N':
                    buscarPorNombre(pcontroler);
                    break;
                case 'D':
                    modificar(pcontroler);
                    break;
                case 'A':
                    crear(pcontroler);
                    break;
                case 'E':
                    eliminar(pcontroler);
                    break;
                case 'R':
                    empleadoAdd(pcontroler);
                case 'S':
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    private static void mostrar(ProyectoController pcontroler){
           for (Proyecto p : pcontroler.getProyectos()) {
               IO.println(p.show());
           }
     }

    private static void buscarPorCodigo(ProyectoController pcontroler) {
            IO.print("Código ? ");
             Integer id = IO.readInt();
             Proyecto p = pcontroler.getProyectoId(id).get();
             if (p != null){
                   IO.println(p.show());
             }
     }

     private static void buscarPorNombre(ProyectoController pcontroler) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Proyecto e : pcontroler.getProyectosByNombre(inicio)) {
			IO.println(e.show());
		}
	}

    private static void modificar(ProyectoController pcontroler) {
        IO.print("Código del proyecto a modificar? ");
        Integer id = IO.readInt();
        Optional<Proyecto> proyectoOptional = pcontroler.getProyectoId(id);
        if (proyectoOptional.isPresent()){
            Proyecto proyecto = proyectoOptional.get();
            IO.println("Proyecto encontrado: " + proyecto.getNombre());
            IO.print("Nuevo nombre (dejar vacío si no se quiere cambiar: ");
            String nuevoNombre = IO.readString().trim();
            if (!nuevoNombre.isEmpty()){
                proyecto.setNombre(nuevoNombre);
                pcontroler.updateProyecto(proyecto);
                IO.println("Proyecto modificado exitosamente.");
            }else {
                IO.println("No se realizaron cambios en el nombre del proyecto. ");
            }
        } else {
            IO.println("Proyecto no encontrado.");
        }
	}
	private static void crear(ProyectoController pcontroler) {
        IO.print("Nombre del nuevo proyecto: ");
        String nombre = IO.readString().trim();
        if (!nombre.isEmpty()) {
            Proyecto nuevoProyecto = new Proyecto();
            nuevoProyecto.setNombre(nombre);
            Proyecto creado = pcontroler.updateProyecto(nuevoProyecto);
            if (!creado.isNull()) {
                IO.println("Nuevo proyecto creado exitosamente.");
            } else {
                IO.println("No se pudo crear el proyecto.");
            }
        } else {
            IO.println("El nombre del proyecto no puede estar vacío.");
        }
	}

    private static void empleadoAdd(ProyectoController pcontroler){
        IO.print("Código del proyecto al que desea agregar un empleado ");
        int idProyecto = IO.readInt();
        Optional<Proyecto> proyectoOptional = pcontroler.getProyectoId(idProyecto);

        if (proyectoOptional.isPresent()){
            Proyecto proyecto = proyectoOptional.get();

            IO.print("Código del empleado que desar asociar: ");
            int idEmpleado = IO.readInt();
            Optional<Empleado> empleadoOptional = EmpleadoController.getEmpleadoId(idEmpleado);

            if (empleadoOptional.isPresent()){
                Empleado empleado = empleadoOptional.get();
                proyecto.addEmpleado(empleado);

                pcontroler.updateProyecto(proyecto);

                IO.print("Empleado agregado al proyecto exitosamente.");
            } else {
                IO.print("Empleado no encontrado.");
            }
        } else {
            IO.print("Proyecto no encontrado.");
        }
    }
	private static void eliminar(ProyectoController pcontroler) {
        IO.print("Código del proyecto a eliminar? ");
        Integer id = IO.readInt();
        Optional<Proyecto> proyectoOptional = pcontroler.getProyectoId(id);
        if (proyectoOptional.isPresent()) {
            Proyecto proyecto = proyectoOptional.get();
            boolean borrado = pcontroler.deleteProyecto(proyecto);
            IO.println(borrado ? "Proyecto borrado exitosamente." : "No se ha podido borrar el proyecto.");
        } else {
            IO.println("Proyecto no encontrado.");
        }
	}


}
