package com.buthdev.demo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buthdev.demo.dtos.ItemDTO;
import com.buthdev.demo.exceptions.NotFoundException;
import com.buthdev.demo.model.Item;
import com.buthdev.demo.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	public Item findById(Long id) {
		return itemRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
	}
	
	public Item createItem(ItemDTO itemDto) {
		Item item = new Item();
		convertToItem(itemDto, item);
		
		return itemRepository.save(item);
	}

	public Item updateItem(ItemDTO itemDto, Long id) {
		Item item = findById(id);
		convertToItem(itemDto, item);
		return itemRepository.save(item);
	}
	
	public void deleteItem(Long id) {
		findById(id);
		itemRepository.deleteById(id);
	}
	
	public List<Item> findAllById(Iterable<Long> id) {
		return itemRepository.findAllById(id);
	}
	
	
	private Item convertToItem(ItemDTO itemDto, Item item) {
		BeanUtils.copyProperties(itemDto, item);
		return item;
	}
}