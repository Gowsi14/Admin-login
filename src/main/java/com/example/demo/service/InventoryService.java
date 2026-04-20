package com.example.demo.service;

import com.example.demo.dto.InventoryResponse;
import com.example.demo.dto.InventorySummaryResponse;
import com.example.demo.entity.Inventory;
import com.example.demo.repo.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    private InventoryResponse toResponse(Inventory i) {
        return InventoryResponse.builder()
                .id(i.getId())
                .itemName(i.getItemName())
                .category(i.getCategory())
                .quantity(i.getQuantity())
                .unit(i.getUnit())
                .price(i.getPrice())
                .status(i.getStatus())
                .build();
    }

    private String calculateStatus(int quantity) {
        if (quantity == 0) return "OUT_OF_STOCK";
        if (quantity <= 20) return "LOW_STOCK";
        return "IN_STOCK";
    }

    public InventorySummaryResponse getSummary() {
        long total = inventoryRepository.count();
        long low = inventoryRepository.countByStatus("LOW_STOCK");
        long out = inventoryRepository.countByStatus("OUT_OF_STOCK");
        return new InventorySummaryResponse(total, low, out);
    }

    public List<InventoryResponse> getAll() {
        return inventoryRepository.findAll()
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public InventoryResponse getById(Long id) {
        return toResponse(inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found")));
    }

    public InventoryResponse create(Inventory inventory) {
        inventory.setStatus(calculateStatus(inventory.getQuantity()));
        return toResponse(inventoryRepository.save(inventory));
    }

    public InventoryResponse update(Long id, Inventory updated) {
        Inventory i = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        i.setItemName(updated.getItemName());
        i.setCategory(updated.getCategory());
        i.setQuantity(updated.getQuantity());
        i.setUnit(updated.getUnit());
        i.setPrice(updated.getPrice());
        i.setStatus(calculateStatus(updated.getQuantity()));
        return toResponse(inventoryRepository.save(i));
    }

    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }
}