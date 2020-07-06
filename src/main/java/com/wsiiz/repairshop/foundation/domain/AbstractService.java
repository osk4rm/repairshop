package com.wsiiz.repairshop.foundation.domain;

public interface AbstractService<E extends BaseEntity> {

  E save(E entity);

}
