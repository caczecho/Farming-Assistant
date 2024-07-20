package com.farmingassistant.model;

public class ItemRequirement {
    private final int itemId;
    private final int quantity;

    public ItemRequirement(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
