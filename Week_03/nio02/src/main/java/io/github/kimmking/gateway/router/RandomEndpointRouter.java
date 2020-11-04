package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomEndpointRouter implements HttpEndpointRouter {
    private static final Random RANDOM = new Random();

    @Override
    public String route(List<String> endpoints) {
        int index = RANDOM.nextInt(endpoints.size());
        return endpoints.get(index);
    }
}
