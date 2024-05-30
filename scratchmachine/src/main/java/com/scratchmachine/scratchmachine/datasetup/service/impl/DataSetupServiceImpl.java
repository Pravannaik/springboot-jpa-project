package com.scratchmachine.scratchmachine.datasetup.service.impl;

import com.scratchmachine.scratchmachine.datasetup.service.DataSetupService;
import com.scratchmachine.scratchmachine.repository.ItemRepository;
import com.scratchmachine.scratchmachine.service.ItemService;
import com.scratchmachine.scratchmachine.service.ScratchCardService;
import com.scratchmachine.scratchmachine.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataSetupServiceImpl implements DataSetupService {

    @Autowired
    private UserService userService;

    @Autowired
    private ScratchCardService scratchCardService;

    @Autowired
    private ItemService itemService;

    @Override
    public void deleteAllDataTable() {
        userService.deleteAll();
        scratchCardService.deleteAll();
        itemService.deleteAll();
    }

    @Override
    public void loadAllDataTable() {
        userService.loadUserData();
        scratchCardService.loadScratchCardData();
        itemService.loadItemData();
    }
}
