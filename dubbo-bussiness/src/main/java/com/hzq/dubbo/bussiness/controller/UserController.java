package com.hzq.dubbo.bussiness.controller;


import com.hzq.dubbo.User;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.bussiness.service.BusUserInfoService;
import com.hzq.dubbo.bussiness.service.BusUserService;
import com.hzq.dubbo.provider.ProviderInterface;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * mybatisplus测试
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BusUserService busUserService;

    @Reference(check = false)
    private ProviderInterface providerInterface;

    /**
     * 查询
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @GetMapping("/remote")
    public ResultResponse<String> remote(@RequestParam("para") String para){
        return providerInterface.remoteSPI(para);
    }

    /**
     * 查询
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
    */
    @GetMapping("/getUser")
    public ResultResponse<User> getUser(@RequestParam("id") Integer id){
        return ResultResponse.success(busUserService.getById(id));
    }

    /**
     * 新增
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @PostMapping("/addUser")
    public ResultResponse<Integer> addUser(@RequestBody User user){
        boolean save = busUserService.save(user);
        return ResultResponse.success(user.getId());
    }

    /**
     * 更新
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @PostMapping("/updateUser")
    public ResultResponse<Boolean> updateUser(@RequestBody User user){
        return ResultResponse.success(busUserService.saveOrUpdate(user));
    }

    /**
     * 删除
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @GetMapping("/deleteUser")
    public ResultResponse<Boolean> deleteUser(@RequestParam("id") Integer id){
        return ResultResponse.success(busUserService.removeById(id));
    }
}
