package model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"empleado", "jefe"})

@Entity
@Table(name = "departamentos")
@NamedQueries({
		@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
})
public class Departamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Integer id;
	@Column(nullable = false)
	private String nombre;
	
	// Relación 1-N con Empleado, un departamento puede tener muchos empleados
    @OneToMany(mappedBy="departamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Empleado> empleado = new HashSet<>();
    
    @OneToOne//(mappedBy = "departamentoJefe")
	@JoinColumn(name = "jefe_id", referencedColumnName = "id")
	private Empleado jefe;
    
	public Departamento(Integer id,String nombre) {
		setId(id);
		setNombre(nombre);
		this.empleado = new HashSet<>();
	}

	public void addEmpleado(Empleado e) {
		if (this.getEmpleado() == null) {
			this.setEmpleado(new HashSet<>());
		}
		this.getEmpleado().add(e);
		e.setDepartamento(this);
	}




	public void addJefe(Empleado jefe) {
		this.setJefe(jefe);
		jefe.setDepartamento(this);
	}

	public void remove(Empleado empleado) {
		this.empleado.remove(empleado);
		// Comprobamos si el empleado está en la colección antes de desvincular el departamento
		if (empleado != null && empleado.getDepartamento() == this) {
			empleado.setDepartamento(null);
		}
	}


	public String show() {
		if (id == 0) {
			return "no departamento!!!";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%2d:%-20s:", id, nombre));

		if (jefe != null) {
			sb.append(String.format(" Jefe [%2d:%s]", jefe.getId(), jefe.getNombre()));
		} else {
			sb.append(" Sin jefe asignado");
		}

		return sb.toString();
	}
	public boolean isNull() {
		return this==null;
	}
}
