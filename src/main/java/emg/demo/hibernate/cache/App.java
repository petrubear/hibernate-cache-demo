package emg.demo.hibernate.cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class App {
	public static void main(String[] args) {
		// creo la instancia de hazelcast porque uso la configuracion de
		// native-client
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();

		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		// crea la tabla si no existe
		// new SchemaExport(cfg).execute(true, true, false, false);

		Session session1 = factory.openSession();
		// data de ejemplo
		/*
		 * Transaction t = session1.beginTransaction(); Employee newEmp = new
		 * Employee(); newEmp.setId(121); newEmp.setName("edison");
		 * newEmp.setSalary(1000); session1.save(newEmp); t.commit();
		 */

		// id del empleado que voy a consultar, puede no existir en la base D:
		final int empId = 3;
		Employee emp1 = (Employee) session1.load(Employee.class, new Integer(
				empId));
		System.out.println(emp1.getId() + " " + emp1.getName() + " "
				+ emp1.getSalary());
		session1.close();

		Session session2 = factory.openSession();
		Employee emp2 = (Employee) session2.load(Employee.class, new Integer(
				empId));
		System.out.println(emp2.getId() + " " + emp2.getName() + " "
				+ emp2.getSalary());
		session2.close();

	}
}
