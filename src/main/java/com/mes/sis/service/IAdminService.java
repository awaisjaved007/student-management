package com.mes.sis.service;

import com.mes.sis.vo.request.ClassVO;

public interface IAdminService {

  void addClass(ClassVO classVO);

  void removeClass(final Long classId);
}
