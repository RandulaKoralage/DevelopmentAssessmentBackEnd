package com.assessment.priceengine.controller;

import com.assessment.priceengine.dto.OrderDTO;
import com.assessment.priceengine.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ItemController {
    @Autowired
    ItemService itemService = new ItemService();

    @RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @RequestMapping(value = "/pricelist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPriceList(@RequestParam int id) {
        return ResponseEntity.ok(itemService.getPriceList(id));
    }

    @RequestMapping(value = "/price", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPriceForItem(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(itemService.getPriceForItem(order));
    }
}
