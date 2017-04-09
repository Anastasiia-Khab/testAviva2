package src.main;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.model.Employee;
import src.model.Holiday;

public class HolidayCalculator {

	private final List<Employee> employees;

	private final List<Holiday> holidays;

	public HolidayCalculator(List<Employee> employees, List<Holiday> holidays) {
		this.employees = employees;
		this.holidays = holidays;
	}

	// 1
	public List<Employee> getWorkingEmployees(LocalDate dateFrom,
			LocalDate dateTo) {
		return employees
				.stream()
				.filter(e -> check(dateFrom, dateTo, e)
						|| !holidays.stream().anyMatch(
								h -> h.getEmployee().getName()
										.equals(e.getName())))
				.collect(Collectors.toList());
	}

	// checks if employee don't have a holidays in specific date range
	private boolean check(LocalDate dateFrom, LocalDate dateTo,
			Employee employee) {
		return holidays
				.stream()
				.filter(h -> (h.getDateFrom().isAfter(dateFrom) && h
						.getDateFrom().isAfter(dateTo))
						|| (h.getDateTo().isBefore(dateFrom) && h.getDateTo()
								.isBefore(dateTo)))
				.anyMatch(
						h -> h.getEmployee().getName()
								.equals(employee.getName()));

	}

	// 2
	public Month getHolidayMonth(int year) {
		Map<Month, List<Employee>> map = new HashMap<>();
		for (Holiday holiday : holidays) {
			// Checks if holiday is in this year
			// This condition is not covered with tests
			// because in your sample there was no situation that someone has
			// holiday,
			// which starts in one year and ends in another
			// also I don't know if it is needed in general
			if (holiday.getDateFrom().getYear() == year
					|| holiday.getDateTo().getYear() == year) {
				// foreach Month in range
				for (int i = holiday.getDateFrom().getMonthValue(); i <= holiday
						.getDateTo().getMonthValue(); i++) {
					Month month = Month.of(i);
					// checks if in this month we already have some holidays
					if (!map.containsKey(month)) {
						map.put(month, new ArrayList<>());
					}
					// checks if this Employee(by Name of Employee)already have
					// holiday in this month
					if (!contains(map.get(month), holiday.getEmployee())) {
						map.get(month).add(holiday.getEmployee());
					}
				}
			}
		}
		// returns those Month wich in Map has as Value the longest List of
		// Employees
		return map.entrySet().stream().max((e1, e2) -> {
			int tmp = e1.getValue().size() - e2.getValue().size();
			if (tmp != 0)
				return tmp;
			return -(e1.getKey().compareTo(e2.getKey()));
		}).get().getKey();
	}

	// I had to do ike this because we don't have Employee.hashcode implemented
	// check by Name if Employee is in List of Employees
	private boolean contains(Collection<Employee> list, Employee employee) {
		for (Employee empl : list) {
			if (empl.getName().equals(employee.getName())) {
				return true;
			}
		}
		return false;
	}
}
