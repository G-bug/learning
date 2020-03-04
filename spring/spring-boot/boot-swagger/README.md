
### 重点
- `SeaggerConfig`
    > `ApiInfo`中配置基本描述信息, `Dockt`中配置具体访问协议及扫描包地址
- 重要注解
    - `@RestControllerAdvice` = `@ResponseBody` + `@ControllerAdvice`
        >前者将全部异常处理返回为json串, 后者可以将`@ExceptionHandler` `@InitBinder` `@ModelAttribute`的定义作用到所有的`@RequsetMapping`中
    - `@EnableSwagger2` 开启Swagger2 
    - `@Api`定义接口所在类,如某个Controller类
    - `@ApiResponse(s)`描述错误响应信息
    - `@ApiOperation`描述一个接口
    - `@ApiImplicitParam(s)`对接口参数进行描述    
    - `@ApiModel`描述一个Model对象,用于复杂参数的情况
    - `@ApiIgnore`忽略该接口

- 与Shiro集成需要将如`/swagger-ui.html`权限放开

访问地址`http://localhost:8080/swagger-ui.html`

### 参考
- [参考项目](https://www.xncoding.com/2017/07/08/spring/sb-swagger2.html)