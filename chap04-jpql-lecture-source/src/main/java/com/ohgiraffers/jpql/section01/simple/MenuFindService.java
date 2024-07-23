package com.ohgiraffers.jpql.section01.simple;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuFindService {

    private MenuRepository menuRepository;

    public MenuFindService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    public String findMenuNameByMenuCode(int menuCode) {

        return menuRepository.findMenuNameByMenuCode(menuCode);
    }

    public Object findObjectByMenuCode(int menuCode) {

        return menuRepository.findObjectByMenuCode(menuCode);
    }

    public List<Menu> findAllMenusWithTypedQuery() {

        return menuRepository.findAllMenusWithTypedQuery();
    }

    public List<Menu> findAllMenusWithQuery() {

        return menuRepository.findAllMenusWithQuery();
    }

    public List<Integer> findAllCategoryCodsInMenu() {

        return menuRepository.findAllCategoryCodesInMenu();
    }

    public List<Menu> findMenusInCategoryCodes(List<Integer> categoryCodes) {

        return menuRepository.findMenusInCategoryCodes(categoryCodes);
    }

    public List<Menu> searchMenusBySearchValue(String searchValue) {

        return menuRepository.findMenusBySearchValue(searchValue);
    }
}
