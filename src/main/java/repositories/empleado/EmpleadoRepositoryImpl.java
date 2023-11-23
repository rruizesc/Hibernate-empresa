package repositories.empleado;

import db.HibernateManager;
import exceptions.DepartamentoException;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Empleado;

import java.util.List;
import java.util.Optional;


public class EmpleadoRepositoryImpl  implements EmpleadoRepository {
    @Override
    public List findAll() {
        //logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createNamedQuery("Empleado.findAll", Empleado.class);
        List<Empleado> list = query.getResultList();
        hb.close();
        return list;

    }

    @Override
    public Optional<Empleado> findById(Integer id) {
        //logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Empleado> empleado = Optional.ofNullable(hb.getManager().find(Empleado.class, id));
        hb.close();
        return empleado;
    }

    @Override
    public List<Empleado> findByName(String nombre) {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre", Empleado.class);
        query.setParameter("nombre", nombre);
        List<Empleado> lista = query.getResultList();
        hb.close();
        return lista;
    }


    public Optional<Empleado> findEmpleadosSinDepartamento() {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createQuery(
                "SELECT e FROM Empleado e WHERE e.departamento IS NULL", Empleado.class);
        List<Empleado> empleadosSinDepartamento = query.getResultList();
        hb.close();
        return Optional.ofNullable((Empleado) empleadosSinDepartamento);
    }

    @Override
    public Empleado save(Empleado entity) {
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
            throw new DepartamentoException("Error al empleado con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }

    }

    @Override
    public Boolean delete(Empleado entity) {
        //logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Empleado.class, entity.getDepartamento());
            entity.setDepartamento(null);
            entity = hb.getManager().find(Empleado.class, entity.getProyecto());
            entity.setProyecto(null);
            hb.getManager().remove(entity);
            save(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new EmpleadoException("Error al eliminar empleado en dpeartamento");
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }
}

