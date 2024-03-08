package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "inventory_orders")
public class InventoryOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    float item_id;
    float units_received;
    List<Item> items;

    public float getItem_id() {
        return item_id;
    }

    public void setItem_id(float item_id) {
        this.item_id = item_id;
    }

    public float getUnits_received() {
        return units_received;
    }

    public void setUnits_received(float units_received) {
        this.units_received = units_received;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
