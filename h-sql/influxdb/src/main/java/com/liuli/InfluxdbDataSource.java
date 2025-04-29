package com.liuli;

import com.influxdb.LogLevel;
import com.influxdb.client.*;
import com.influxdb.exceptions.InfluxException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * influxdb数据源
 *
 * @author zhanghui
 * @version 1.0
 * @Description: influxdb数据源
 * @date 2022/12/12 16:55:42
 */
@Configuration
@Slf4j
public class InfluxdbDataSource {

    private static final Pattern TAGS_PROPERTY = Pattern.compile("(influx2\\.tags\\.)(.+)");
    private static final Pattern DURATION_PATTERN = Pattern.compile("^(\\d+)([a-zA-Z]{0,2})$");

//    @Value("${influxdb.file_url}")
//    String fileUrl;

    @Autowired
    InfluxdbProperties influxdbProperties;

    @Bean
    public InfluxDBClientOptions clientOptions() {
        try {
            String url=influxdbProperties.getUrl();
            String org=influxdbProperties.getOrg();
            String bucket=influxdbProperties.getBucket();
            String token=influxdbProperties.getToken();
            String logLevel=influxdbProperties.getLogLevel();
            String readTimeout=influxdbProperties.getReadTimeout();
            String writeTimeout=influxdbProperties.getWriteTimeout();
            String connectTimeout=influxdbProperties.getConnectTimeout();
//            File file = ResourceUtils.getFile(fileUrl);
//            InputStream inputStream = Files.newInputStream(file.toPath());
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            String url = properties.getProperty("influx2.url");
//            String org = properties.getProperty("influx2.org");
//            String bucket = properties.getProperty("influx2.bucket");
//            String token = properties.getProperty("influx2.token");
//            String logLevel = properties.getProperty("influx2.logLevel");
//            String readTimeout = properties.getProperty("influx2.readTimeout");
//            String writeTimeout = properties.getProperty("influx2.writeTimeout");
//            String connectTimeout = properties.getProperty("influx2.connectTimeout");
//            String precision = properties.getProperty("influx2.precision");
//            String consistency = properties.getProperty("influx2.consistency");
//            String clientType = properties.getProperty("influx2.clientType");
//            //
//            // Default tags
//            //
//            properties.stringPropertyNames().stream()
//                    .filter(TAGS_PROPERTY.asPredicate())
//                    .forEach(key -> {
//                        Matcher matcher = TAGS_PROPERTY.matcher(key);
//                        matcher.find();
//                        String tagKey = matcher.group(2);
//                        InfluxDBClientOptions.builder().addDefaultTag(tagKey, properties.getProperty(key).trim());
//                    });
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .protocols(Collections.singletonList(Protocol.HTTP_1_1));
            if (readTimeout != null) {
                client.readTimeout(toDuration(readTimeout));
            }
            if (writeTimeout != null) {
                client.writeTimeout(toDuration(writeTimeout));
            }
            if (connectTimeout != null) {
                client.connectTimeout(toDuration(connectTimeout));
            }
            InfluxDBClientOptions.Builder builder = InfluxDBClientOptions.builder();
            builder.okHttpClient(client);
            if (url != null) {
                builder.url(url);
            }
            if (org != null) {
                builder.org(org);
            }
            if (bucket != null) {
                builder.bucket(bucket);
            }
            if (token != null) {
                builder.authenticateToken(token.toCharArray());
            }
            if (logLevel != null) {
                builder.logLevel(Enum.valueOf(LogLevel.class, logLevel));
            }
//            if (precision != null) {
//                builder.precision(Enum.valueOf(WritePrecision.class, precision));
//            }
//            if (consistency != null) {
//                builder.consistency(Enum.valueOf(WriteConsistency.class, consistency));
//            }
//            if (clientType != null) {
//                builder.clientType(clientType);
//            }
            return builder.build();
        } catch (Exception e) {
            return null;
        }
        // return InfluxDBClientOptions.builder().loadProperties().build();
    }

    private Duration toDuration(@Nonnull final String value) {

        Matcher matcher = DURATION_PATTERN.matcher(value);
        if (!matcher.matches()) {
            throw new InfluxException("'" + value + "' is not a valid duration");
        }

        String amount = matcher.group(1);
        String unit = matcher.group(2);

        ChronoUnit chronoUnit;
        switch (unit != null && !unit.isEmpty() ? unit.toLowerCase() : "ms") {
            case "ms":
                chronoUnit = ChronoUnit.MILLIS;
                break;
            case "s":
                chronoUnit = ChronoUnit.SECONDS;
                break;
            case "m":
                chronoUnit = ChronoUnit.MINUTES;
                break;
            default:
                throw new InfluxException("unknown unit for '" + value + "'");
        }

        return Duration.of(Long.parseLong(amount), chronoUnit);
    }

    /**
     * 创建influxdb客户端
     *
     * @return InfluxDBClient
     */
    @Bean
    public InfluxDBClient influxdbclient(@NonNull InfluxDBClientOptions clientOptions) {
        Objects.requireNonNull(clientOptions, "InfluxDBClientOptions must not be null");
        return InfluxDBClientFactory.create(clientOptions);
    }

    /**
     * 创建influxdb 插入api
     *
     * @param influxdbclient influxdb客户端
     * @return WriteApiBlocking
     */
    @Bean
    public WriteApiBlocking writeApiBlocking(InfluxDBClient influxdbclient) {
        return influxdbclient.getWriteApiBlocking();
    }

    /**
     * 创建influxdb 查询api
     *
     * @param influxdbclient influxdb客户端
     * @return QueryApi
     */
    @Bean
    public QueryApi queryApi(InfluxDBClient influxdbclient) {
        return influxdbclient.getQueryApi();
    }

    /**
     * 创建influxdb 删除api
     *
     * @param influxdbclient influxdb客户端
     * @return DeleteApi
     */
    @Bean
    public DeleteApi deleteApi(InfluxDBClient influxdbclient) {
        return influxdbclient.getDeleteApi();
    }

    /**
     * 创建influxdb buckets api
     *
     * @param influxdbclient influxdb客户端
     * @return BucketsApi
     */
    @Bean
    public BucketsApi bucketsApi(InfluxDBClient influxdbclient) {
        return influxdbclient.getBucketsApi();
    }

}
