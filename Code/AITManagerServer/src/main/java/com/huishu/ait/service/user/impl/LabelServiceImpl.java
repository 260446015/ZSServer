package com.huishu.ait.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huishu.ait.entity.Label;
import com.huishu.ait.entity.common.AjaxResult;
import com.huishu.ait.repository.user.LabelRepository;
import com.huishu.ait.service.user.LabelService;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelRepository labelRepository;

	@Override
	public List<Label> getLabel(String park) {
		return labelRepository.findByPark(park);
	}

	@Override
	public List<Label> getMyLabel(Long id) {
		return labelRepository.findByUserId(id);
	}

	@Override
	public AjaxResult addLabel(Label label) {
		AjaxResult result = new AjaxResult();
		List<Label> list = labelRepository.findByLabel(label.getLabel());
		if(list.size()==0){
			result.setSuccess(false).setMessage("添加失败，请勿重复添加该标签");
		}
		Label save = labelRepository.save(label);
		if (save == null) {
			result.setSuccess(false).setMessage("添加失败，请稍后再试");
		}
		return result.setSuccess(true).setMessage("添加成功");
	}

	@Override
	public AjaxResult dropLabel(String msg[]) {
		AjaxResult result = new AjaxResult();
		for (String label : msg) {
			labelRepository.deleteByLabel(label);
		}
		return result.setSuccess(true).setMessage("删除成功");
	}

}
