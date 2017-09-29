package no.cantara.base.commands.http;

import com.github.kevinsawicki.http.HttpRequest;
import no.cantara.base.commands.BaseHystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public abstract class BaseHystrixHttpCommand<Response> extends BaseHystrixCommand<Response> {
    protected Logger log;

    protected BaseHystrixHttpCommand(String groupKey, int timeout) {
        super(groupKey, timeout);
        initialize();
    }

    protected BaseHystrixHttpCommand(String groupKey) {
        super(groupKey);
        initialize();
    }

    protected abstract HttpRequest getRequest() throws MalformedURLException;
    protected abstract Response dealWithResponse(HttpResponse response);

    @Override
    protected Response run() throws Exception {
        try {
            HttpRequest request = initializeRequest();

            String url = request.url().toString();
            String method = request.method();
            log.trace("Request: {} - {}", method, url);

            request = dealWithRequestBeforeSend(request);
            HttpResponse response = getResponse(request);
            onCompleted(response);

            boolean isSuccessful = response.isSuccessful();
            if (isSuccessful) {
                return dealWithResponse(response);
            }

            return dealWithFailedResponse(response);
        } catch(Exception exception) {
            log.error("Request failed - Application authentication failed ", exception);
            throw exception;
        }
    }

    @Override
    protected Response getFallback() {
        log.trace("Fallback - Request failed");
        return null;
    }

    protected Response dealWithFailedResponse(HttpResponse response) {
        return null;
    }

    protected HttpRequest dealWithRequestBeforeSend(HttpRequest request) {
        return request;
    }

    protected HttpResponse getResponse(HttpRequest request) {
        byte[] responseBody = request.bytes();
        int statusCode = request.code();
        String reasonPhrase = request.message();
        Map<String, List<String>> headers = request.headers();

        return new HttpResponse(statusCode, responseBody, reasonPhrase, headers);
    }

    private void initialize() {
        String tag = this.getClass().getName();
        this.log = LoggerFactory.getLogger(tag);
    }

    private HttpRequest initializeRequest() throws MalformedURLException {
        HttpRequest request = getRequest();

//        request.trustAllCerts();
//        request.trustAllHosts();

        return request;
    }

    private void onCompleted(HttpResponse response) {
        String result = response.isSuccessful() ? "OK" : "FAILED";
        String reason = response.getReasonPhrase();
        String responseBody = response.getBody();
        int statusCode = response.getStatusCode();

        log.trace("Response - {}: {} {} - {}", result, statusCode, reason, responseBody);
    }
}
