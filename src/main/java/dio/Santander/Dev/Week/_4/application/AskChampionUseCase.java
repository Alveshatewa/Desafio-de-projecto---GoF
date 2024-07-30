package dio.Santander.Dev.Week._4.application;

import dio.Santander.Dev.Week._4.domain.exceptions.ChampionNotFoundException;
import dio.Santander.Dev.Week._4.domain.model.Champion;
import dio.Santander.Dev.Week._4.domain.ports.ChampionsRepository;

public record AskChampionUseCase(ChampionsRepository repository) {

    public String askChampion(Long championId, String ask){

        Champion champion = repository.findOneById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        String context = champion.GeneratedContextByQuestion(ask);

        return context;
    }
}
