package controlador;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Proyecto;
import repositories.proyecto.ProyectoRepository;
import repositories.proyecto.ProyectoRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProyectoController {

    private final Logger logger = Logger.getLogger(ProyectoController.class.getName());
    private ProyectoRepository proyectosRepository = new ProyectoRepositoryImpl();

    public List<Proyecto> getProyectos(){
        logger.info("Obteniendo Proyectos");
        return proyectosRepository.findAll();
    }

    public List<Proyecto> getProyectosByNombre(String nombre){
        logger.info("Obteniendo Proyectos que empiezan por " + nombre);
        return proyectosRepository.findByName(nombre);
    }

    public Proyecto createProyecto(Proyecto proyecto) {
        logger.info("Creando Proyecto");
        return proyectosRepository.save(proyecto);
    }

    public Optional<Proyecto> getProyectoId(Integer id) {
        logger.info("Obteniendo Proyecto con id: " + id);
        return proyectosRepository.findById(id);
    }

    public Proyecto updateProyecto(Proyecto proyecto) {
       logger.info("Actualizando Proyecto con id: " + proyecto.getId());
        return proyectosRepository.save(proyecto);
    }

    public Boolean deleteProyecto(Proyecto proyecto) {
        logger.info("Eliminando Proyecto con id: " + proyecto.getId());
        return proyectosRepository.delete(proyecto);
    }

}
