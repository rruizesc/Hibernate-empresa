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
@EqualsAndHashCode(exclude = "empleado")


@Entity
@Table(name = "proyectos")
@NamedQueries({
		@NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
})


public class Proyecto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Integer id;
	@Column(nullable = false)
	private String nombre;
	
	@ManyToMany
	@JoinTable(
	        name = "empleado_proyecto", 
	        joinColumns = @JoinColumn(name = "proyecto_id"), 
	        inverseJoinColumns = @JoinColumn(name = "empleado_id") 
	    )
	private Set<Empleado> empleado = new HashSet<>();

	public Proyecto(Integer id, String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public Object show() {
		return false;
	}

	public boolean isNull() {
		return false;
	}
	
	
	/*
	public void addEmpleado(Empleado e) {
		 e.getProyecto().add(this);
		 this.getEmpleado().add(e);
	}
	*/
}
