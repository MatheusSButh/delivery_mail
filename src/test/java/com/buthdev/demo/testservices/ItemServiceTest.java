package com.buthdev.demo.testservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buthdev.demo.dtos.ItemDTO;
import com.buthdev.demo.model.Item;
import com.buthdev.demo.repositories.ItemRepository;
import com.buthdev.demo.services.ItemService;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void createItem() {

        ItemDTO itemDto = new ItemDTO("Produto teste", 50.00);

        Item item = new Item();
        item.setId(1L);
        item.setName(itemDto.name());
        item.setValue(itemDto.value());

        when(itemRepository.save(any(Item.class))).thenReturn(item);

        Item result = itemService.createItem(itemDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Produto teste", result.getName());
        assertEquals(50.00, result.getValue());
    }
}
