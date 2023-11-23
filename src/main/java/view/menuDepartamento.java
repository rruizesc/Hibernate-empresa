package view;

import controlador.DepartamentoController;
import controlador.EmpleadoController;
import io.IO;
import model.Departamento;

import java.util.List;
import java.util.Optional;

public class menuDepartamento {

    public static void menuDepartamento() {

        DepartamentoController dcontroler = new DepartamentoController();
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
            switch (Character.toUpperCase(IO.readChar())){
                case 'M':
                    mostrar(dcontroler);
                    break;
                case'C':
                    buscarPorCodigo(dcontroler);
                    break;
                case 'N':
                    buscarPorNombre(dcontroler);
                    break;
                case 'D':
                    modificar(dcontroler);
                    break;
                case 'A':
                    anadir(dcontroler);
                    break;
                case 'E':
                    borrar(dcontroler);
                    break;
                case 'S':
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
private static void mostrar(DepartamentoController dcontroler){
          for (Departamento e : dcontroler.getDepartamento()) {
               IO.println(e.show());
           }
     }

    private static void buscarPorCodigo(DepartamentoController dcontroler) {
            IO.print("Código ? ");
            Integer id = IO.readInt();
           	Departamento e = dcontroler.getDepartamentoId(id).get();
            if (e != null){
                   IO.println(e.show());
             }
     }

     private static void buscarPorNombre(DepartamentoController dcontroler) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Departamento e : dcontroler.getDepartamentoByNombre(inicio)) {
			IO.println(e.show());
		}
	}

	private static void modificar(DepartamentoController dcontroler) {
		IO.print("Código del departamento a modificar ? ");
		Integer id = IO.readInt();
		Optional<Departamento> d = dcontroler.getDepartamentoId(id);
		if (d == null) {
			IO.println("No se ha encontrado el departamento");
			return;
		}
		IO.printf("Nombre [%s] ? ", d.get().getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			d.get().setNombre(nombre);
		}
		IO.printf("Jefe [%s] ? ", d.get().getJefe().show());
		Integer jefe = IO.readIntOrNull();
		if (jefe != null) {

			EmpleadoController daoEmpleado = new EmpleadoController();
			d.get().setJefe(daoEmpleado.getEmpleadoId(jefe).get());
		}
		Departamento anadido = dcontroler.crearDepartamento(d.get());
		IO.println(anadido.isNull() ? "Modificado" : "No se ha podido modificar");
	}
	private static void anadir(DepartamentoController dcontroler) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		Departamento d = Departamento.builder().nombre(nombre).build();
		Departamento anadido = dcontroler.crearDepartamento(d);
		IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
	}
	 private static void borrar(DepartamentoController dcontroler) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Departamento e = (Departamento) dcontroler.getDepartamentoId(id).get();
		boolean borrado = dcontroler.deleteDepartamento(e);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}



}
