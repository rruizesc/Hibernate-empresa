package view;


import controlador.ProyectoController;
import io.IO;
import model.Departamento;
import model.Proyecto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class menuProyecto {
    public static void menuProyecto() {
        //Aquí va el controlador de proyecto:
        ProyectoController pcontroler = new ProyectoController();
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
                    mostrar(pcontroler);
                    break;
                case'C':
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
                case 'S':
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    private static void mostrar(ProyectoController dcontroler){
           for (Proyecto p : dcontroler.getProyectos()) {
               IO.println(p.show());
           }
     }

    private static void buscarPorCodigo(ProyectoController dcontroler) {
            IO.print("Código ? ");
             Integer id = IO.readInt();
             Proyecto p = dcontroler.getProyectoId(id).get();
             if (p != null){
                   IO.println(p.show());
             }
     }

     private static void buscarPorNombre(ProyectoController dcontroler) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Proyecto e : dcontroler.getProyectosByNombre(inicio)) {
			IO.println(e.show());
		}
	}

    private static void modificar(ProyectoController dcontroler) {

	}
	private static void crear(ProyectoController dcontroler) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		IO.print("Nacido (aaaa-mm-dd) ? ");
		LocalDate nacido = IO.readLocalDateOrNull();
		Proyecto p = Proyecto.builder()
				.nombre(nombre)
				.build();
		Proyecto anadido = dcontroler.createProyecto(p);
		IO.println(anadido.isNull() ? "Añadido" : "No se ha podido añadir");
	}
	private static void eliminar(ProyectoController dcontroler) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Proyecto p = (Proyecto) dcontroler.getProyectoId(id).get();
		boolean borrado = dcontroler.deleteProyecto(p);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}


}
