package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getByCar(String model, int series) {
      Car car = (Car) sessionFactory.getCurrentSession().createQuery("from Car where model = :param1 and series = :param2" ).setParameter("param1", model).setParameter("param2", series).getSingleResult();
      User user = (User) sessionFactory.getCurrentSession().createQuery("from User where carDetail = :param1").setParameter("param1", car).getSingleResult();
      return user;
   }

}
