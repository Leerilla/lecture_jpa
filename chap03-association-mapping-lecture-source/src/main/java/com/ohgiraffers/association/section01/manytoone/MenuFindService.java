package com.ohgiraffers.association.section01.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuFindService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuFindService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findMenuByMenuCode(int menuCode) {

        Menu foundMenu = menuRepository.findMenuByMenuCode(menuCode);

        return foundMenu;
    }
}
