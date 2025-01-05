package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Optional<User> getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select u from User u join u.car c where c.model=:model and c.series=:series", User.class)
                .setParameter("model", model)
                .setParameter("series", series);
        List<User> result = query.getResultList();
        //я так понял что машина и пользователь уникальны и результат будет бинарным
        // - либо есть одно совпадение, либо нет совпадений вообще
        return result.size() == 1 ? Optional.of(result.getFirst()) : Optional.empty();
    }

}
