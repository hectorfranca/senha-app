/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AtendimentoModel;

/**
 *
 * @author Héctor França
 */
public class AtendimentoController {

    public int save(AtendimentoModel atendimentoModel) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(atendimentoModel);
            entityManager.getTransaction().commit();
            return atendimentoModel.getId();
        } catch (Exception exception) {
            System.out.println("Nao foi possivel salvar o atendimento.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return 0;

    }

    public AtendimentoModel getFirst() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("FROM AtendimentoModel WHERE STATUS = 0 ORDER BY id ASC");
            query.setMaxResults(1);

            return (AtendimentoModel) query.getSingleResult();
        } catch (Exception exception) {
            System.out.println("Nao existe proximo atendimento.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;

    }

    public List<AtendimentoModel> getNextList() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("FROM AtendimentoModel WHERE STATUS = 0 ORDER BY id ASC");
            query.setMaxResults(3);

            return query.getResultList();
        } catch (Exception exception) {
            System.out.println("Nao tem proxima lista.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;

    }

    public List<AtendimentoModel> getChamadosList() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("FROM AtendimentoModel WHERE STATUS = 2 ORDER BY id DESC");
            query.setMaxResults(3);

            return query.getResultList();
        } catch (Exception exception) {
            System.out.println("Sem lista de chamados.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;

    }

    public AtendimentoModel getChamado() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("FROM AtendimentoModel WHERE STATUS = 1 ORDER BY id ASC");
            query.setMaxResults(1);

            return (AtendimentoModel) query.getSingleResult();
        } catch (Exception exception) {
            System.out.println("Nenhum atendimento em andamento.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;

    }

    public void updateJaAtendido() throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("UPDATE FROM AtendimentoModel SET STATUS = 2 WHERE STATUS = 1");

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            System.out.println("Nenhum atendimento em andamento.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public void update(AtendimentoModel atendimentoModel) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            if (entityManager.find(AtendimentoModel.class, atendimentoModel.getId()) != null) {
                entityManager.getTransaction().begin();
                entityManager.merge(atendimentoModel);
                entityManager.getTransaction().commit();
            }
        } catch (Exception exception) {
            System.out.println("Nao foi possivel atualizar o atendimento.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
