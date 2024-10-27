package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User u left join fetch u.car", User.class);
      return query.getResultList();
   }

   @Override
   @Transactional
   public User getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      return session
              .createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }
}



