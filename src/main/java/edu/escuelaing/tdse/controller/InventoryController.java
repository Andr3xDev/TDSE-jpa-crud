package edu.escuelaing.tdse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.escuelaing.tdse.model.dtos.InventoryCreateRequest;
import edu.escuelaing.tdse.model.dtos.InventoryResponse;
import edu.escuelaing.tdse.model.dtos.InventorySearchParams;
import edu.escuelaing.tdse.model.dtos.InventoryUpdateRequest;
import edu.escuelaing.tdse.service.InventoryServiceImpl;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createItem(@RequestBody InventoryCreateRequest request) {
        InventoryResponse createdItem = inventoryService.createItem(request);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<InventoryResponse>> getAllItems(Pageable pageable) {
        Page<InventoryResponse> items = inventoryService.getAllItems(pageable);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InventoryResponse>> searchItems(InventorySearchParams params, Pageable pageable) {
        Page<InventoryResponse> results = inventoryService.getFilteredItems(params,
                pageable);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateItem(
            @PathVariable Long id,
            @RequestBody InventoryUpdateRequest request) {
        InventoryResponse updatedItem = inventoryService.updateItem(id, request);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

}