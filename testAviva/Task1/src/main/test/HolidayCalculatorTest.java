package src.main.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import src.main.HolidayCalculator;
import src.model.Employee;
import src.model.Holiday;

import com.google.common.collect.ImmutableList;

@SuppressWarnings("deprecation")
public class HolidayCalculatorTest {

	public static List<Employee> AVIVA_EMPLOYEES;
	public static List<Holiday> AVIVA_HOLIDAYS;

	@BeforeClass
	public static void onlyOnce() {
		Employee JOHN = new Employee("JOHN");
		Employee ANNA = new Employee("ANNA");
		Employee ROB = new Employee("ROB");
		Employee LUCAS = new Employee("LUCAS");
		AVIVA_EMPLOYEES = new ImmutableList.Builder<Employee>().add(JOHN)
				.add(ANNA).add(ROB).add(LUCAS).build();
		List<Holiday> JOHN_HOLIDAYS = ImmutableList.of(
				new Holiday(JOHN, LocalDate.of(2017, Month.FEBRUARY, 2),
						LocalDate.of(2017, Month.FEBRUARY, 3)),
				new Holiday(JOHN, LocalDate.of(2017, Month.JULY, 10), LocalDate
						.of(2017, Month.AUGUST, 10)));

		List<Holiday> ANNA_HOLIDAYS = ImmutableList.of(new Holiday(ANNA,
				LocalDate.of(2017, Month.JULY, 13), LocalDate.of(2017,
						Month.AUGUST, 1)));

		List<Holiday> ROB_HOLIDAYS = ImmutableList.of(
				new Holiday(ROB, LocalDate.of(2017, Month.FEBRUARY, 2),
						LocalDate.of(2017, Month.FEBRUARY, 2)),
				new Holiday(ROB, LocalDate.of(2017, Month.JULY, 5), LocalDate
						.of(2017, Month.AUGUST, 5)),
				new Holiday(ROB, LocalDate.of(2017, Month.AUGUST, 8), LocalDate
						.of(2017, Month.AUGUST, 12)));
		AVIVA_HOLIDAYS = new ImmutableList.Builder<Holiday>()
				.addAll(JOHN_HOLIDAYS).addAll(ANNA_HOLIDAYS)
				.addAll(ROB_HOLIDAYS).build();
	}

	@Test
	public void GetWorkingEmployeesFromOneDayShouldReturnAllEmployees() {
		HolidayCalculator calculator = new HolidayCalculator(AVIVA_EMPLOYEES,
				AVIVA_HOLIDAYS);
		Assert.assertEquals(
				AVIVA_EMPLOYEES.size(),
				calculator.getWorkingEmployees(
						LocalDate.of(2017, Month.JANUARY, 1),
						LocalDate.of(2017, Month.JANUARY, 1)).size());

	}

	@Test
	public void GetWorkingEmployeesFromAllYearShouldReturnNull() {
		HolidayCalculator calculator = new HolidayCalculator(AVIVA_EMPLOYEES,
				AVIVA_HOLIDAYS);
		Assert.assertEquals(
				1,
				calculator.getWorkingEmployees(
						LocalDate.of(2017, Month.JANUARY, 1),
						LocalDate.of(2017, Month.DECEMBER, 1)).size());

	}

	@Test
	public void testGetHolidayMonth() {
		HolidayCalculator calculator = new HolidayCalculator(AVIVA_EMPLOYEES,
				AVIVA_HOLIDAYS);
		Assert.assertEquals(Month.JULY, calculator.getHolidayMonth(2017));

	}

}
