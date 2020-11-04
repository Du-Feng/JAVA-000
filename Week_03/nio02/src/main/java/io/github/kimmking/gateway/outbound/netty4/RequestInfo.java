package io.github.kimmking.gateway.outbound.netty4;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestInfo  {
    private final URI uri;
    private final List<RequestHeader> headers;

    RequestInfo(Builder builder) {
        this.uri = builder.uri;
        this.headers = builder.headers;
    }

    public String method() {
        return "GET";
    }

    public URI uri() {
        return uri;
    }

    public List<RequestHeader> headers() {
        return headers;
    }

    public static class Builder {
        private URI uri;
        private final List<RequestHeader> headers = new ArrayList<>();

        public Builder uri(String url) {
            try {
                this.uri = new URI(url);
            } catch (URISyntaxException ignored) {
            }
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.add(new RequestHeader(key, value));
            return this;
        }

        public Builder headers(List<RequestHeader> headers) {
            this.headers.addAll(headers);
            return this;
        }

        public RequestInfo build() {
            Objects.requireNonNull(uri, "Uri is in wrong format or not set!");
            return new RequestInfo(this);
        }
    }
}