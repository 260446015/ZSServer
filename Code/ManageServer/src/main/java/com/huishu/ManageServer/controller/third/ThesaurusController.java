package com.huishu.ManageServer.controller.third;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huishu.ManageServer.common.AjaxResult;
import com.huishu.ManageServer.common.conf.MsgConstant;
import com.huishu.ManageServer.common.util.ReadExcelUtil;
import com.huishu.ManageServer.common.util.StringUtil;
import com.huishu.ManageServer.controller.BaseController;
import com.huishu.ManageServer.entity.dbSecond.CompanyEntity;
import com.huishu.ManageServer.entity.dbThird.KeywordTypeEntity;
import com.huishu.ManageServer.entity.dbThird.ThesaurusEntity;
import com.huishu.ManageServer.entity.dto.AbstractDTO;
import com.huishu.ManageServer.entity.dto.IndustrySummitDTO;
import com.huishu.ManageServer.entity.dto.dbThird.AttributeDTO;
import com.huishu.ManageServer.entity.dto.dbThird.RelatedDTO;
import com.huishu.ManageServer.entity.dto.dbThird.TKeyWordDTO;
import com.huishu.ManageServer.entity.dto.dbThird.WordDataDTO;
import com.huishu.ManageServer.entity.dto.dbThird.addKeyWordDTO;
import com.huishu.ManageServer.es.entity.SummitInfo;
import com.huishu.ManageServer.service.third.ThesaurusService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author hhy
 * @date 2018年3月16日
 * @Parem
 * @return 
 * 
 */

@Controller
@RequestMapping("/apis/keyInfo")
public class ThesaurusController extends BaseController{
	private static final Logger LOGGER = Logger.getLogger(ThesaurusController.class);
	
	@Autowired
	private ThesaurusService service;
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "{page}.html" }, method = RequestMethod.GET)
	public String findYQCompany(@PathVariable String page,String id ,Model model) {
		if("ThesaurusRelatedManage".equals(page)){
			if(StringUtil.isNotEmpty(id)){
				model.addAttribute("info", id);
			}
		}
		return "/thesaurus/" + page;
	}
	
	
	/**
	 *	新增词性分类信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateLabel.json", method = RequestMethod.GET,params={"typeWord"})
	public AjaxResult updateLabel( String typeWord){
		try {
			if(StringUtil.isEmpty(typeWord)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info= service.updateTypeWord(typeWord);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("新增词性分类信息失败!原因是：",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}

	/**
	 *	获取所有的关系词信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllRelated.json", method = RequestMethod.GET)
	public AjaxResult getAllRelated(){
		try {
			return success( service.getAllRelatedInfo());
		} catch (Exception e) {
			LOGGER.error("获取所有关系词信息失败，原因是:",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 *	新增关系词信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRelated.json", method = RequestMethod.GET,params={"relatedWord"})
	public AjaxResult updateRelated( String relatedWord){
		try {
			if(StringUtil.isEmpty(relatedWord)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info= service.updateRelatedWord(relatedWord);
			
			return success(info);
		} catch (Exception e) {
			LOGGER.error("新增关系词信息失败!原因是：",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 *	根据类型词获取关键词的信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findWordByType.json", method = RequestMethod.GET,params={"typeWord"})
	public AjaxResult findWordByType( String typeWord){
		try {
			if(StringUtil.isEmpty(typeWord)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			List<ThesaurusEntity> obj = service.findKeyWordByType(typeWord);
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("根据类型词获取关键词的信息失败!原因是",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	
	/**
	 * 关键词列表展示
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findKeyWordInfoList.json", method = RequestMethod.POST)
	public AjaxResult findKeyWordInfoList(@RequestBody TKeyWordDTO dto){
		try {
 			if(StringUtil.isEmpty(dto.getType())||StringUtil.isEmpty(dto.getSort())){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			Page<WordDataDTO> page = service.findByPage(dto);
			return  successPage(page, page.getNumber()+1);
		} catch (Exception e) {
			LOGGER.error("获取关键词库列表失败!", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 新增或者修改词-属性
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpAttributeDTO.json",method=RequestMethod.POST)
	public AjaxResult addOrUpAttributeDTO(@RequestBody AttributeDTO dto){
		try {
			if(StringUtil.isEmpty(dto.getKeyword())){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info = service.saveOrUpAttributeData(dto);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("新增或者更新词失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 * 新增或者修改词-关系
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpAttributeRelatedDTO.json",method=RequestMethod.POST)
	public AjaxResult addOrUpAttributeRelatedDTO(@RequestBody RelatedDTO dto){
		try {
			if(StringUtil.isEmpty(dto.getKeyword())){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONArray info = service.addOrUpdate(dto);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("新增或者更新词失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	
	/**
	 * Excel文件上传（批量处理数据）
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ExcelDataUpload.json",method=RequestMethod.POST)
	public AjaxResult ExcelDataUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		LOGGER.info("file name is :" + file.getOriginalFilename());
		if(!file.isEmpty()){
			String 	OriginalFilename = file.getOriginalFilename();//文件名称
			String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
			if(!ServletFileUpload.isMultipartContent(request)){
				return error("文件没有上传");
			}
			
			File uploadDir = new File("images");
			if (!uploadDir.isDirectory()) {
				if (!uploadDir.mkdir()) {
					return error("上传文件路径非法"); 
				}
			}
			if (!uploadDir.canWrite()) {
				return error("上传目录没有写权限");
			}
			String subSequence = OriginalFilename.substring(0,OriginalFilename.lastIndexOf(".") );
			
			String newname = UUID.randomUUID() + "." + fileSuffix;
			try {
				String url = "e:/excel";
				File saveFile = new File(url, newname);
				if(!new File(url).exists())   {
				    new File(url).mkdirs();
				}
				file.transferTo(saveFile);
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
						ReadExcelUtil util = new ReadExcelUtil();
						List<String> map = util.readExcel("e:/excel/" + newname, newname);
						//获取文件名OriginalFilename
						if(subSequence.indexOf("属性")<=0){
							map.subList(0, 1).clear();//不是属性表，需要把第一行清除
							//遍历数据
							for(String value:map){
								service.addDataInfo(value);//保存数据
							}
						}else{
							List<String> subList = map.subList(0, 1);//属性表，需要进行属性的动态添加与删除
//							map.subList(0, 1).clear();
							//遍历数据
							for(String value:map){
								//保存数据
								service.addDataInfo(subList,value);
							}
						}
						
							service.printLog(OriginalFilename,"数据存库完成");
						
						} catch (Exception e) {
							LOGGER.error("存储数据失败！", e);
							service.printLog(OriginalFilename,e.toString());
						}
						
					}
				}).start();;
				return success("上传成功");
			}catch(Exception e){
				LOGGER.error("文件上传失败：",e);
				return error("文件上传异常");
			}
		}else{
			return error("文件没有上传");
		}
	}
	
	/**
	 * 获取标签属性
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getLabelInfo.json",method=RequestMethod.GET)
	public AjaxResult getLabelInfo(){
		List<KeywordTypeEntity> list = service.getLableInfo();
		return success(list);
	}
	
	/**
	 *	根据id获取属性的信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findAttributeInfoById.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult findAttributeInfoById(String id){
		try {
			if(StringUtil.isEmpty(id)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONObject obj = service.findAttributeInfoById(id);
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("根据id查看关键词以及关联关系!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 *	根据id获取关联关系的信息
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findRelatedInfoById.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult findRelatedInfoById( String id){
		try {
			if(StringUtil.isEmpty(id)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			JSONObject obj = service.findRelatedById(id);
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("根据id查看关键词以及关联关系!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 *	根据id删除词
	 * @param dto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRelatedInfoById.json", method = RequestMethod.GET,params={"id"})
	public AjaxResult deleteRelatedInfoById( String id){
		try {
			if(StringUtil.isEmpty(id)){
				return error(MsgConstant.ILLEGAL_PARAM);
			}
			boolean info = service.deleteRelatedById(id);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("根据id删除关键词以及关联关系失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
	/**
	 * 根据id删除关联关系
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteRelateById.json",method=RequestMethod.GET,params={"id"})
	public AjaxResult deleteRelateById(String id ){
		if(StringUtil.isEmpty(id)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			boolean info = service.deleteRelatedInfoById(id);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("根据id删除关联关系失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	/**
	 * 根据新增词获取新增数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findInfoByKeyword.json",method=RequestMethod.GET,params={"keyword"})
	public AjaxResult findInfoByKeyword(String keyword ){
		if(StringUtil.isEmpty(keyword)){
			return error(MsgConstant.ILLEGAL_PARAM);
		}
		try {
			JSONObject info = service.findAllInfoByKeyWord(keyword);
			return success(info);
		} catch (Exception e) {
			LOGGER.error("根据新增词获取新增数据失败：", e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
	}
	@ResponseBody
	@RequestMapping(value = "/listKeyWord.do", method = RequestMethod.GET)
	public AjaxResult listKeyWord(){
		try {
			JSONArray obj = service.findAllKeyWord();
			return success(obj);
		} catch (Exception e) {
			LOGGER.error("列表查看舆情公司数据失败!",e);
			return error(MsgConstant.SYSTEM_ERROR);
		}
		
	}
}
