package com.springbootmybatis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.springbootmybatis.entity.History;
import com.springbootmybatis.entity.LoginEntity;
import com.springbootmybatis.entity.PageBean4User;
import com.springbootmybatis.entity.User;
import com.springbootmybatis.service.HistoryService;
import com.springbootmybatis.service.UserService;

import cn.dsna.util.images.ValidateCode;

@RestController
@CrossOrigin(allowCredentials ="true")//设置是否允许客户端发送cookie信息。默认是false 
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HistoryService HistoryService;
	
	
	/**
     * 生成验证码
     *
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping("/getValidateCode")
    public void getValidateCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 告诉客户端不使用缓存
        response.setHeader("param", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setIntHeader("expires", 0);

        ValidateCode vc = new ValidateCode(110, 25, 4, 9);
        String code = vc.getCode();
        request.getSession().setAttribute("code", code);
        vc.write(response.getOutputStream());
    }
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser")
	public Integer addUser(@RequestBody User user) {
		user.setUserId(String.valueOf(System.currentTimeMillis()));
		user.setRemainingBooks(3);
		if (userService.nameExists(user)!=null) {
			return 0;
		}else{
			return userService.addUser(user);
		}
	}
	/**
	 * 更改用户资料
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUser")
	public Integer updateUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		return userService.updateUser(user);
	}
	/**
	 * 更改密码
	 * @param user
	 * @return
	 */
	@RequestMapping("/updatePassword")
	public Integer updatePassword(@RequestBody User user) {
		// TODO Auto-generated method stub
		return userService.updatePassword(user);
	}
	/**
	 *  根据用户loginId查询其个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/showUser")
	public User showUser(@RequestBody User user) {
		// TODO Auto-generated method stub
		return userService.showUser(user);
	}
	
	/**
	 * 用户登陆认证
	 * @param user
	 * @return
	 */
	@RequestMapping("/loginUser")
	public Integer loginUser(@RequestBody LoginEntity login, HttpServletRequest request) {
		String realCode = (String) request.getSession().getAttribute("code");

		if (!login.getValidateCode().toLowerCase().equalsIgnoreCase(realCode)) {
			return 3;
		}else {
			User user = new User();
			user.setLoginId(login.getLoginId());
			user.setUserPwd(login.getUserPwd());
			/*管理员账号和密码*/
			if (user.getLoginId().toString().equals("admin") &&user.getUserPwd().toString().equals("admin")) {			
				return 2;//登陆管理员
			}else {
				User user2=userService.loginUser(user);
				if(user2!=null){			
					return 1;//登陆用户
				}else {
					return 0;//登陆失败
				}
			}
		}



	}	
	/**
	 * 管理员查询所有用户
	 * @return
	 */
	@RequestMapping("/findAllUsers")
	public PageBean4User findAllUsers(@RequestBody PageBean4User pageBean4User){
				//分页
				PageHelper.startPage(pageBean4User.getCurrentPage(), pageBean4User.getPageSize());
				List<User> list = userService.findAllUsers(pageBean4User.getUserPrimaryKey());
				//得到总数量
				Integer itemCount = userService.getUsersCount(pageBean4User.getUserPrimaryKey());
				//根据分页条件得到总的页数
				
				pageBean4User.setTotalCount(itemCount);
				pageBean4User.setUsers(list);
				return pageBean4User;
	}
	/**
	 * 根据loginId删除某用户
	 * @param user
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public Integer deleteUser(@RequestBody User user) {
		History history=new History();
		history.setuId(user.getUserId());
		if (HistoryService.getHistoryByUId(history).size()==0) {
			Integer flag = HistoryService.deleteHistoryByUId(user.getUserId());
			if(flag > 0 ) {
				return userService.deleteUser(user);//1 删除成功
			}else {
				return 3;
			}
			
		}else {
			return 2;//有借书没还 不能删
		}			

		
	}
}
