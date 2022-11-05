package ru.sber.javacourse.report.generator.impl;

import ru.sber.javacourse.garage.Garage;
import ru.sber.javacourse.garage.Vehicle;
import ru.sber.javacourse.report.Report;
import ru.sber.javacourse.report.generator.ReportGenerator;
import ru.sber.javacourse.report.impl.ReportImpl;

import java.lang.reflect.Field;
import java.util.*;

public class VehicleReportGenerator<T extends Vehicle> implements ReportGenerator<T> {
    @Override
    public Report generate(Collection<T> entities, SortedMap<String, String> fields2Aliases, String sheetName) {
        if (!entities.isEmpty() && (fields2Aliases == null || fields2Aliases.isEmpty())) {
            fields2Aliases = getAsIsAliases(entities.iterator().next());
        }
        var reportRepr = new ArrayList<ArrayList<String>>();
        reportRepr.add(new ArrayList<>(fields2Aliases.values()));
        for (var entity : entities) {
            var fields2Values = entityFields(entity);
            var row = matchFieldsWithAliases(fields2Values, fields2Aliases);
            reportRepr.add(row);
        }
        return new ReportImpl(reportRepr, sheetName);
    }

    private SortedMap<String, String> getAsIsAliases(T entity) {
        var fieldMap = new TreeMap<String, String>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field.getName());
        }
        return fieldMap;
    }

    private Map<String, String> entityFields(T entity) {
        var fieldMap = new HashMap<String, String>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            try {
                fieldMap.put(field.getName(), String.valueOf(field.get(entity)));
            }
            catch (Exception ignore) {}
        }
        return fieldMap;
    }

    private ArrayList<String> matchFieldsWithAliases(
            Map<String, String> fields2Values,
            SortedMap<String, String> fields2Aliases
    ) {
        var values = new ArrayList<String>();
        for (var fieldName : fields2Aliases.keySet()) {
            values.add(fields2Values.getOrDefault(fieldName, "null"));
        }
        return values;
    }

}
