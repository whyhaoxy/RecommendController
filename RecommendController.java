package cn.edu.bjtu.weibo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired
import cn.edu.bjtu.weibo.service.*;

@RestController
@RequestMapping("/r")
public class RecommendController {
	@Autowired
	private RecommendWeiboService recommendWeiboService;
	
	@Autowired
	private RecommendUsersService recommendUsersService;
	
	@RequestMapping("/recommendUsers",method=RequestMethod.GET)
	public Result getRecommendUsersService(@RequestBody User user,String userId, int pageIndex, int numberPerPage){
		//�����Ƽ�
		recommendUsersService.updateRecommendResult();
		List<Users> userArray=ArrayList<Users>();
		//�õ��Ƽ��û��б�
		userArray=recommendUsersService.getRecommendUserList(userId,pageIndex,numberPerPage);
		
		Result result = new Result();
		if(userArray == null || userArray.isEmpty())
		{
			result.setStatus(Result.FAILED);
			result.setTipCode("401");
			result.setTipMsg("Recommend user falied");
			return result;
		}
		
		result.setStatus(Result.SUCCESS);
		result.setTipCode("200");
		result.setTipMsg("Recommend user success");
		//���õ����û��ŵ�result��
		result.setData(userArray);
		
		return result;
	}
	
	@RequestMapping("/recommendWeibo",method=RequestMethod.GET)
	public Result getRecommendUsersService(@RequestBody User user,String userId, int pageIndex, int numberPerPage){
		//�����Ƽ�
		recommendWeiboService.updateRecommendResult();
		List<Weibo> weiboArray=ArrayList<Weibo>();
		//�õ��Ƽ��û��б�
		weiboArray=recommendWeiboService.getRecommentWeiboList(userId,pageIndex,numberPerPage);
		
		Result result = new Result();
		if(weiboArray == null || weiboArray.isEmpty())
		{
			result.setStatus(Result.FAILED);
			result.setTipCode("401");
			result.setTipMsg("Recommend user falied");
			return result;
		}
		
		result.setStatus(Result.SUCCESS);
		result.setTipCode("200");
		result.setTipMsg("Recommend user success");
		//���õ���΢���ŵ�result��
		result.setData(weiboArray);
		
		return result;
	}
	
}
