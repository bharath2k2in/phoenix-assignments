package com.phoenix.assignment;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Map.Entry;

public class LogAnalyser {

    private static final int MINIMUM_COLUMNS = 3;
    private static final String INPUT_LOG_FILE = "src/main/resources/logFile.2015-09-10.log";
    private static final Pattern START_OF_LOG_LINE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static final String ERROR = "ERROR";

    public Map<String, Integer> generateLogReport(final LogType logType) throws IOException {

        final List<String> allLogs = Files.readAllLines(Paths.get(INPUT_LOG_FILE), Charset.defaultCharset());

        final List<String> specificLogs = new ArrayList<>();
        for (final String log : allLogs) {
            String[] columns = StringUtils.split(log);
            if (columns.length > MINIMUM_COLUMNS
                    && START_OF_LOG_LINE_PATTERN.matcher(columns[0]).matches()
                    && logType.name().equals(columns[2])) {
                specificLogs.add(concatLogDescription(columns));
            }
        }

        final Map<String, Integer> logOccurrences = findLogOccurrences(specificLogs);
        return sortLogs(logOccurrences);
    }

    private static Map<String, Integer> findLogOccurrences(final List<String> errorLines) {
        final Map<String, Integer> errorOccurrenceMap = new HashMap<>();
        for(final String errorLogLine : errorLines) {
            if(errorOccurrenceMap.containsKey(errorLogLine)) {
                errorOccurrenceMap.put(errorLogLine, errorOccurrenceMap.get(errorLogLine) + 1);
            } else {
                errorOccurrenceMap.put(errorLogLine, 1);
            }
        }
        return errorOccurrenceMap;
    }

    private static Map<String, Integer> sortLogs(Map<String, Integer> unsortMap) {

        final List<Entry<String, Integer>> unsortedList = new LinkedList<>(unsortMap.entrySet());

        Collections.sort(unsortedList, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        final Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : unsortedList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private static String concatLogDescription(final String[] errorLine) {
        return StringUtils.join(errorLine, " ", 4, errorLine.length);
    }
}
