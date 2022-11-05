package ru.sber.javacourse.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Report {
    byte[] asBytes() throws IOException;
    void writeTo(OutputStream os) throws IOException;

    ArrayList<ArrayList<String>> getReportData();
}
