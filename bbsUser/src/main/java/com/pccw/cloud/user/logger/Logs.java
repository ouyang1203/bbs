package com.pccw.cloud.user.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.slf4j.Marker;  
import org.slf4j.spi.LocationAwareLogger;  
  
/** 
 * 通用日志器，依赖于slf4j的API，最大限度优化性能 
 *  
 *  
 */  
public class Logs {  
  
    private static final Object[] EMPTY_ARRAY = new Object[] {};  
    private static final String FQCN = Logs.class.getName();  
  
    /** 
     * 获取适配日志器，供内部调用 
     *  
     * @return 
     */  
    @SuppressWarnings("restriction")  
    private static LocationAwareLogger getLocationAwareLogger(final int depth) {  
        String className = sun.reflect.Reflection.getCallerClass(depth)  
                .getName();  
        return (LocationAwareLogger) LoggerFactory.getLogger(className);  
    }  
  
    /** 
     * 静态的获取日志器 
     *  
     * @return 
     */  
    public static Logger getLogger() {  
        return getLocationAwareLogger(3);  
    }  
  
    public static String getName() {  
        return getLocationAwareLogger(3).getName();  
    }  
  
    public static boolean isTraceEnabled() {  
        return getLocationAwareLogger(3).isTraceEnabled();  
    }  
  
    public static void trace(String msg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void trace(String format, Object arg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.TRACE_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void trace(String format, Object arg1, Object arg2) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.TRACE_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void trace(String format, Object... arguments) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.TRACE_INT, format, arguments, null);  
    }  
  
    public static void trace(String msg, Throwable t) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isTraceEnabled(Marker marker) {  
        return getLocationAwareLogger(3).isTraceEnabled(marker);  
    }  
  
    public static void trace(Marker marker, String msg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void trace(Marker marker, String format, Object arg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.TRACE_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void trace(Marker marker, String format, Object arg1,  
            Object arg2) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.TRACE_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void trace(Marker marker, String format, Object... argArray) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.TRACE_INT, format, argArray, null);  
    }  
  
    public static void trace(Marker marker, String msg, Throwable t) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.TRACE_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isInfoEnabled() {  
        return getLocationAwareLogger(3).isInfoEnabled();  
    }  
  
    public static void error(Exception e) {
    	StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter(); 
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally {
			if(sw!=null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(pw!=null) {
				pw.close();
			}
		}
		if(sw!=null) {
			String str = sw.toString().replaceAll("(\\r\\n|\\r|\\n|\\n\\r)", "");
			getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.ERROR_INT,  
					str, EMPTY_ARRAY, null);  
		}
    }
    
    public static void info(String msg) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.INFO_INT,  
                msg, EMPTY_ARRAY, null);  
    }  
  
    public static void info(String format, Object arg) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.INFO_INT,  
                format, new Object[] { arg }, null);  
    }  
  
    public static void info(String format, Object arg1, Object arg2) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.INFO_INT,  
                format, new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void info(String format, Object... arguments) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.INFO_INT,  
                format, arguments, null);  
    }  
  
    public static void info(String msg, Throwable t) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.INFO_INT,  
                msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isInfoEnabled(Marker marker) {  
        return getLocationAwareLogger(3).isInfoEnabled(marker);  
    }  
  
    public static void info(Marker marker, String msg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void info(Marker marker, String format, Object arg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.INFO_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void info(Marker marker, String format, Object arg1,  
            Object arg2) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.INFO_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void info(Marker marker, String format, Object... argArray) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.INFO_INT, format, argArray, null);  
    }  
  
    public static void info(Marker marker, String msg, Throwable t) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.INFO_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isDebugEnabled() {  
        return getLocationAwareLogger(3).isDebugEnabled();  
    }  
  
    public static void debug(String msg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void debug(String format, Object arg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void debug(String format, Object arg1, Object arg2) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.DEBUG_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void debug(String format, Object... arguments) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.DEBUG_INT, format, arguments, null);  
    }  
  
    public static void debug(String msg, Throwable t) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isDebugEnabled(Marker marker) {  
        return getLocationAwareLogger(3).isDebugEnabled(marker);  
    }  
  
    public static void debug(Marker marker, String msg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void debug(Marker marker, String format, Object arg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.DEBUG_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void debug(Marker marker, String format, Object arg1,  
            Object arg2) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.DEBUG_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void debug(Marker marker, String format, Object... argArray) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.DEBUG_INT, format, argArray, null);  
    }  
  
    public static void debug(Marker marker, String msg, Throwable t) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.DEBUG_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isWarnEnabled() {  
        return getLocationAwareLogger(3).isWarnEnabled();  
    }  
  
    public static void warn(String msg) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.WARN_INT,  
                msg, EMPTY_ARRAY, null);  
    }  
  
    public static void warn(String format, Object arg) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.WARN_INT,  
                format, new Object[] { arg }, null);  
    }  
  
    public static void warn(String format, Object arg1, Object arg2) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.WARN_INT,  
                format, new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void warn(String format, Object... arguments) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.WARN_INT,  
                format, arguments, null);  
    }  
  
    public static void warn(String msg, Throwable t) {  
        getLocationAwareLogger(3).log(null, FQCN, LocationAwareLogger.WARN_INT,  
                msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isWarnEnabled(Marker marker) {  
        return getLocationAwareLogger(3).isWarnEnabled(marker);  
    }  
  
    public static void warn(Marker marker, String msg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void warn(Marker marker, String format, Object arg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.WARN_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void warn(Marker marker, String format, Object arg1,  
            Object arg2) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.WARN_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void warn(Marker marker, String format, Object... argArray) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.WARN_INT, format, argArray, null);  
    }  
  
    public static void warn(Marker marker, String msg, Throwable t) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.WARN_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isErrorEnabled() {  
        return getLocationAwareLogger(3).isErrorEnabled();  
    }  
  
    public static void error(String msg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void error(String format, Object arg) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.ERROR_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void error(String format, Object arg1, Object arg2) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.ERROR_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void error(String format, Object... arguments) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.ERROR_INT, format, arguments, null);  
    }  
  
    public static void error(String msg, Throwable t) {  
        getLocationAwareLogger(3).log(null, FQCN,  
                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);  
    }  
  
    public static boolean isErrorEnabled(Marker marker) {  
        return getLocationAwareLogger(3).isErrorEnabled(marker);  
    }  
  
    public static void error(Marker marker, String msg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, null);  
    }  
  
    public static void error(Marker marker, String format, Object arg) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.ERROR_INT, format, new Object[] { arg },  
                null);  
    }  
  
    public static void error(Marker marker, String format, Object arg1,  
            Object arg2) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.ERROR_INT, format,  
                new Object[] { arg1, arg2 }, null);  
    }  
  
    public static void error(Marker marker, String format, Object... argArray) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.ERROR_INT, format, argArray, null);  
    }  
  
    public static void error(Marker marker, String msg, Throwable t) {  
        getLocationAwareLogger(3).log(marker, FQCN,  
                LocationAwareLogger.ERROR_INT, msg, EMPTY_ARRAY, t);  
    }  
  
} 