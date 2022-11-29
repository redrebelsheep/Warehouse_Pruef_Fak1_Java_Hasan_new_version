package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;
import vw.vorbereitung.warehouse.sequencegenerator.SequenceGeneratorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BasketItemService {

    private BasketItemRepository repository;

    private SequenceGeneratorService sequenceGeneratorService;

    public List<BasketItemDocument> getAll(){
        return repository.findAll();
    }

    public void save(BasketItemDocument document){
        document.setId(this.sequenceGeneratorService.generateSequence(BasketItemDocument.SEQUENCE_NAME));
        repository.save(document);
    }

    public BasketItemDocument getBasketItem(UUID itemNumber) {
        Optional<BasketItemDocument> document = repository.findByItemNumber(itemNumber);
        if(!document.isPresent()){

        }
        return document.get();
    }

    public boolean deletedItem(UUID itemNumber) {
        if(repository.findByItemNumber(itemNumber).isPresent()){
            repository.deleteByItemNumber(itemNumber);
            return true;
        }
        return false;
    }
    public Optional<BasketItemDocument> checkItemIsPresent(UUID itemNumber) {
        return repository.findByItemNumber(itemNumber);
    }

    public BasketItemDocument saveItem(BasketItemDocument item){
        return repository.save(item);
    }
}
