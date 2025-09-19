package edu.escuelaing.tdse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.escuelaing.tdse.model.dtos.InventoryCreateRequest;
import edu.escuelaing.tdse.model.dtos.InventoryResponse;
import edu.escuelaing.tdse.model.dtos.InventorySearchParams;
import edu.escuelaing.tdse.model.dtos.InventoryUpdateRequest;

public class InventoryServiceImpl implements InventoryService {

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryResponse createItem(InventoryCreateRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'createItem'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InventoryResponse> getAllItems(Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllItems'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InventoryResponse> getFilteredItems(InventorySearchParams params,
            org.springframework.data.domain.Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'getFilteredItems'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryResponse updateItem(Long id, InventoryUpdateRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteItem(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteItem'");
    }

}