package menu;


import io.IO;

import java.util.List;

public class menuProyecto {
    public static void menuProyecto() {
        //Aquí va el controlador de proyecto:
        //ProyectoController pcontroler = new ProyectoController();
        System.out.println("Bienvenido al menú de Proyecto");
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
            switch (Character.toUpperCase(IO.readChar())){
                case 'M':
                    //mostrar(pcontroler);
                    break;
                case'C':
                    //buscarPorCodigo(pcontroler);
                    break;
                case 'N':
                    //buscarPorNombre(pcontroler);
                    break;
                case 'D':
                    //modificar(pcontroler);
                    break;
                case 'A':
                    //crear(pcontroler);
                    break;
                case 'E':
                    //eliminar(pcontroler);
                    break;
                case 'S':
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    /*private static void mostrar(EmpleadoController dcontroler){
    *       for (Empleado e : econtroller.getEmpleados()) {
    *           IO.println(e.show());
    *       }
    * }
    *
    * private static void buscarPorCodigo(EmpleadoController dcontroler) {
    *         IO.print("Código ? ");
    *         Integer id = IO.readInt();
    *         Empleado e = dao.getEmpleadoId(id).get();
    *         if (e != null){
    *               IO.println(e.show());
    *         }
    * }
    *
    * private static void buscarPorNombre(EmpleadoController dcontroler) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Empleado e : dao.getEmpleadosByNombre(inicio)) {
			IO.println(e.show());
		}
	}

    * private static void modificar(EmpleadoController dcontroler) {
		IO.print("Código del empleado a modificar ? ");
		Integer id = IO.readInt();
		Optional<Empleado> emp = dao.getEmpleadoId(id);
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
		IO.printf("Nacido (aaaa-mm-dd) [%s] ? ", emp.get().getNacido());
		LocalDate nacido = IO.readLocalDateOrNull();
		if (nacido != null) {
			emp.get().setNacido(nacido);
		}
		IO.printf("Departamento [%s] ? ", emp.get().getDepartamento().show());
		Integer departamento = IO.readIntOrNull();
		if (departamento != null) {
			emp.get().setDepartamento(Departamento.builder().id(departamento).build());
		}
		Empleado anadido = dao.createEmpleado(emp.get());
		IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
	}
	* private static void anadir(EmpleadoController dcontroler) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		IO.print("Nacido (aaaa-mm-dd) ? ");
		LocalDate nacido = IO.readLocalDateOrNull();
		Empleado e = Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.nacido(nacido)
				.build();
		Empleado anadido = dao.createEmpleado(e);
		IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
	}
	* private static void borrar(EmpleadoController dcontroler) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Empleado e = (Empleado) dao.getEmpleadoId(id).get();
		boolean borrado = dao.deleteEmpleado(e);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}

    * */
}
