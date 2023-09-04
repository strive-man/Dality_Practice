# 关于模块

## 最近更新介绍

时间：2023.8.23

内容：

- 修正了github上所提出的所有遗留问题
- 添加配置了日志管理功能

​		关于日志功能详细信息可再沟通，目前能保证此项添加不会影响模块功能

时间：2023.8.20

内容：

- Idea配置了正确的Google代码规范插件
- 代码注释模板已更新使其符合规范
- 所有的类、方法注释已经检查更新完毕
- 模块接口测试运行正常

## 介绍

- 上传内容：启动类 kids-attention-centerV2.0基础上，并入了member模块 

- 模块名称：kids-attention-member-9025

- 模块主要实现功能：孩子家长、员工登录账号，修改账号密码；超级管理员登录账号【固定账号密码】

  > 实现了拦截器进行登录校验的功能：interceptor包+config包的MyLoginInterceptor类
  >
  > 实现了异常类处理全局各种异常的功能：exception包+commons模块的exception包

- 对应的controller层：MemberLoginController、MemberUpdateController

- 后端接口测试文档：https://www.showdoc.cc/2346962693776179 ,密码：zgq

## TODO:

```
1：MyLoginInterceptor
```

拦截器配置类内需要根据后续开发完善拦截页面url地址

```
2：LoginInterceptor
```

拦截器本身逻辑代码也是需要根据后续开发继续完善内容

## 技术债：

- util包内个别util类没有解决异味，因为要和前端对接完成后确定util类的构成，暂时不处理
- RSA前端加密后的内容目前是后端工具类实现的，后续对接后再修改

## 备注：

- sonar给出警告部分，个人认为不用修改，有不同意见可以联系我
- templates包下的error.html文件是，用户想访问此项目但地址输错返回一个404页面，后期项目合并后这个内容直接放到路由模块即可，暂留

