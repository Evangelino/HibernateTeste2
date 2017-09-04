package modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.domain.Cliente;

/**
 *
 * @author Evangelino
 */
public class ClienteDao {

    public void salvarAtualizar(Cliente cliente) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        if (cliente.getCodigo() != null) {
            cliente = em.merge(cliente);

        }
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();

    }

    public void excluir(Cliente cliente) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();

        cliente = em.merge(cliente);

        em.remove(cliente);
        em.getTransaction().commit();
        em.close();

    }

    public List <Cliente> pesquisar(Cliente cliente) {
        EntityManager em = Conexao.getEntityManager();
        StringBuilder sql = new StringBuilder("from Cliente where 1 = 1");
        if (cliente.getCodigo() != null) {
            sql.append("and c.codigo= :codigo ");

        }
        if (cliente.getNome() != null && !cliente.getNome().equals("")) {
            sql.append("and c.nome like :nome ");

        }
        Query query = em.createQuery(sql.toString());
        if (cliente.getCodigo() != null) {
            query.setParameter("codgio", cliente.getCodigo());

        }
        if (cliente.getNome() != null && !cliente.getNome().equals("")) {
            query.setParameter("nome","%"+cliente.getNome());
        }
        return query.getResultList();
    }
}
