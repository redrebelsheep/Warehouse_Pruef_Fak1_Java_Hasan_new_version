package vw.vorbereitung.warehouse.basketItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasketItemController.class)
public class BasketItemControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BasketItemService service;

  @Autowired private ObjectMapper mapper;

  private BasketItem pojo;

  private String jsonObject;

  private String restLink;

  private UUID itemNumber;

  @BeforeEach()
  void setUp() throws JsonProcessingException {
    this.pojo =
        BasketItem.builder()
            .productName("testName")
            .purchasingPrice(1.0)
            .sellingPrice(2.0)
            .manufacturer("manfucator")
            .amount(1)
            .build();
    this.jsonObject = this.mapper.writeValueAsString(this.pojo);
    this.restLink = "/api/item/";
    this.itemNumber = UUID.randomUUID();
  }

  @Test
  @DisplayName(" Get all pojo | Status: 200 | BasketItemController -> Test")
  void getAll_return_all_status_200_test() throws Exception {

    // Arrange
    List<BasketItem> pojoList = new ArrayList<>();
    pojoList.add(this.pojo);
    this.jsonObject = this.mapper.writeValueAsString(pojoList);

    when(this.service.getAll()).thenReturn(pojoList);
    this.mockMvc
        .perform(get(this.restLink))
        // Assert
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonObject));
  }

  /**
   * Gets all return all status 204 wrong name year no entry test.
   *
   * @throws Exception the exception
   */
  @Test
  @DisplayName("Get no pojo if not exists | Status: 204 | BasketItemController -> Test")
  void getAll_return_all_status_204_test() throws Exception {
    // Arrange

    List<BasketItem> pojoList = new ArrayList<>();
    this.jsonObject = this.mapper.writeValueAsString(pojoList);

    when(this.service.getAll()).thenReturn(pojoList);

    // Act
    this.mockMvc
        .perform(get(this.restLink))
        // Assert
        .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName(" Get one pojo with right identifier | Status: 200 | BasketItemController -> Test")
  void getAll_return_one_status_200_test() throws Exception {

    // Arrange
    this.jsonObject = this.mapper.writeValueAsString(this.pojo);

    when(this.service.getBasketItem(any(UUID.class))).thenReturn(this.pojo);
    this.mockMvc
        .perform(get(this.restLink + "/109cfd88-f912-11ec-b939-0242ac12000"))
        // Assert
        .andExpect(status().isOk())
        .andExpect(content().json(this.jsonObject));
  }

  @Test
  @DisplayName(" Get one pojo with wrong identifier | Status: 404 | BasketItemController -> Test")
  void getAll_return_one_status_404_test() throws Exception {

    // Arrange
    this.jsonObject = this.mapper.writeValueAsString(this.pojo);

    when(this.service.getBasketItem(any(UUID.class)))
        .thenThrow(
            new BasketItemItemNumberNotFoundException(this.itemNumber, HttpStatus.NOT_FOUND));

    this.mockMvc
        .perform(get(this.restLink + "/109cfd88-f912-11ec-b939-0242ac12000"))
        // Assert
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName(" save right basketItem  | Status: 201 | BasketItemController -> Test")
  void postBasketItem_right_basketItem_return_basketItem_Status_201_test() throws Exception {

    // Arrange
    this.pojo.setItemNumber(this.itemNumber);

    String json = this.mapper.writeValueAsString(this.pojo);

    when(this.service.saveItem(any(BasketItem.class))).thenReturn(this.pojo);

    // Act
    this.mockMvc
        .perform(post(this.restLink).contentType(MediaType.APPLICATION_JSON).content(json))
        // Assert
        .andExpect(status().isCreated())
        .andExpect(content().json(json));
  }

  @Test
  @DisplayName(" update right basketItem  | Status: 201 | basketItemController -> Test")
  void putbasketItem_right_basketItem_return_basketItem_Status_201_test() throws Exception {

    // Arrange
    this.pojo.setItemNumber(this.itemNumber);
    String json = this.mapper.writeValueAsString(this.pojo);

    when(this.service.updateItem(any(BasketItem.class))).thenReturn(this.pojo);

    // Act
    this.mockMvc
        .perform(put(this.restLink).contentType(MediaType.APPLICATION_JSON).content(json))
        // Assert
        .andExpect(status().isCreated())
        .andExpect(content().json(json));
  }

  @Test
  @DisplayName(" update invalid basketItem  | Status: 4004 | basketItemController -> Test")
  void putBasketItem_invalid_basketItem_return_Status_4004_test() throws Exception {

    // Arrange
    this.pojo.setItemNumber(this.itemNumber);
    this.pojo.setManufacturer(null);
    String json = this.mapper.writeValueAsString(this.pojo);

    when(this.service.updateItem(any(BasketItem.class))).thenReturn(this.pojo);

    // Act
    this.mockMvc
        .perform(put(this.restLink).contentType(MediaType.APPLICATION_JSON).content(json))
        // Assert
        .andExpect(status().isBadRequest())
        .andExpect(
            result ->
                assertTrue(
                    result.getResolvedException() instanceof MethodArgumentNotValidException));
  }

  @Test
  @DisplayName("delete basketItem with right item | Status: 200 | BasketItemController -> Test")
  void deletedBasketItem_right_basketItem_return_basketItem_Status_200_test() throws Exception {

    // Arrange
    String json = this.mapper.writeValueAsString(this.pojo);

    when(this.service.deletedItem(any(UUID.class))).thenReturn(true);

    // Act
    this.mockMvc
        .perform(delete(this.restLink + "/109cfd88-f912-11ec-b939-0242ac12000"))
        // Assert
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("delete basketItem with wrong item | Status: 404 | BasketItemController -> Test")
  void deletedBasketItem_wrong_basketItem_return_basketItem_Status_204_test() throws Exception {

    // Arrange
    String json = this.mapper.writeValueAsString(this.pojo);

    when(this.service.deletedItem(any(UUID.class))).thenReturn(false);

    // Act
    this.mockMvc
        .perform(delete(this.restLink + "/109cfd88-f912-11ec-b939-0242ac12002"))
        // Assert
        .andExpect(status().isNotFound());
  }
}
