package no.cantara.base.commands.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpResponse {
    private static final Logger LOG = LoggerFactory.getLogger(HttpResponse.class);

    private String body;
    private int statusCode;
    private Map<String, List<String>> headers;
    private String reasonPhrase;

    public HttpResponse(int statusCode, byte[] body, String reasonPhrase, Map<String, List<String>> headers) {
        this(statusCode, new String(body), reasonPhrase, headers);
    }

    public HttpResponse(int statusCode, String body, String reasonPhrase, Map<String, List<String>> headers) {
        this.body = body;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public List<String> getHeader(String name) {
        boolean containsHeader = headers != null && headers.containsKey(name);
        if (!containsHeader) {
            LOG.warn("Header with name {} not found", name);
            return Collections.emptyList();
        }

        return headers.get(name);
    }
}
