package repositories.proyecto;

import db.HibernateManager;
import exceptions.EmpleadoException;
import exceptions.ProyectoException;
import jakarta.persistence.TypedQuery;
import model.Proyecto;

import java.util.List;
import java.util.Optional;



public class ProyectoRepositoryImpl  implements ProyectoRepository {
    @Override
    public List findAll() {
        //logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Proyecto> query = hb.getManager().createNamedQuery("Proyecto.findAll", Proyecto.class);
        List<Proyecto> list = query.getResultList();
        hb.close();
        return list;

    }

    @Override
    public Optional<Proyecto> findById(Integer id) {
        //logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Proyecto> proyecto = Optional.ofNullable(hb.getManager().find(Proyecto.class, id));
        hb.close();
        return proyecto;
    }

    @Override
    public List<Proyecto> findByName(String nombre) {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Proyecto> query = hb.getManager().createQuery("SELECT p FROM Proyecto p WHERE p.nombre = :nombre", Proyecto.class);
        query.setParameter("nombre", nombre);
        List<Proyecto> lista = query.getResultList();
        hb.close();
        return lista;
    }

    @Override
    public Proyecto save(Proyecto entity) {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();

        try {
            hb.getTransaction().begin();

            // Cargar la entidad dentro de la transacción
            Proyecto persistentEntity = hb.getManager().find(Proyecto.class, entity.getId());

            // Actualizar la entidad persistente
            if (persistentEntity != null) {
                persistentEntity.setNombre(entity.getNombre());
                // Actualiza otros campos según sea necesario

                hb.getManager().merge(persistentEntity);
            }

            hb.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
            throw new ProyectoException("Error al proyecto con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            hb.close();
        }
    }



    @Override
    public Boolean delete(Proyecto entity) {
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Buscar el proyecto por su ID y eliminarlo directamente
            entity = hb.getManager().find(Proyecto.class, entity.getId());
            hb.getManager().remove(entity);
            hb.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
            throw new ProyectoException("Error al eliminar el proyecto");
        } finally {
            hb.close();
        }
    }

    }
