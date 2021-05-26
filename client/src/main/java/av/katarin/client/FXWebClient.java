package av.katarin.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;

import java.util.Collection;


public class FXWebClient {

    private final Traverson client;

    public FXWebClient(Traverson client) {
        this.client = client;
    }

    public Collection<Document> getDocuments(){
        return client.follow("documents")
                .toObject(new ParameterizedTypeReference<CollectionModel<Document>>() {})
                .getContent();
    }
}
