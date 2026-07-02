package com.ernoxin.fipiranapi.common.util;

public final class JalaliDateTimeFormatter {

    private JalaliDateTimeFormatter() {
    }

    public static String formatGregorianDevenHeven(Integer deven, Integer hEven) {
        return JalaliDateTimeParsing.formatGregorianDevenHeven(deven, hEven);
    }

    public static Integer gregorianDevenToJalaliDeven(Integer deven) {
        return JalaliDateTimeParsing.gregorianDevenToJalaliDeven(deven);
    }

    public static String normalizeDisplayDateTime(String raw) {
        return JalaliDateTimeParsing.normalizeDisplayDateTime(raw);
    }

    public static String fromIsoDateAndHeven(String transactionDate, Integer hEven) {
        return JalaliDateTimeParsing.fromIsoDateAndHeven(transactionDate, hEven);
    }

    public static String firstNonBlank(String... values) {
        return JalaliDateTimeParsing.firstNonBlank(values);
    }
}
