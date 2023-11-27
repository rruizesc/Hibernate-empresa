package view;

import java.util.Scanner;

import static view.menuDepartamento.menuDepartamento;
import static view.menuEmpleado.menuEmpleado;
import static view.menuProyecto.menuProyecto;

public class Menu {
	
	public static void main(String[] args) {
		mostrarMenu();
	}

	public static void mostrarMenu(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Menu de Empresa");
		System.out.println("¿Que quieres hacer?");
		System.out.println("1.Trabajar en Departamento 2.Trabajar en Empleado 3.Trabajar en Proyecto 4.Salir ");
		int n = sc.nextInt();
		while(true){
			switch (n) {
				case 1:
					menuDepartamento();
					break;
				case 2:
					menuEmpleado();
					break;
				case 3:
					menuProyecto();
					break;
				case 4:
					System.out.println("Saliendo del programa");
					System.exit(0);
				default:
					System.out.println("Opción inválida");
					break;
			}
		}
	}
}
