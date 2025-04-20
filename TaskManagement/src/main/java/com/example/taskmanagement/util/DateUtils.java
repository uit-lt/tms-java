package com.example.taskmanagement.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

  /**
   * Checks if a date is upcoming (within 3 days from now but not passed)
   */
  public static boolean isUpcoming(LocalDate date) {
    if (date == null)
      return false;

    LocalDate now = LocalDate.now();
    return date.isAfter(now) && ChronoUnit.DAYS.between(now, date) <= 3;
  }

  /**
   * Checks if a date is overdue (before today)
   */
  public static boolean isOverdue(LocalDate date) {
    if (date == null)
      return false;

    return date.isBefore(LocalDate.now());
  }
}
