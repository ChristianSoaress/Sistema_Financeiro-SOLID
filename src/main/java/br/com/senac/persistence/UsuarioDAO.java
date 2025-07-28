package br.com.senac.persistence;

import br.com.senac.model.Usuario;
import br.com.senac.repository.UsuarioRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UsuarioDAO implements UsuarioRep {
    
    @Override
    public void cadastrarUsuario(Usuario usuario){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(usuario);
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
    
    @Override
    public Usuario autenticar(String username, String senha){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username AND u.senha = :senha", Usuario.class);
            query.setParameter("username", username);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }
    
    @Override
    public void excluir(int id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if(usuario != null){
                em.remove(usuario);
            }
            em.getTransaction().commit();
        }
        catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    @Override
    public List<Usuario> listar(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            return query.getResultList();
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    @Override
    public boolean podeRemoverUsuario(int idUsuario, int idUsuarioAutenticado){
        return idUsuario != idUsuarioAutenticado;
    }
}
