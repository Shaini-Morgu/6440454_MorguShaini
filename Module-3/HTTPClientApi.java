import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HTTPClientApi
{
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/octocat"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());

            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println("\nParsed JSON:");
            System.out.println("Login: " + jsonObject.get("login").getAsString());
            System.out.println("Name: " + jsonObject.get("name").getAsString());
            System.out.println("Public Repos: " + jsonObject.get("public_repos").getAsInt());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
