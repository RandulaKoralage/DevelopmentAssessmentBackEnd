package com.assessment.priceengine.repository;

import com.assessment.priceengine.entity.Item;
import com.assessment.priceengine.repositotry.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {


    @Mock
    ItemRepository itemRepository;

    @Test
    public void getAllItemsTest(){
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item(1, "Penguin-ears", 20, 175.00));
        expectedItemList.add(new Item(2, "Horseshoe", 5, 825.00));

        Mockito.when(itemRepository.findAll()).thenReturn(expectedItemList);

        List<Item> actualItemList = itemRepository.findAll();

        assertArrayEquals(expectedItemList.toArray(), actualItemList.toArray(),"Incorrect Item List");
    }

    @Test
    public void getItemByItemIdTest(){

        Item item1 = new Item(1, "Penguin-ears", 20, 175.00);
        Item item2 =new Item(2, "Horseshoe", 5, 825.00);

        Mockito.when(itemRepository.getItemByItemId(1)).thenReturn(item1);
        Mockito.when(itemRepository.getItemByItemId(2)).thenReturn(item2);

       Item actualItem1 = itemRepository.getItemByItemId(1);
       Item actualItem2 = itemRepository.getItemByItemId(2);

        assertEquals(item1,actualItem1,"Incorrect Item");
        assertEquals(item2,actualItem2,"Incorrect Item");
    }
}
