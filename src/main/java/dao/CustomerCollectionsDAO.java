package dao;
 
import domain.Customer;
import java.util.HashMap;
import java.util.Map;
 
public final class CustomerCollectionsDAO implements CustomerDao {
 
	private static final Map<String, Customer> customers = new HashMap<>();
 
	public CustomerCollectionsDAO() {
		// some dummy data for testing
		Customer boris = new Customer();
		boris.setUsername("boris");
		boris.setFirst_Name("Boris");
		boris.setSurname("McNorris");
		boris.setPassword("guest");
		boris.setShippingAddress("123 Some Street,\nNorth East Valley,\nDunedin");
		boris.setEmailAddress("boris@example.net");
		boris.setCreditCardDetails("Visa 1234-2345-3456-4567, Exp 03/2019, CVC 241");
 
		Customer doris = new Customer();
		doris.setUsername("doris");
		doris.setFirst_Name("Doris");
		doris.setSurname("Dolores");
		doris.setPassword("guest");
		doris.setShippingAddress("321 Anywere Ave,\nSt Clair,\nDunedin");
		doris.setEmailAddress("doris@example.net");
		doris.setCreditCardDetails("Visa 7654-6543-5432-4321, Exp 05/2019, CVC 853");
 
		save(boris);
		save(doris);
	}
 
	@Override
	public void save(Customer customer) {
		System.out.println("Saving customer: " + customer);
		customers.put(customer.getUsername(), customer);
	}
 
	@Override
	public Customer getCustomer(String username) {
		return customers.get(username);
	}
 
	@Override
	public Boolean validateCredentials(String username, String password) {
		if (customers.containsKey(username)) {
			return customers.get(username).getPassword().equals(password);
		} else {
			return false;
		}
	}
 
}