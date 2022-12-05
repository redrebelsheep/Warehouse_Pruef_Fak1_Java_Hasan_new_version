package vw.vorbereitung.warehouse.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vw.vorbereitung.warehouse.basketItem.BasketItemModelMapper;

@Configuration
public class GlobalMapperConfiguration {

  @Autowired public ModelMapper mapper;

  @Bean
  public BasketItemModelMapper BasketItemModelMapper() {
    return new BasketItemModelMapper(this.mapper);
  }
}
