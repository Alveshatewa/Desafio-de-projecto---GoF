package dio.Santander.Dev.Week._4.domain.ports;

import dio.Santander.Dev.Week._4.domain.model.Champion;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {
    /*Encontra todos os champions*/
    List<Champion> findAll();

    /*Encontra apenas um champion pelo id informado pelo usuario*/
    Optional<Champion> findOneById(Long id);

}
