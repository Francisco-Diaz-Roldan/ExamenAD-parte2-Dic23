package cesur.examen.domain.car;

import cesur.examen.common.DAO;
import cesur.examen.common.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 Nombre del alumno: Froancisco Díaz Roldán
 * Fecha:11/12/2023
 */

@Log
public class CarDAO implements DAO<Car> {
    @Override
    public Car save(Car car) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                s.persist(car);
                t.commit();
            } catch (Exception e) {
                if (t != null) {
                    t.rollback();
                }
                e.printStackTrace();
            }
            return car;
        }
    }

    @Override
    public Car update(Car car) {
        return null;
    }

    @Override
    public boolean remove(Car car) {
        return false;
    }

    @Override
    public Car get(Long id) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    public List<Car> getAllByManufacturer(String manufacturer) {
        List<Car> out = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Car c WHERE c.manufacturer = :manufacturer";
            Query<Car> query = session.createQuery(hql, Car.class);

            query.setParameter("manufacturer", manufacturer);

            out.addAll(query.getResultList());

            System.out.println("Coches con el fabricante '" + manufacturer + "': " + out.size());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        System.out.println("Método getAllByManufacturer implementado");
        return out;
    }
}
