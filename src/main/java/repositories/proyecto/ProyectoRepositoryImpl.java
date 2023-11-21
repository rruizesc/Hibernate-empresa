package repositories.proyecto;

import db.HibernateManager;
import exceptions.EmpleadoException;
import exceptions.ProyectoException;
import jakarta.persistence.TypedQuery;
import model.Proyecto;

import java.util.List;
import java.util.Optional;

import static com.ibm.java.diagnostics.utils.Context.logger;

public class ProyectoRepositoryImpl  implements ProyectoRepository {
    @Override
    public List findAll() {
        logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Proyecto> query = hb.getManager().createNamedQuery("Raqueta.findAll", Proyecto.class);
        List<Proyecto> list = query.getResultList();
        hb.close();
        return list;

    }

    @Override
    public Optional<Proyecto> findById(Integer id) {
        logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Proyecto> proyecto = Optional.ofNullable(hb.getManager().find(Proyecto.class, id));
        hb.close();
        return proyecto;
    }

    @Override
    public Proyecto save(Proyecto entity) {
        logger.info("save()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return entity;

        } catch (Exception e) {
            throw new ProyectoException("Error al proyecto con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }

    }

    @Override
    public Boolean delete(Proyecto entity) {
        logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Proyecto.class, findAll());
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