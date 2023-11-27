package repositories.departamento;

import model.Departamento;
import repositories.CrudRepository;


public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {
    void update(Departamento entity);
}