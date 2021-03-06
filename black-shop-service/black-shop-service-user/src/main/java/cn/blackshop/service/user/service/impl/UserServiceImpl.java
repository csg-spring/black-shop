/**  
 
* <p>Company: www.black-shop.cn</p>  

* <p>Copyright: Copyright (c) 2018</p>   

* black-shop(黑店) 版权所有,并保留所有权利。

*/  
package cn.blackshop.service.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import cn.blackshop.basic.redis.util.RedisUtil;
import cn.blackshop.common.basic.constants.Constants;
import cn.blackshop.common.basic.core.ApiService;
import cn.blackshop.common.basic.core.ResponseResult;
import cn.blackshop.common.utils.BeanUtils;
import cn.blackshop.common.utils.StringUtils;
import cn.blackshop.model.user.dto.out.UserOutDTO;
import cn.blackshop.model.user.entity.User;
import cn.blackshop.service.api.user.service.UserService;
import cn.blackshop.service.user.mapper.UserMapper;

/**
 * UserBasicServiceImpl
 * @author zibin
 */
@RestController
public class UserServiceImpl extends ApiService<UserOutDTO> implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 根据用户名查询用户
	 */
	@Override
	public ResponseResult<UserOutDTO> getUserByNickName(String nickName) {
	    User user=userMapper.getUserByNickName(nickName);
	    BeanUtils.copyEntityProperties(user, UserOutDTO.class);
		return setResultSuccess();
	}

	/**
	 * 批量查询用户集合
	 */
	@Override
	public ResponseResult<List<UserOutDTO>> queryUserList() {
	  List<User> userList=userMapper.queryUserList();
	  List<UserOutDTO> userDTOs = BeanUtils.batchTransform(UserOutDTO.class, userList);
	  /*return setResultSuccess(userDTOs);*/
	  return null;
	}

  @Override
  public ResponseResult<UserOutDTO> existMobileNumber(String mobileNumber) {
    if(StringUtils.isBlank(mobileNumber)){
      return setResultError("手机号码不能为空!");
    }
    User user=userMapper.existMobileNumber();
    if(user==null){
      return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户信息不存在!");
    }
    UserOutDTO userOutDTO = BeanUtils.transfrom(UserOutDTO.class, user);
    return setResultSuccess(userOutDTO);
  }

  @Override
  public ResponseResult<UserOutDTO> getUserInfo(String token) {
    return null;
  }

  @Override
  public ResponseResult<UserOutDTO> getUserByUserName(String userName) {
    return null;
  }
}
