package org.eclipse.paho.client.mqttv3.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/* loaded from: classes.dex */
public class SimpleLogFormatter extends Formatter {
    private static final String LS = System.getProperty("line.separator");

    @Override // java.util.logging.Formatter
    public String format(LogRecord logRecord) {
        PrintWriter printWriter;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(logRecord.getLevel().getName()).append("\t");
        stringBuffer.append(String.valueOf(MessageFormat.format("{0, date, yy-MM-dd} {0, time, kk:mm:ss.SSSS} ", new Date(logRecord.getMillis()))) + "\t");
        String sourceClassName = logRecord.getSourceClassName();
        String str = "";
        if (sourceClassName != null) {
            int length = sourceClassName.length();
            if (length > 20) {
                str = logRecord.getSourceClassName().substring(length - 19);
            } else {
                str = new StringBuffer().append(sourceClassName).append(new char[]{' '}, 0, 1).toString();
            }
        }
        stringBuffer.append(str).append("\t").append(" ");
        stringBuffer.append(left(logRecord.getSourceMethodName(), 23, ' ')).append("\t");
        stringBuffer.append(logRecord.getThreadID()).append("\t");
        stringBuffer.append(formatMessage(logRecord)).append(LS);
        if (logRecord.getThrown() != null) {
            stringBuffer.append("Throwable occurred: ");
            Throwable thrown = logRecord.getThrown();
            try {
                StringWriter stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter);
                try {
                    thrown.printStackTrace(printWriter);
                    stringBuffer.append(stringWriter.toString());
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (printWriter != null) {
                        try {
                            printWriter.close();
                        } catch (Exception e2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                printWriter = null;
            }
        }
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length >= 0) {
                stringBuffer.append(c);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
