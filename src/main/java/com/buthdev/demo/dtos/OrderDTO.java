package com.buthdev.demo.dtos;

import java.util.Set;

public record OrderDTO(Set<Long> items, Long id) {

}
