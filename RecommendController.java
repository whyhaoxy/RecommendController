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
	public Result getRecommendUsersService(String userId, int pageIndex, int numberPerPage){
		//更新推荐
		recommendUsersService.updateRecommendResult();
		List<Users> userArray=ArrayList<Users>();
		//得到推荐用户列表
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
		//将得到的用户放到result中
		result.setData(userArray);
		
		return result;
	}
	
	@RequestMapping("/recommendWeibo",method=RequestMethod.GET)
	public Result getRecommendWeiboService(String userId, int pageIndex, int numberPerPage){
		//更新推荐
		recommendWeiboService.updateRecommendResult();
		List<Weibo> weiboArray=ArrayList<Weibo>();
		//得到推荐用户列表
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
		//将得到的微博放到result中
		result.setData(weiboArray);
		
		return result;
	}
	
}
