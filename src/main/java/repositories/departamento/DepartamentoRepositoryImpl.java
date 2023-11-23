package repositories.departamento;

import db.HibernateManager;
import exceptions.DepartamentoException;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Departamento;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;



public class DepartamentoRepositoryImpl  implements DepartamentoRepository {
    private final Logger logger = Logger.getLogger(DepartamentoRepositoryImpl.class.getName());

    @Override
    public List findAll() {
        //logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Departamento> query = hb.getManager().createNamedQuery("Departamendo", Departamento.class);
        List<Departamento> list = query.getResultList();
        hb.close();
        return list;

    }

    @Override
    public Optional<Departamento> findById(Integer id) {
        //logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Departamento> departamento = Optional.ofNullable(hb.getManager().find(Departamento.class, id));
        hb.close();
        return departamento;
    }

    @Override
    public List<Departamento> findByName(String nombre) {
        //logger.info("findByName()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Departamento> query = hb.getManager().createQuery("SELECT e FROM Departamento e WHERE e.nombre = :nombre", Departamento.class);
        query.setParameter("nombre", nombre);
        List<Departamento> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Departamento save(Departamento entity) {
        //logger.info("save()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return entity;

        } catch (Exception e) {
            throw new DepartamentoException("Error al departamento con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }

    }

    @Override
    public Boolean delete(Departamento entity) {
        //logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Departamento.class, entity.getJefe());
            entity.setJefe(null);
            hb.getManager().remove(entity);
            save(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new EmpleadoException("Error al eliminar empleado en departamento");
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }

}
