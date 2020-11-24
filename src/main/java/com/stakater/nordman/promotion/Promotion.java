package com.stakater.nordman.promotion;

import java.util.*;

import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.*;
import io.quarkus.panache.common.*;

@Entity
public class Promotion extends PanacheEntity {
    public String code;
    public String itemId;
    public double percentOff;
    public String description;
    public boolean active;

    public static List<Promotion> listByItemIds(String itemId) {
        if (itemId != null) {
            return Promotion.list("itemId in :itemId and active = true", Parameters.with("itemId", itemId));
        } else {
            return new ArrayList<Promotion>();
        }

    }

    public static Map<String, Promotion> listActive() {
        List<Promotion> active = Promotion.list("active = true");
        Map<String, Promotion> activePromotions = new HashMap<String, Promotion>();
        if (active != null && !active.isEmpty()) {
            for (Promotion promotion : active) {
                activePromotions.put(promotion.itemId, promotion);
            }
        }
        return activePromotions;
    }
}