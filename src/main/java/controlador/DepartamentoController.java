package controlador;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Departamento;
import repositories.departamento.DepartamentoRepository;
import repositories.departamento.DepartamentoRepositoryImpl;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoController {

    private final Logger logger = Logger.getLogger(DepartamentoController.class.getName());
    private  DepartamentoRepository departamentoRepository = new DepartamentoRepositoryImpl();

    public List<Departamento> getDepartamentos(){
        logger.info("Obteniendo Departamentos");
        return departamentoRepository.findAll();
    }

    public List<Departamento> getDepartamentosByNombre(String nombre){
        logger.info("Obteniendo Departamentos que empiezan por " + nombre);
        return departamentoRepository.findByName(nombre);
    }

    public Departamento crearDepartamento(Departamento departamento) {
        logger.info("Creando Departamento");
        return departamentoRepository.save(departamento);
    }

    public Optional<Departamento> getDepartamentoId(Integer id) {
        logger.info("Obteniendo Departamento con id: " + id);
        return departamentoRepository.findById(id);
    }

    public Departamento updateTenista(Departamento departamento) {
        logger.info("Actualizando Departamento con id: " + departamento.getId());
        return departamentoRepository.save(departamento);
    }

    public Boolean deleteDepartamento(Departamento departamento) {
        logger.info("Eliminando Departamento con id: " + departamento.getId());
        return departamentoRepository.delete(departamento);
    }
}