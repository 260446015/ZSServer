package com.huishu.ait.service.Specialist;

import java.util.List;

import com.huishu.ait.entity.Specialist;

public interface SpecialistService {

	List<Specialist> findAll();

	Specialist getSpecialistById(String id);

}
