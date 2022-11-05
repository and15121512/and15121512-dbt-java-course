package ru.sber.javacourse.report.generator;

import ru.sber.javacourse.report.Report;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface ReportGenerator<T> {
    Report generate(Collection<T> entities, SortedMap<String, String> fields2Aliases, String sheetName);
}
