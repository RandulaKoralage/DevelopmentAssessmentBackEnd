package com.assessment.priceengine.controller;

import com.assessment.priceengine.dto.ItemDTO;
import com.assessment.priceengine.dto.OrderAmountDTO;
import com.assessment.priceengine.dto.OrderDTO;
import com.assessment.priceengine.service.ItemService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Test
    public void getAllItems() throws Exception {
        List<ItemDTO> expectedItemList = new ArrayList<>();
        expectedItemList.add(new ItemDTO(1, "Penguin-ears", 20, 175.00,
                null));
        expectedItemList.add(new ItemDTO(2, "Horseshoe", 5, 825.00,
                null));

        given(itemService.getAllItems()).willReturn(expectedItemList);
        mockMvc.perform(MockMvcRequestBuilders.get("/items"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.[0].itemId").value(1))
                .andExpect(jsonPath("$.[1].itemId").value(2));
    }

    @Test
    public void getPriceList() throws Exception {
        int id = 1;
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

        given(itemService.getPriceList(id)).willReturn(itemDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/price_list").queryParam("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.itemId").value(1));
    }

    @Test
    public void getPriceForItemTest() throws Exception {
        OrderDTO order1 = new OrderDTO(1, true, 5);

        OrderAmountDTO orderAmount1 = new OrderAmountDTO(1, 56.88);

        String jsonReq = "{\"itemId\":1,\"isUnit\":true,\"qty\":5" +
                "}";

        given(itemService.getPriceForItem(order1)).willReturn(orderAmount1);
        mockMvc.perform(MockMvcRequestBuilders.post("/price")
                .content(jsonReq).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
