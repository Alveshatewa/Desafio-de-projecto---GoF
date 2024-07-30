package dio.Santander.Dev.Week._4.application;

import dio.Santander.Dev.Week._4.domain.model.Champion;
import dio.Santander.Dev.Week._4.domain.ports.ChampionsRepository;

import java.util.List;

public record ListChampionsUseCase(ChampionsRepository repository) {

   public  List<Champion> findAll(){
       return  repository.findAll();
   }
}
