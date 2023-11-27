package repositories.departamento;

import db.HibernateManager;
import exceptions.DepartamentoException;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;

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
        TypedQuery<Departamento> query = hb.getManager().createNamedQuery("Departamento.findAll", Departamento.class);
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
        TypedQuery<Departamento> query = hb.getManager().createQuery("SELECT d FROM Departamento d WHERE d.nombre = :nombre", Departamento.class);
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
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();

            // Eliminar empleados asociados
            for (Empleado empleado : entity.getEmpleado()) {
                empleado.setDepartamento(null);
                hb.getManager().remove(empleado);
            }

            // Eliminar el departamento
            hb.getManager().remove(entity);

            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
            throw new DepartamentoException("Error al eliminar el departamento");
        }
    }





    @Override
    public void update(Departamento entity) {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Actualizar el departamento
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
        } catch (Exception e) {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
            throw new DepartamentoException("Error al actualizar el departamento");
        } finally {
            hb.close();
        }
    }

}
