/*
 * Trabalhodesenvolvido para disciplina de ISS - 2016
 */
package dao;

import java.util.List;
import modelo.HibernateUtil;
import modelo.Reserva;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gabriel
 */
public class ReservaDAO {
    public void cadastroReserva(Reserva reserva) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(reserva);
        
        tx.commit();
        
        session.flush();
        session.close();
    }
    
    public boolean verificaReserva(int idMaterial, int idUsuario) {
        Criteria crit;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        crit = session.createCriteria(Reserva.class);
        crit.add(Restrictions.eq("idMaterial", idMaterial));
        crit.add(Restrictions.eq("idUsuario", idUsuario));
        List<Reserva> resultado = crit.list();

        session.flush();
        session.close();
        
        if (resultado.isEmpty()) {
            System.out.println("true");
        }
        return resultado.isEmpty();
    }
}
