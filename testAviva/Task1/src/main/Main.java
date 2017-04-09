package src.main;

import java.time.LocalDate;
import java.time.Month;

import src.sampledata.SampleAvivaEmployees;
import src.sampledata.SampleAvivaHolidays;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HolidayCalculator calculator = new HolidayCalculator(
				SampleAvivaEmployees.AVIVA_EMPLOYEES,
				SampleAvivaHolidays.AVIVA_HOLIDAYS);
		System.out.println(calculator.getWorkingEmployees(
				LocalDate.of(2017, Month.JANUARY, 1),
				LocalDate.of(2017, Month.JANUARY, 1)));
		System.out.println(calculator.getHolidayMonth(2017));
		System.out.println(calculator.getWorkingEmployees(
				LocalDate.of(2017, Month.JANUARY, 1),
				LocalDate.of(2017, Month.DECEMBER, 1)));
	}

}
