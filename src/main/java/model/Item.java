package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lookup_item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    float item_nbr;
    String class_desc;
    String item_desc;
    InventoryOrders inventoryOrders;

    public float getItem_nbr() {
        return item_nbr;
    }

    public void setItem_nbr(float item_nbr) {
        this.item_nbr = item_nbr;
    }

    public String getClass_desc() {
        return class_desc;
    }

    public void setClass_desc(String class_desc) {
        this.class_desc = class_desc;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public InventoryOrders getInventoryOrders() {
        return inventoryOrders;
    }

    public void setInventoryOrders(InventoryOrders inventoryOrders) {
        this.inventoryOrders = inventoryOrders;
    }
}
