package com.buthdev.demo.model;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double totalValue;
	private OffsetDateTime orderDate;
	private OffsetDateTime estimatedDelivery;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "order_items",
		    joinColumns = @JoinColumn(name = "order_id"),
		    inverseJoinColumns = @JoinColumn(name = "item_id")
		)
	private List<Item> items;
	
	@ManyToOne
	private User userReceiver;
	
	@ManyToOne
	private User userSender;
}
