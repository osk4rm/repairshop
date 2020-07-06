package com.wsiiz.repairshop.enterprise.domain.branch;

import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import org.springframework.stereotype.Component;

@Component
public class BranchFactory implements AbstractFactory<Branch> {

    public Branch create(){
        return new Branch();
    }

}
