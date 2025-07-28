package br.com.senac.persistence;

import br.com.senac.model.RegistroVenda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class RegistroDAO {
    
    public void adicionar(RegistroVenda registro){
        
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(registro);
            em.getTransaction().commit();
        }
        catch(Exception e){
            if(em.getTransaction().isActive()){
               em.getTransaction().rollback();
            }
            throw e;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public List<RegistroVenda> listar(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<RegistroVenda> query = em.createQuery("SELECT r FROM RegistroVenda r", RegistroVenda.class);
            return query.getResultList();
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public void atualizar(RegistroVenda registro){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(registro);
            em.getTransaction().commit();
        }
        catch(Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public void excluir(int id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            RegistroVenda registro = em.find(RegistroVenda.class, id);
            if(registro != null){
                em.remove(registro);
            }
            em.getTransaction().commit();
        }
        catch(Exception e){
            if(em.getTransaction().isActive()){
               em.getTransaction().rollback();
            }
            throw e;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public RegistroVenda buscarId(Integer id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            RegistroVenda venda = em.find(RegistroVenda.class, id);
            if(venda != null){
                em.detach(venda);
            }
            return venda;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
}