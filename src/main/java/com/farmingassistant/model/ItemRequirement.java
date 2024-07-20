package com.farmingassistant.model;

import lombok.Getter;

/**
 * Represents an item requirement with an item ID and quantity.
 */
@Getter
public class ItemRequirement {
    private final int itemId;
    private final int quantity;

    /**
     * Constructs an ItemRequirement with the specified item ID and quantity.
     *
     * @param itemId   the ID of the item
     * @param quantity the quantity of the item required
     */
    public ItemRequirement(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

}
