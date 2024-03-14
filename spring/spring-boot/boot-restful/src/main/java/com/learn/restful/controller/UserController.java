/**
 * @author G-bug 2019/9/29
 */
package com.learn.restful.controller;

import com.learn.restful.model.BaseResponse;
import com.learn.restful.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @auth Administrator
 */

@RestController // 等同 @Controller + @ResponseBody 直接返回json数据
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BaseResponse<List<User>> getUserList() {
        List<User> r = new ArrayList<>(users.values());
        return new BaseResponse<>(true, "查询列表成功", r);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @PostMapping("")
    public BaseResponse<String> postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return new BaseResponse<>(true, "新增成功", "");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse<User> getUser(@PathVariable Long id) {
        return new BaseResponse<>(true, "查询成功", users.get(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    // @PutMapping
    // @PathVariable 获取参数 id
    public BaseResponse<String> putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return new BaseResponse<>(true, "更新成功", "");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResponse<String> deleteUser(@PathVariable Long id) {
        users.remove(id);
        return new BaseResponse<>(true, "删除成功", "");
    }
}
