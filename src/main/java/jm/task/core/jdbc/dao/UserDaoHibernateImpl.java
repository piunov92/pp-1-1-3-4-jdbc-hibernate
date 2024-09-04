package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = getSessionFactory();
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
//    public void createUsersTable() {
//        try (Session session = sessionFactory.openSession()) {
//            String sql = "CREATE TABLE IF NOT EXISTS users " +
//                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
//                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
//                "age TINYINT NOT NULL)";
//            transaction = session.beginTransaction();
//            session.createSQLQuery(/*"""
//                    CREATE TABLE IF NOT EXISTS `pp-1-1-3-4`.`users` (
//                      `id` INT NOT NULL AUTO_INCREMENT,
//                      `name` VARCHAR(45) NOT NULL,
//                      `lastName` VARCHAR(45) NOT NULL,
//                      `age` TINYINT NOT NULL,
//                      PRIMARY KEY (`id`));"""*/sql).addEntity(User.class).executeUpdate();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            System.err.println("error creating users table " + e.getMessage());
//        }
//    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
