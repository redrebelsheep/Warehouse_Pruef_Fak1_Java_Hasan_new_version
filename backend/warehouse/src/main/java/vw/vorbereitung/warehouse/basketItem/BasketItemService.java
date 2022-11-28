package vw.vorbereitung.warehouse.basketItem;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import vw.vorbereitung.warehouse.basketItem.model.BasketItemDocument;
import vw.vorbereitung.warehouse.sequencegenerator.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.List;

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



}
