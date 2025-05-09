package com.buthdev.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buthdev.demo.dtos.ItemDTO;
import com.buthdev.demo.model.Item;
import com.buthdev.demo.services.ItemService;

@RestController
@RequestMapping(value = "items")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@GetMapping
	public ResponseEntity<List<Item>> findAll() {
		return ResponseEntity.ok().body(itemService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody ItemDTO itemDto) {
		itemService.createItem(itemDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Item> updateItem (@RequestBody ItemDTO itemDto, @PathVariable Long id) {
		itemService.updateItem(itemDto, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}