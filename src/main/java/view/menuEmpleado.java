package view;

import io.IO;
import controlador.EmpleadoController;
import model.Departamento;
import model.Empleado;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class menuEmpleado {
    public static void menuEmpleado() {
        //Aquí va el controlador de proyecto:
        EmpleadoController econtroler = new EmpleadoController();
        System.out.println("Bienvenido al menú de Empleado");
        System.out.println("¿Qué quieres hacer?");
        List<String> option = List.of(
                "Mostrar",
                "buscar por Código",
                "buscar por Nombre",
                "moDificar",
                "Añadir",
                "Eliminar",
                "Salir"
        );

        while (true) {
            IO.println("Indica la letra en mayusculas para elegir: " + option);
            switch (Character.toUpperCase(IO.readChar())) {
                case 'M':
                    mostrar(econtroler);
                    break;
                case 'C':
                    buscarPorCodigo(econtroler);
                    break;
                case 'N':
                    buscarPorNombre(econtroler);
                    break;
                case 'D':
                    modificar(econtroler);
                    break;
                case 'A':
                    crear(econtroler);
                    break;
                case 'E':
                    eliminar(econtroler);
                    break;
                case 'S':
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private static void mostrar(EmpleadoController econtroler) {
        for (Empleado e : econtroler.getEmpleados()) {
            IO.println(e.show());
        }
    }

    private static void buscarPorCodigo(EmpleadoController econtroler) {
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Empleado e = econtroler.getEmpleadoId(id).get();
        if (e != null) {
            IO.println(e.show());
        }
    }

    private static void buscarPorNombre(EmpleadoController econtroler) {
        IO.print("El nombre empieza por ? ");
        String inicio = IO.readString();
        for (Empleado e : econtroler.getEmpleadosByNombre(inicio)) {
            IO.println(e.show());
        }
    }

    private static void modificar(EmpleadoController econtroler) {
        IO.print("Código del empleado a modificar ? ");
        Integer id = IO.readInt();
        Optional<Empleado> emp = econtroler.getEmpleadoId(id);
        if (emp == null) {
            IO.println("No se ha encontrado al empleado");
            return;
        }
        IO.printf("Nombre [%s] ? ", emp.get().getNombre());
        String nombre = IO.readString();
        if (!nombre.isBlank()) {
            emp.get().setNombre(nombre);
        }
        IO.printf("Salario [%s] ? ", emp.get().getSalario());
        Double salario = IO.readDoubleOrNull();
        if (salario != null) {
            emp.get().setSalario(salario);
        }
        IO.printf("Nacido (aaaa-mm-dd) [%s] ? ", emp.get().getFNacimiento());
        LocalDate nacido = IO.readLocalDateOrNull();
        if (nacido != null) {
            emp.get().setFNacimiento(nacido);
        }
        IO.printf("Departamento [%s] ? ", emp.get().getDepartamento().show());
        Integer departamento = IO.readIntOrNull();
        if (departamento != null) {
            emp.get().setDepartamento(Departamento.builder().id(departamento).build());
        }
        Empleado anadido = econtroler.createEmpleado(emp.get());
        IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
    }

    private static void crear(EmpleadoController econtroler) {
        IO.print("Nombre ? ");
        String nombre = IO.readString();
        IO.print("Salario ? ");
        Double salario = IO.readDoubleOrNull();
        IO.print("Nacido (aaaa-mm-dd) ? ");
        LocalDate nacido = IO.readLocalDateOrNull();
        Empleado e = Empleado.builder()
                .nombre(nombre)
                .salario(salario)
                .fNacimiento(nacido)
                .build();
        Empleado anadido = econtroler.createEmpleado(e);
        IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
    }

    private static void eliminar(EmpleadoController econtroler) {
        IO.print("Código ? ");
        Integer id = IO.readInt();
        Empleado e = (Empleado) econtroler.getEmpleadoId(id).get();
        boolean borrado = econtroler.deleteEmpleado(e);
        IO.println(borrado ? "Borrado" : "No se ha podido borrar");
    }

}
