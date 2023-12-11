package cesur.examen.domain.client;

import cesur.examen.common.HibernateUtil;
import cesur.examen.domain.car.Car;
import cesur.examen.domain.car.CarDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Froancisco Díaz Roldán
 * Fecha:11/12/2023
 */

public class ClientService {

    /**
     * Return a List of Client entities that have one or more cars of the given manufacturer.
     * If a client has more than one car of the manufacturer, it only appears once in
     * the list (similar to a Set). Tip: start querying to Car entities...
     *
     * @param manufacturer
     * @return the list of clients
     */

    public static List<Client> hasManufacturer(String manufacturer) {
        List<Client> out = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery(
                    "select distinct c from Client c " +
                            "join fetch c.cars car " +
                            "where car.manufacturer = :manufacturer",
                    Client.class);
            query.setParameter("manufacturer", manufacturer);
            out = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
