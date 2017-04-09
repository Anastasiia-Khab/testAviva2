package src.sampledata;

import static src.sampledata.SampleAvivaEmployees.ANNA;
import static src.sampledata.SampleAvivaEmployees.JOHN;
import static src.sampledata.SampleAvivaEmployees.ROB;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import src.model.Holiday;

import com.google.common.collect.ImmutableList;

public class SampleAvivaHolidays {

	private static final List<Holiday> JOHN_HOLIDAYS = ImmutableList.of(
			new Holiday(JOHN, LocalDate.of(2017, Month.FEBRUARY, 2), LocalDate
					.of(2017, Month.FEBRUARY, 3)),
			new Holiday(JOHN, LocalDate.of(2017, Month.JULY, 10), LocalDate.of(
					2017, Month.AUGUST, 10)));

	private static final List<Holiday> ANNA_HOLIDAYS = ImmutableList
			.of(new Holiday(ANNA, LocalDate.of(2017, Month.JULY, 13), LocalDate
					.of(2017, Month.AUGUST, 1)));

	private static final List<Holiday> ROB_HOLIDAYS = ImmutableList.of(
			new Holiday(ROB, LocalDate.of(2017, Month.FEBRUARY, 2), LocalDate
					.of(2017, Month.FEBRUARY, 2)),
			new Holiday(ROB, LocalDate.of(2017, Month.JULY, 5), LocalDate.of(
					2017, Month.AUGUST, 5)),
			new Holiday(ROB, LocalDate.of(2017, Month.AUGUST, 8), LocalDate.of(
					2017, Month.AUGUST, 12)));

	public static final List<Holiday> AVIVA_HOLIDAYS = new ImmutableList.Builder<Holiday>()
			.addAll(JOHN_HOLIDAYS).addAll(ANNA_HOLIDAYS).addAll(ROB_HOLIDAYS)
			.build();

}
