package com.boajp.repositorios;

import com.boajp.modelo.SplitEntidad;
import jakarta.persistence.*;

public class SplitRepositorio {

    private final EntityManagerFactory emf;
    private final EntityManager em;


    public SplitRepositorio(){
        emf = Persistence.createEntityManagerFactory("default");
        em = emf. createEntityManager();

    }

    public void insertar (SplitEntidad split) throws Exception {

        EntityTransaction transaction = em.getTransaction ();
        try {
            transaction.begin();
            em.persist(split);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar insertar el split");
        }
    }

    public void eliminar (SplitEntidad split) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        SplitEntidad s = em.find(SplitEntidad.class, split.getCodSplit());
        try {
            transaction.begin();
            if (split != null) {
                em.remove(split);
                transaction.commit();
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el split");
        }
    }

    public void modificar (SplitEntidad split) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        SplitEntidad s = em.find(SplitEntidad.class, split.getCodSplit());
        try {
            transaction.begin();
            if (split != null){
                s.setNombre(split.getNombre());
                s.setFechaFin(split.getFechaFin());
                s.setFechaInicio(split.getFechaInicio());
                s.setTemporada(split.getTemporada());
                s.setListaJornadas(split.getListaJornadas());
                s.setTablaClasificaciones(split.getTablaClasificaciones());
                em.persist(s);
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar modificar el split");
        }
    }


    public void verNumeroSplit (){
        EntityTransaction transaction = em.getTransaction ();
        transaction.begin();
        Query qNroSplit = em.createNativeQuery ("SELECT COUNT (*) FROM personas ");


    }
}


