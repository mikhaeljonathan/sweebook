import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;

public class EmployeeHandler {

	public EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public JInternalFrame showManageEmployeeForm() {
		return new JInternalFrame();
	}
	
	public List<Employee> getAll(){
		return new ArrayList<Employee>();
	}
	
	public Employee findById(String id) {
		return new Employee();
	}
	
	public Employee insert(HashMap<String, String> inputs) {
		return new Employee();
	}
	
	public Employee update(HashMap<String, String> inputs) {
		return new Employee();
	}
	
	public Employee createWithPendingStatus(HashMap<String, String> inputs) {
		return new Employee();
	}
	
	public Employee createWithActiveStatus(HashMap<String, String> inputs) {
		return new Employee();
	}
	
	public Employee firedEmployee(String id) {
		return new Employee();
	}
	
	public Employee acceptEmployee(String id) {
		return new Employee();
	}
	
}
