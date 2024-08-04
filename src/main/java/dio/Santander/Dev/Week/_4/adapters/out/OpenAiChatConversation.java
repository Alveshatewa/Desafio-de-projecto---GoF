package dio.Santander.Dev.Week._4.adapters.out;

import dio.Santander.Dev.Week._4.domain.ports.GenerativeAiService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "OPENAI")
@FeignClient(name = "OpenAiChatConversation", url = "${openai.base-url}", configuration = OpenAiChatConversation.Configuration.class)
public interface OpenAiChatConversation extends GenerativeAiService {

    @PostMapping("/v1/chat/completions")
    AiCompletionResponse chatCompletion(AiCompletionRequest request);

    @Override
    default String generativeContext(String objective, String context){

        // Instanciando o recorde de request como um novo request para poder tratar os dados recebidos do record
        String model = "gpt-3.5-turbo";
        List<Messages> messages =  List.of(
                new Messages("system", objective),
                new Messages("user", context)
        );
        AiCompletionRequest request = new AiCompletionRequest(model, messages);

        AiCompletionResponse response = chatCompletion(request);

        return response.choices().getFirst().message().content();
    }

    /*Mapeando os Jsons por meio dd records para consumo de api da openai*/

    record AiCompletionRequest(String model, List<Messages> message){ }
    record Messages (String role, String content){ }

    record AiCompletionResponse(List<Choices> choices){ }
    record Choices (Messages message) { }

    class Configuration{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey){

            return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(apiKey) );
        }

    }
}
