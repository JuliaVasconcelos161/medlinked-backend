package com.medlinked.utils;

import java.time.format.DateTimeFormatter;

public class JavaDateFormatter {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter EMAILFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm'hrs.'");
}
