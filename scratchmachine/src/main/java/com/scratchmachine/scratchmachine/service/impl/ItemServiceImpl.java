package com.scratchmachine.scratchmachine.service.impl;

import com.scratchmachine.scratchmachine.entity.Items;
import com.scratchmachine.scratchmachine.entity.Scratchcards;
import com.scratchmachine.scratchmachine.repository.ItemRepository;
import com.scratchmachine.scratchmachine.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void loadItemData() {

        List<Items> itemsList = new ArrayList<>();
        itemsList.add(new Items("onion", BigDecimal.valueOf(30), true));
        itemsList.add(new Items("capcicum", BigDecimal.valueOf(30), true));
        itemsList.add(new Items("tomato", BigDecimal.valueOf(30), true));
        itemsList.add(new Items("potato", BigDecimal.valueOf(30), true));
        itemsList.add(new Items("cucumner", BigDecimal.valueOf(30), true));

        itemRepository.saveAll(itemsList);
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
