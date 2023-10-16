package com.liuli.filter;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogFilter extends Filter<LoggingEvent> {
    @Override
    public FilterReply decide(LoggingEvent event) {
        String loggerName = event.getLoggerName();
        if(loggerName.startsWith("com.demo")||loggerName.startsWith("com.yomahub")){
            //项目本身的日志才会入库
//            EasySlf4jLogging log = new EasySlf4jLogging();
//            log.setLogTime(DateUtils.getCurrentLocalDateTime());
//            log.setLogThread(event.getThreadName());
//            log.setLogClass(loggerName);
//            log.setLogLevel(event.getLevel().levelStr);
//            log.setTrackId(event.getMDCPropertyMap().get("TRACE_ID"));
//            log.setLogContent(event.getFormattedMessage());//日
//            System.err.println(event.getFormattedMessage());
//            System.err.println(event.getThreadName());
//            System.err.println(loggerName);
//            System.err.println(event.getLevel().levelStr);
//            System.err.println(event.getMDCPropertyMap().get("TRACE_ID"));
            return FilterReply.ACCEPT;
        }else{
            //非项目本身的日志不会入库
            return FilterReply.DENY;
        }

        //delete from easy_slf4j_logging where log_time <= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
    }
}
