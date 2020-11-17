package com.assessment.priceengine.controller;

import com.assessment.priceengine.dto.ItemDTO;
import com.assessment.priceengine.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

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
}
