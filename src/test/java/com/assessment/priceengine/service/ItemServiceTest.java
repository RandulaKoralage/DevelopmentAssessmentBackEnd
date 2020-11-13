package com.assessment.priceengine.service;

import com.assessment.priceengine.dto.ItemDTO;
import com.assessment.priceengine.dto.OrderAmountDTO;
import com.assessment.priceengine.dto.OrderDTO;
import com.assessment.priceengine.entity.Item;
import com.assessment.priceengine.repositotry.ItemRepository;
import com.assessment.priceengine.util.EntityDTOMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    public void getAllItemsTest() {
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item(1, "Penguin-ears", 20, 175.00));
        expectedItemList.add(new Item(2, "Horseshoe", 5, 825.00));

        Mockito.when(itemRepository.findAll()).thenReturn(expectedItemList);

        List<ItemDTO> expected = EntityDTOMapper.mapList(expectedItemList, ItemDTO.class);
        List<ItemDTO> actual = itemService.getAllItems();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void getPriceForItemTest() {
        Item item1 = new Item(1, "Penguin-ears", 20, 175.00);
        Item item2 = new Item(2, "Horseshoe", 5, 825.00);

        OrderDTO order1 = new OrderDTO(1, true, 5);
        OrderDTO order2 = new OrderDTO(1, false, 3);
        OrderDTO order3 = new OrderDTO(2, true, 1);
        OrderDTO order4 = new OrderDTO(2, false, 5);

        OrderAmountDTO orderAmount1 = new OrderAmountDTO(1, 56.88);
        OrderAmountDTO orderAmount2 = new OrderAmountDTO(1, 472.5);
        OrderAmountDTO orderAmount3 = new OrderAmountDTO(2, 214.5);
        OrderAmountDTO orderAmount4 = new OrderAmountDTO(2, 3712.5);

        Mockito.when(itemRepository.getItemByItemId(order1.getItemId())).thenReturn(item1);
        assertEquals(orderAmount1, itemService.getPriceForItem(order1), "Wrong calculation");

        Mockito.when(itemRepository.getItemByItemId(order2.getItemId())).thenReturn(item1);
        assertEquals(orderAmount2, itemService.getPriceForItem(order2), "Wrong calculation");

        Mockito.when(itemRepository.getItemByItemId(order3.getItemId())).thenReturn(item2);
        assertEquals(orderAmount3, itemService.getPriceForItem(order3), "Wrong calculation");

        Mockito.when(itemRepository.getItemByItemId(order4.getItemId())).thenReturn(item2);
        assertEquals(orderAmount4, itemService.getPriceForItem(order4), "Wrong calculation");

    }

    @Test
    public void getPriceList() {
        int id = 1;
        Item item1 = new Item(1, "Penguin-ears", 20, 175.00);
        Mockito.when(itemRepository.getItemByItemId(id)).thenReturn(item1);

        HashMap<Integer, Double> priceMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "\"1\": 11.375,\n" +
                "\"2\": 22.75,\n" +
                "\"3\": 34.125,\n" +
                "\"4\": 45.5,\n" +
                "\"5\": 56.875,\n" +
                "\"6\": 68.25,\n" +
                "\"7\": 79.625,\n" +
                "\"8\": 91,\n" +
                "\"9\": 102.375,\n" +
                "\"10\": 113.75,\n" +
                "\"11\": 125.125,\n" +
                "\"12\": 136.5,\n" +
                "\"13\": 147.875,\n" +
                "\"14\": 159.25,\n" +
                "\"15\": 170.625,\n" +
                "\"16\": 182,\n" +
                "\"17\": 193.375,\n" +
                "\"18\": 204.75,\n" +
                "\"19\": 216.125,\n" +
                "\"20\": 175,\n" +
                "\"21\": 186.375,\n" +
                "\"22\": 197.75,\n" +
                "\"23\": 209.125,\n" +
                "\"24\": 220.5,\n" +
                "\"25\": 231.875,\n" +
                "\"26\": 243.25,\n" +
                "\"27\": 254.625,\n" +
                "\"28\": 266,\n" +
                "\"29\": 277.375,\n" +
                "\"30\": 288.75,\n" +
                "\"31\": 300.125,\n" +
                "\"32\": 311.5,\n" +
                "\"33\": 322.875,\n" +
                "\"34\": 334.25,\n" +
                "\"35\": 345.625,\n" +
                "\"36\": 357,\n" +
                "\"37\": 368.375,\n" +
                "\"38\": 379.75,\n" +
                "\"39\": 391.125,\n" +
                "\"40\": 350,\n" +
                "\"41\": 361.375,\n" +
                "\"42\": 372.75,\n" +
                "\"43\": 384.125,\n" +
                "\"44\": 395.5,\n" +
                "\"45\": 406.875,\n" +
                "\"46\": 418.25,\n" +
                "\"47\": 429.625,\n" +
                "\"48\": 441,\n" +
                "\"49\": 452.375,\n" +
                "\"50\": 463.75\n" +
                "}";
        try {
            priceMap = mapper.readValue(json, new TypeReference<HashMap<Integer, Double>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        ItemDTO itemDTO = new ItemDTO(1, "Penguin-ears", 20, 175, priceMap);
        assertEquals(itemDTO, itemService.getPriceList(id));
    }
}
