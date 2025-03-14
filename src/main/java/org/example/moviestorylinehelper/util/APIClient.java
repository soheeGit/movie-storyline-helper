package org.example.moviestorylinehelper.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.moviestorylinehelper.model.dto.APIClientParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public interface APIClient {

    private Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }

    private void info(String message) {
        getLogger().info(message);
    }

    private void error(String message) {
        getLogger().severe(message);
    }

    default public String callAPI(APIClientParam param) {
        try {
            HttpResponse<String> response = httpClient.send(buildRequest(param), HttpResponse.BodyHandlers.ofString());
            info("%d".formatted(response.statusCode()));
            return response.body();
        }catch (Exception e) {
            error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    HttpClient httpClient = HttpClient.newHttpClient();

    private HttpRequest buildRequest(APIClientParam param){
        return HttpRequest.newBuilder()
                .uri(URI.create(param.url()))
                .method(param.method(), HttpRequest.BodyPublishers.ofString(param.body()))
                .headers(param.headers())
                .build();
    }
}
