package com.assessment.priceengine.repositotry;

import com.assessment.priceengine.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item getItemByItemId(int id);
}
