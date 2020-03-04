
### 参考:
- [参考项目](https://www.xncoding.com/2017/07/05/spring/sb-restful.html)

### 重点
#### RESTful(Representational State Transfer)是一种软件设计风格，是目前最流行的一种互联网软件架构
- `@RestController` = `@ResponseBody` + `@Controller` 
- API常见设计
    - GET /users 查询列表   `@GetMapping`
    - POST /user 创建 `@PostMapping` = Post请求 + `RequestMapping`
    - GET /users/{id} 查询某个对象
    - PUT /users/{id} 更新某个对象
    - DELETE /users/{id} 删除某个对象

- RESTful架构中URI不应包含动词,如`GET POST`等应放在http协议中

- `@RequestBody`标注方法参数,将请求体映射到该参数
- `@PathVariable`标注方法参数, 映射URL的路径参数`{id}`
- `@RequestParam`标注方法参数, 映射URL中的请求参数`/user?id=1`

