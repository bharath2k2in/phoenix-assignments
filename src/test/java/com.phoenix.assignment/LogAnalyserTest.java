package com.phoenix.assignment;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class LogAnalyserTest {

    private LogAnalyser logAnalyser;

    @Before
    public void setup() {
        logAnalyser = new LogAnalyser();
    }

    @Test
    public void testGenerateErrorLogReport() throws IOException {
        printAndAssertLogOccurrences(26, LogType.ERROR);
    }

    @Test
    public void testGenerateDebugLogReport() throws IOException {
        printAndAssertLogOccurrences(1397, LogType.DEBUG);
    }

    @Test
    public void testGenerateWarnLogReport() throws IOException {
        printAndAssertLogOccurrences(13, LogType.WARN);
    }

    @Test
    public void testGenerateInfoLogReport() throws IOException {
        printAndAssertLogOccurrences(180, LogType.INFO);
    }

    private void printAndAssertLogOccurrences(final int occurrences, final LogType logType) throws IOException {
        Map<String, Integer> logReport = logAnalyser.generateLogReport(logType);
        printReport(logReport, logType);
        Assert.assertNotNull(logReport);
        Assert.assertEquals(occurrences, calculateLogOccurrences(logReport));
    }

    private int calculateLogOccurrences(final Map<String, Integer> report) {
        int logCount = 0;
        Set<Map.Entry<String, Integer>> entries = report.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            logCount += entry.getValue();
        }
        return logCount;
    }

    private static void printReport(final Map<String, Integer> report, final LogType logType) {
        final Set<Map.Entry<String, Integer>> entries = report.entrySet();
        System.out.println("\n\n-----------------------------------");
        System.out.println("COUNT :: " + logType.name() + " DESCRIPTIONS");
        System.out.println("-----------------------------------");
        for(final Map.Entry<String, Integer> entry : entries) {
            System.out.println("  " + entry.getValue() + "   :: " + entry.getKey());
        }
    }
}
