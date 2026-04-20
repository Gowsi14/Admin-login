package com.example.demo.rest;

import com.example.demo.dto.InventoryResponse;
import com.example.demo.dto.InventorySummaryResponse;
import com.example.demo.entity.Inventory;
import com.example.demo.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/summary")
    public ResponseEntity<InventorySummaryResponse> getSummary() {
        return ResponseEntity.ok(inventoryService.getSummary());
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> create(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.create(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> update(@PathVariable Long id,
                                                     @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.update(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}