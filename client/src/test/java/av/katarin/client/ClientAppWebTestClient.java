package av.katarin.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import java.net.URI;

@SpringBootTest
@AutoConfigureWebTestClient
public class ClientAppWebTestClient {


    @Test
    void traversonTest() {
        FXWebClient fxWebClient = new FXWebClient(new Traverson(URI.create("http://localhost:8080/"), MediaTypes.HAL_JSON));
        Assertions.assertFalse(fxWebClient.getDocuments().isEmpty());
    }
}
