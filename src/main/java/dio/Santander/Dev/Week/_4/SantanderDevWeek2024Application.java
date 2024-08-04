package dio.Santander.Dev.Week._4;

import dio.Santander.Dev.Week._4.application.AskChampionUseCase;
import dio.Santander.Dev.Week._4.application.ListChampionsUseCase;
import dio.Santander.Dev.Week._4.domain.ports.ChampionsRepository;
import dio.Santander.Dev.Week._4.domain.ports.GenerativeAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class SantanderDevWeek2024Application {

	public static void main(String[] args) {
		SpringApplication.run(SantanderDevWeek2024Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository championsRepository) {
		return new ListChampionsUseCase(championsRepository);
	}

	@Bean
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository championsRepository, GenerativeAiService genService) {
		return new AskChampionUseCase(championsRepository, genService);
	}
}