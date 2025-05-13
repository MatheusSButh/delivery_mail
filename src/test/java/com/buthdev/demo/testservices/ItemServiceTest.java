package com.buthdev.demo.testservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buthdev.demo.dtos.ItemDTO;
import com.buthdev.demo.model.Item;
import com.buthdev.demo.repositories.ItemRepository;
import com.buthdev.demo.services.ItemService;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    
    @InjectMocks
    private ItemService itemService;
    
    @Captor
    private ArgumentCaptor<Item> itemArgumentCaptor;

    @Test
    void shouldCreateItem() {

    	//Arrange
        ItemDTO itemDto = new ItemDTO("Produto teste", 50.00);

        Item item = new Item();
        item.setId(1L);
        item.setName(itemDto.name());
        item.setValue(itemDto.value());
        
        when(itemRepository.save(itemArgumentCaptor.capture())).thenReturn(item);

        //Act
        Item result = itemService.createItem(itemDto);
        
        //Assets
        Item capturedItem = itemArgumentCaptor.getValue();
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Produto teste", result.getName());
        assertEquals(50.00, result.getValue());

        assertEquals(result.getName(), capturedItem.getName());
        assertEquals(result.getValue(), capturedItem.getValue());
    }
    
   
    @Test
    void shouldThrowException() {
    	
    	//Arrange
        ItemDTO itemDto = new ItemDTO("Produto teste", 50.00);
        when(itemRepository.save(any(Item.class))).thenThrow(new RuntimeException());
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> itemService.createItem(itemDto));
    }
    
    @Test
    void shouldThrowExceptionWhenNameIsNull() {
    	
    	//Arrange
    	ItemDTO itemDto = new ItemDTO(null, 50.00);
    	when(itemRepository.save(any(Item.class))).thenThrow(new IllegalArgumentException());
    	
    	// Act & Assert
    	assertThrows(IllegalArgumentException.class, () -> itemService.createItem(itemDto));
    }
}