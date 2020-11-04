package io.github.kimmking.gateway.outbound.netty4;

import java.net.URI;
import java.util.List;

public interface HttpRequest {
    String method();

    URI uri();

    List<HttpHeader> headers();
}
