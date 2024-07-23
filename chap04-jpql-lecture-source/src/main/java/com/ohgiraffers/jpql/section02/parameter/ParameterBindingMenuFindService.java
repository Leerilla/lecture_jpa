package com.ohgiraffers.jpql.section02.parameter;

import org.springframework.stereotype.Service;

@Service
public class ParameterBindingMenuFindService {

    private ParameterBindingMenuRepository parametgerBindingMenuRepository;

    public ParameterBindingMenuFindService(ParameterBindingMenuRepository parametgerBindingMenuRepository) {
        this.parametgerBindingMenuRepository = parametgerBindingMenuRepository;
    }
    public Menu bindParameterWithName(int menuCode) {

        return parametgerBindingMenuRepository.bindParameterWithName(menuCode);
    }

    public Menu bindParameterWithOrder(int menuCode) {

        return parametgerBindingMenuRepository.bindParameterWithOrder(menuCode);
    }
}
