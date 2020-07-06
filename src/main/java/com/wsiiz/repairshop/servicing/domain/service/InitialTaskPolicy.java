package com.wsiiz.repairshop.servicing.domain.service;

import java.util.List;

public interface InitialTaskPolicy {

  List<Task> generateTasks(Service service);
}
