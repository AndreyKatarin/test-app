package av.katarin.client.config;

import av.katarin.client.FXWebClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class FXClientConfiguration {
    @Bean
    public FXWebClient fxWebClient(Traverson traverson) {
        return new FXWebClient(traverson);
    }


    @Bean
    @ConditionalOnMissingBean
    public Traverson traverson(){
        return new Traverson(URI.create("http://localhost:8080"), MediaTypes.HAL_JSON);
    }

}
