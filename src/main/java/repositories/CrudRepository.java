package repositories;

import java.util.List;
import java.util.Optional;

// Vamos a simular
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
public interface CrudRepository<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    List<T> findByName(String name);

    T save(T entity);

    Boolean delete(T entity);
}
