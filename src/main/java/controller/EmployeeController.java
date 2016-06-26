package controller;


import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	MongoOperations mongoOperations;
	
	@RequestMapping("/")
	String showHome()
	{
		return "index";
	}
	
	@RequestMapping(value="/employee",method=RequestMethod.POST)
	String showResult(@ModelAttribute Employee employee,Model model)
	{
		//insert into database
		model.addAttribute("employee", employee);
		Employee temp = new Employee(employee.getFirstName(),employee.getLastName(),employee.getRole());
		mongoOperations.save(temp);
		return "redirect:";
	}
	
	@RequestMapping(value="/employee",method=RequestMethod.GET)
	String showForm(@ModelAttribute Employee employee,Model model)
	{
		model.addAttribute("employee", new Employee());
		return "create";
	}
	
	@RequestMapping(value="/employees",method=RequestMethod.GET)
	String showAll(Model model)
	{
		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = (List<Employee>) mongoOperations.findAll(Employee.class);
		model.addAttribute("list",listOfEmployees);
		return "employees";
	}
	
	@RequestMapping(value="/employees",method = RequestMethod.POST)
	String updateEmployee(@ModelAttribute Employee employee, @RequestParam String action)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(employee.getEmployee_Id()));
		if(action.equals("delete"))
		{
			mongoOperations.remove(query, Employee.class);
		}
		else
		{
			//Employee temp = new Employee(employee.getFirstName(),employee.getLastName(),employee.getRole());
			Update update = new Update();
			update.set("firstName", employee.getFirstName());
			update.set("lastName",employee.getLastName());
			update.set("role",employee.getRole());	
			mongoOperations.updateFirst(query, update, Employee.class);
		}
		return "redirect:employees";
	}
	
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	String viewProfile(@PathVariable("id") String id, Model model)
	{
		Query query= new Query();
		Employee emp = mongoOperations.findOne(query.addCriteria(Criteria.where("_id").is(id)), Employee.class);
		model.addAttribute("employee",emp);
		return "profile";
	}
}
