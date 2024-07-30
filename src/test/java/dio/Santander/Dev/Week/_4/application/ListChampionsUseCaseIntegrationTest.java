package dio.Santander.Dev.Week._4.application;


import dio.Santander.Dev.Week._4.domain.model.Champion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.util.List;

@SpringBootTest
public class ListChampionsUseCaseIntegrationTest {

    @Autowired
    private ListChampionsUseCase listChampionsUseCase;

@Test
    public void  setListChampionsUseCase (){
        List<Champion> champions = listChampionsUseCase.findAll();

    Assertions.assertEquals(12, champions.size());

    }
}
