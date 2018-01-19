package com.huishu.ManageServer.service.user.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.huishu.ManageServer.entity.dbFirst.UserPark;
import com.huishu.ManageServer.repository.first.UserParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.huishu.ManageServer.common.conf.KeyConstan;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.entity.dbFirst.SearchCount;
import com.huishu.ManageServer.entity.dbFirst.UserBase;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.AccountDTO;
import com.huishu.ManageServer.entity.dto.AccountSearchDTO;
import com.huishu.ManageServer.entity.dto.UserBaseDTO;
import com.huishu.ManageServer.repository.first.SearchCountRepository;
import com.huishu.ManageServer.repository.first.UserRepository;
import com.huishu.ManageServer.security.Digests;
import com.huishu.ManageServer.security.Encodes;
import com.huishu.ManageServer.service.AbstractService;
import com.huishu.ManageServer.service.user.UserService;

/**
 * 用户管理实现类
 *
 * @author yindq
 * @date 2018/1/4
 */
@Service
//@Transactional("firstTransactionManager")
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SearchCountRepository searchCountRepository;
	@Autowired
	private UserParkRepository userParkRepository;

	@Override
	public Page<UserBase> listUserBase(AbstractDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<UserBase> list = userRepository.findPage(dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = userRepository.count();
		Page<UserBase> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	@Override
	public Page<UserBase> listParkUserBase(AccountDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		List<UserBase> list = userRepository.findParkPage(dto.getPark(),dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = userRepository.countByUserPark(dto.getPark());
		Page<UserBase> impl = new PageImpl<>(list, request, count);
		return impl;
	}

	@Override
	public Boolean saveUserBase(UserBaseDTO dto) {
		UserBase userBase = new UserBase();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = format.format(calendar.getTime());
		userBase.setCreateTime(now);
		String end;
		if(dto.getUserTime().equals("1")){
			calendar.add(Calendar.MONTH ,+1);
			end = format.format(calendar.getTime());
		}else if(dto.getUserTime().equals("12")){
			calendar.add(Calendar.MONTH ,+12);
			end = format.format(calendar.getTime());
		}else if (dto.getUserTime().equals("24")){
			calendar.add(Calendar.MONTH ,+24);
			end = format.format(calendar.getTime());
		}else if (dto.getUserTime().equals("36")){
			calendar.add(Calendar.MONTH ,+36);
			end = format.format(calendar.getTime());
		}else {
			calendar.add(Calendar.MONTH ,+120);
			end = format.format(calendar.getTime());
		}
		userBase.setExpireTime(end);
		userBase.setId(dto.getId());
		userBase.setImageUrl(dto.getImageUrl());
		userBase.setIsCheck(1);
		userBase.setIsSingle(0);
		userBase.setIsWarn(0);
		byte[] salt = Digests.generateSalt(Encodes.SALT_SIZE);
		byte[] hashPassword = Digests.sha1(MsgConstant.USER_PASSWORD.getBytes(), Digests.hexStringToBytes(Encodes.encodeHex(salt)),
				Encodes.HASH_INTERATIONS);
		userBase.setPassword(Encodes.encodeHex(hashPassword));
		userBase.setRealName(dto.getRealName());
		userBase.setSalt(Encodes.encodeHex(salt));
		userBase.setStartTime(now);
		userBase.setTelphone(dto.getTelphone());
		userBase.setUserAccount(dto.getUserAccount());
		userBase.setUserDepartment(dto.getUserDepartment());
		userBase.setUserEmail(dto.getUserEmail());
		userBase.setUserJob(dto.getUserJob());
		userBase.setUserLevel(dto.getUserLevel());
		UserPark one = userParkRepository.findOne(Long.valueOf(dto.getUserPark()));
		userBase.setUserPark(one.getName());
		userBase.setUserType(dto.getUserType());
		UserBase save = userRepository.save(userBase);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public Boolean dropUserBase(Long id) {
		userRepository.delete(id);
		return true;
	}

	@Override
	public UserBase findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<AccountDTO> getAccountByUser(AccountDTO dto) {
		List<UserBase> ids = userRepository.findByUserPark(dto.getPark());
		BigDecimal price = new BigDecimal(0);
		List<AccountDTO> outList = new ArrayList<>();
		for (UserBase user : ids) {
			List<SearchCount> list = searchCountRepository.findByUserId(user.getId());
			if(list.size() > 0){
				AccountDTO out = new AccountDTO();
				BigDecimal totalPrice = new BigDecimal(0);
				for (SearchCount sc : list) {
					String spec = sc.getSpec();
					try{
						switch (spec) {
						case KeyConstan.URL.SOUSUO:
							price = new BigDecimal(0.01); 
							break;
						case KeyConstan.URL.BASEINFO:
							price = new BigDecimal(0.1); 
							break;
						case KeyConstan.URL.STAFF:
							price = new BigDecimal(0.1); 
							break;
						case KeyConstan.URL.HOLDER:
							price = new BigDecimal(0.1); 
							break;
						case KeyConstan.URL.INVERST:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.CHANGEINFO:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.NIANBAO:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.BRANCH:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.HISTORYRONGZI:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.TEAMMEMBER:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.PRODUCTINFO:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.TZANLI:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.JINGPIN:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.LAWSUIT:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.COURTANNOUNCEMENT:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.SHIXINREN:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.ZHIXINGINFO:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.ABNORMAL:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.XINGZHENGCHUFA:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.YANZHONGWEIFA:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.QIANSHUIGONGGAO:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.BIDS:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.BOND:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.PURCHASELAND:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.EMPLOYMENTS:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.SHUIWU:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.CHECKINFO:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.APPBKINFO:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.CERTIFICATE:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.SHANGBIAO:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.PATENTS:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.COPYREG:
							price = new BigDecimal(0.15); 
							break;
						case KeyConstan.URL.ICP:
							price = new BigDecimal(0.05); 
							break;
						case KeyConstan.URL.VOLATILITY:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.COMPANYINFO:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.HOLDINGCOMPANY:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.SENIOREXECUTIVE:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.ANNOUNCEMENT:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.SHAREHOLDER:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.ISSUERELATED:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.SHARESTRUCTURE:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.EQUITYCHANGE:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.BONUSINFO:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.ALLOTMEN:
							price = new BigDecimal(0.07); 
							break;
						case KeyConstan.URL.QIYEFENGXIAN:
							price = new BigDecimal(0.1); 
							break;
						case KeyConstan.URL.FENGXIANXINXI:
							price = new BigDecimal(0.1); 
							break;
						case KeyConstan.URL.RENFENGXIAN:
							price = new BigDecimal(0.1); 
							break;
						default:
							break;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					BigDecimal d = price.multiply(BigDecimal.valueOf(sc.getTotal()));
					totalPrice = totalPrice.add(d);
				}
				out.setTotalPrice(totalPrice.doubleValue());
				out.setUserAccount(user.getUserAccount());
				outList.add(out);
			}
		}
		return outList;
	}
	

	@Override
	public Boolean modifyIsSingle(Long id,Integer isSingle) {
		UserBase one = userRepository.findOne(id);
		one.setIsSingle(isSingle);
		UserBase save = userRepository.save(one);
		if(save==null){
			return false;
		}
		return true;
	}

	@Override
	public Page<UserBase> getAccountList(AccountSearchDTO dto) {
		PageRequest request = new PageRequest(dto.getPageNum(),dto.getPageSize());
		String[] times = analysisDate(dto.getTime());
		List<UserBase> list = userRepository.findCheckPage(dto.getType(),times[0], times[1],dto.getPageNum() * dto.getPageSize(), dto.getPageSize());
		long count = userRepository.countCheckPage(dto.getType(),times[0], times[1]);
		Page<UserBase> impl = new PageImpl<>(list, request, count);
		return impl;
	}
	
	@Override
	public Boolean modifyIsCheck(Long id) {
		UserBase one = userRepository.findOne(id);
		one.setIsCheck(1);
		UserBase save = userRepository.save(one);
		if(save==null){
			return false;
		}
		return true;
	}
}
