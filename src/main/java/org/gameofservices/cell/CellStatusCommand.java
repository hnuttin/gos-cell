package org.gameofservices.cell;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellStatusCommand extends HystrixCommand<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellStatusCommand.class);

    private final String url;

    public CellStatusCommand(String url) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CellStatusCommand"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(url.substring(7, 9)))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(2)
                        .withCircuitBreakerSleepWindowInMilliseconds(30000)));
        this.url = url;
    }

    @Override
    protected Boolean run() throws Exception {
        boolean status = false;

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);

        try {
            int statusCode = client.executeMethod(method);
            LOGGER.info("cell status of {}: {}", url, statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                status = true;
            }
        } finally {
            method.releaseConnection();
        }

        return status;
    }

    @Override
    protected Boolean getFallback() {
        return false;
    }
}
