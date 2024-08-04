package dio.Santander.Dev.Week._4.adapters.out;

import dio.Santander.Dev.Week._4.domain.ports.GenerativeAiService;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI", matchIfMissing = true)
@FeignClient(name = "GeminiAiConversationContext", url = "${gemini.base-url}", configuration = GeminiAIConversationContext.Configuration.class)
public interface GeminiAIConversationContext extends GenerativeAiService {

    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    openAiChatCompletionResponse textOnlyInput(openAiChatCompletionRequest req);

    @Override
    default String generativeContext(String objective, String context){

        String prompt = """
                 %s
                 %s
                """.formatted(objective, context);

         openAiChatCompletionRequest req= new openAiChatCompletionRequest (
                List.of(new Content(List.of(new Parts(prompt))))
        );
         try {
             openAiChatCompletionResponse response = textOnlyInput(req);
             return  response.candidates().getFirst().content().parts().getFirst().text();
         }catch (FeignException httpErrors){
             return "Error while communicate with Gemini API";
         } catch (Exception unexpecteError){
             return "Error unexpected exception found while communicate with Gemini API";
         }


    }

    // Mapeando os Jsons para consumo da APi do Gemini

    record openAiChatCompletionRequest (List<Content> contents){ }
    record Content(List<Parts> parts){ }
    record Parts(String text){ }
    record openAiChatCompletionResponse (List<Candidates> candidates){ }
    record Candidates(Content content){ }



    class Configuration{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey){

            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }

}
