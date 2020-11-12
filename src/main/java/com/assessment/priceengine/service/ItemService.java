package com.assessment.priceengine.service;

import com.assessment.priceengine.dto.ItemDTO;
import com.assessment.priceengine.dto.OrderAmountDTO;
import com.assessment.priceengine.dto.OrderDTO;
import com.assessment.priceengine.entity.Item;
import com.assessment.priceengine.enumeration.PriceRules;
import com.assessment.priceengine.repositotry.ItemRepository;
import com.assessment.priceengine.util.EntityDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<ItemDTO> getAllItems(){
        List<Item> itemList =itemRepository.findAll();
        return EntityDTOMapper.mapList(itemList,ItemDTO.class);
    }

    public ItemDTO getPriceList(int id){
        HashMap<Integer,Double> priceMap = new HashMap<>();

        Item item = itemRepository.getItemByItemId(id);
        ItemDTO itemDTO = EntityDTOMapper.map(item, ItemDTO.class);

        for(int qty = 1; qty <= 50 ; qty++){
            double price = this.priceEngine(qty, itemDTO.getUnitsPerCarton(), itemDTO.getPricePerCarton());
            priceMap.put(qty,price);
        }
        itemDTO.setUnitPrices(priceMap);
        return itemDTO;
    }

    public OrderAmountDTO getPriceForItem(OrderDTO order){
        int id = order.getItemId();
        boolean isUnits = order.isUnit();
        int qty =order.getQty();

        Item item = itemRepository.getItemByItemId(id);
        ItemDTO itemDTO = EntityDTOMapper.map(item, ItemDTO.class);

        double price = 0.0;
        if(isUnits){
            price = this.priceEngine(qty,itemDTO.getUnitsPerCarton(),itemDTO.getPricePerCarton());
        }else{
            price = this.priceEngine(qty*itemDTO.getUnitsPerCarton(),itemDTO.getUnitsPerCarton(),itemDTO.getPricePerCarton());
        }
        return new OrderAmountDTO(id,Math.round(price*100)/100.0);
    }

    private double priceEngine(int qty, int unitsPerCarton, double pricePerCarton){
        double price = 0;
        boolean isDiscount = false;
        if(qty/unitsPerCarton >= 3){
            isDiscount = true;
        }
        if(isDiscount){
            pricePerCarton = pricePerCarton*0.9;
        }
        if(qty%unitsPerCarton == 0){
            price = (qty/unitsPerCarton)*pricePerCarton;
        }else{
            double cartonPrice = (qty/unitsPerCarton)*pricePerCarton;
            double newCartonPrice = (pricePerCarton*1.3);
            double pricePerUnit = newCartonPrice/unitsPerCarton;
            double unitPrice = (qty%unitsPerCarton)*pricePerUnit;
            price = cartonPrice + unitPrice;
        }
        return price;
    }
}
