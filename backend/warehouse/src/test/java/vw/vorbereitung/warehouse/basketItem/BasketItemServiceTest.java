package vw.vorbereitung.warehouse.basketItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vw.vorbereitung.warehouse.basketItem.model.BasketItem;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;
import vw.vorbereitung.warehouse.sequencegenerator.SequenceGeneratorService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketItemServiceTest {

  @Mock private BasketItemModelMapper mapper;

  @Mock private BasketItemRepository repository;

  @Mock private SequenceGeneratorService sequenceGeneratorService;

  private BasketItemService service;

  private BasketItemDocument document;

  private BasketItem pojo;

  private UUID randomNumberForTest;

  @BeforeEach
  private void Setup() {
    this.service =
        new BasketItemService(this.repository, this.sequenceGeneratorService, this.mapper);
    this.randomNumberForTest = UUID.randomUUID();
    this.document =
        BasketItemDocument.builder()
            .id(2L)
            .itemNumber(this.randomNumberForTest)
            .manufacturer("test")
            .productName("test")
            .purchasingPrice(1.0)
            .sellingPrice(1.0)
            .amount(1)
            .build();
    this.pojo =
        BasketItem.builder()
            .itemNumber(this.randomNumberForTest)
            .manufacturer("test")
            .productName("test")
            .purchasingPrice(1.0)
            .sellingPrice(1.0)
            .amount(1)
            .build();
  }

  @Test
  @DisplayName(" Get all basketItem existing  | basketItemService -> Test")
  void getAll_existing_test() {
    // Arrange
    List<BasketItemDocument> documentList = Collections.singletonList(this.document);
    List<BasketItem> pojoList = Collections.singletonList(this.pojo);

    when(this.repository.findAll()).thenReturn(documentList);
    when(this.mapper.convertDocumentToPojo(this.document)).thenReturn(this.pojo);

    // Act
    List<BasketItem> pojoListActual = this.service.getAll();

    // Assert
    assertEquals(pojoList, pojoListActual);
  }

  @Test
  @DisplayName(" Get all basketItem without existing items | basketItemService -> Test")
  void getAll_without_existing_test() {
    // Arrange
    List<BasketItemDocument> documentList = new ArrayList<>();
    List<BasketItem> pojoList = new ArrayList<>();

    when(this.repository.findAll()).thenReturn(documentList);

    // Act
    List<BasketItem> pojoListActual = this.service.getAll();

    // Assert
    assertTrue(pojoListActual.isEmpty());
  }

  @Test
  @DisplayName(" Get basketItem existing  | basketItemService -> Test")
  void getBasketItem_existing_test() {
    // Arrange
    when(this.repository.findByItemNumber(any(UUID.class)))
        .thenReturn(Optional.ofNullable(this.document));
    when(this.mapper.convertDocumentToPojo(this.document)).thenReturn(this.pojo);

    // Act
    BasketItem pojoActual = this.service.getBasketItem(this.randomNumberForTest);

    // Assert
    assertEquals(this.pojo, pojoActual);
  }

  @Test
  @DisplayName(" Get basketItem without existing  | basketItemService -> Test")
  void getBasketItem_without_existing_test() {
    // Arrange
    when(this.repository.findByItemNumber(any(UUID.class))).thenReturn(Optional.empty());

    // Assert
    assertThrows(
        BasketItemItemNumberNotFoundException.class,
        () -> this.service.getBasketItem(this.randomNumberForTest));
  }

  @Test
  @DisplayName(" test deleteItem existing test | basketItemService -> Test")
  void deleteItem_existing_test() {
    // Arrange
    when(this.repository.findByItemNumber(any(UUID.class)))
        .thenReturn(Optional.ofNullable(this.document));

    // Act
    boolean actual = this.service.deleteItem(this.randomNumberForTest);

    // Assert
    assertTrue(actual);
  }

  @Test
  @DisplayName(" test deleteItem without existing test | basketItemService -> Test")
  void deleteItem_without_existing_test() {
    // Arrange
    when(this.repository.findByItemNumber(any(UUID.class))).thenReturn(Optional.empty());

    // Act
    boolean actual = this.service.deleteItem(this.randomNumberForTest);

    // Assert
    assertFalse(actual);
  }

  @Test
  @DisplayName(" test saveItem existing test | basketItemService -> Test")
  void saveItem_test() {
    // Arrange
    when(this.repository.save(any(BasketItemDocument.class))).thenReturn(this.document);
    when(this.sequenceGeneratorService.generateSequence(BasketItemDocument.SEQUENCE_NAME))
        .thenReturn(1L);
    when(this.mapper.convertPojoToDocument(this.pojo)).thenReturn(this.document);
    when(this.mapper.convertDocumentToPojo(this.document)).thenReturn(this.pojo);

    // Act
    BasketItem pojoActual = this.service.saveItem(this.pojo);

    // Assert
    assertEquals(pojoActual.getItemNumber(), this.pojo.getItemNumber());
  }

  @Test
  @DisplayName(" test updateItem existing test | basketItemService -> Test")
  void updateItem_test() {
    // Arrange
    when(this.repository.save(any(BasketItemDocument.class))).thenReturn(this.document);
    when(this.mapper.convertPojoToDocument(this.pojo)).thenReturn(this.document);
    when(this.mapper.convertDocumentToPojo(this.document)).thenReturn(this.pojo);
    when(this.repository.findByItemNumber(any(UUID.class)))
        .thenReturn(Optional.ofNullable(this.document));

    // Act
    BasketItem pojoActual = this.service.updateItem(this.pojo);

    // Assert
    assertEquals(pojoActual.getItemNumber(), this.pojo.getItemNumber());
  }

  @Test
  @DisplayName(" test updateItem notFound existing test | basketItemService -> Test")
  void updateItem_not_found_test() {
    // Arrange
    when(this.mapper.convertPojoToDocument(this.pojo)).thenReturn(this.document);
    when(this.repository.findByItemNumber(any(UUID.class))).thenReturn(Optional.empty());

    // Assert
    assertThrows(
        BasketItemItemNumberNotFoundException.class, () -> this.service.updateItem(this.pojo));
  }
}
