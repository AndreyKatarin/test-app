package av.katarin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.config.HypermediaWebTestClientConfigurer;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.hateoas.server.core.TypeReferences;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

@SpringBootTest
@AutoConfigureWebTestClient
public class ClientAppWebTestClient {


    @Test
    void exampleTest(@Autowired WebTestClient.Builder builder, @Autowired HypermediaWebTestClientConfigurer configurer) {
        WebTestClient client = builder.apply(configurer).build();

        client.get().uri("http://localhost:8080/documents/1") //
                .exchange() //
                .expectBody(new TypeReferences.EntityModelType<Document>() {})
                .consumeWith(result -> {
                    // assert against this EntityModel<Employee>!
                    Assertions.assertNotNull(result.getResponseBody().getContent());
                });
    }

    @Test
    void exampleRestTest() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new Jackson2HalModule(), new JavaTimeModule())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(
                new MappingJackson2HttpMessageConverter(objectMapper))
        );

        ResponseEntity<CollectionModel<EntityModel<Document>>> model = restTemplate.exchange(URI.create("http://localhost:8080/documents/"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CollectionModel<EntityModel<Document>>>() {
                });
        Collection<EntityModel<Document>> documents = model.getBody().getContent();
    }

    @Test
    void traversonTest() {
        FXWebClient fxWebClient = new FXWebClient(new Traverson(URI.create("http://localhost:8080/"), MediaTypes.HAL_JSON));
        Assertions.assertFalse(fxWebClient.getDocuments().isEmpty());
    }
}
