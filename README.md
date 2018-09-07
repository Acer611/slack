# slack  
this is my slack   
  
slack   
#项目介绍    

this is my slack

#软件架构  

Build --- Gradle  
Compiler ---JAVA 1.8   
  
 后台技术：    
 
    SpringBoot2  
    HikariCP   
    Mybatis  
    Redis   
    Spring-date-redis   
    swagger2  
    Spring-session-data-redis   
    SpringSecurity 
    Scheduled    
    spring aop  
    RabbitMQ  
      
      
新加入 定时任务 Scheduled    
邮件发送 功能     
加入spring aop 用AOP统一处理Web请求日志  
使用腾讯和NEXMO的短信发送功能
  
UserControll 发送手机短信验证码 使用RabbitMQ消息队列,   
调用接口时把手机号和验证码放入message的队列中 作为sender(Provider)     
MessageReceiver 作为接受者（receiver）消费message队列中的信息  
添加二维码的生成工具类


软件架构说明   
 


#安装教程  
xxxx  
xxxx 
xxxx  

#使用说明  

服务启动后，可通过访问 host:prot/swagger-ui.html(如：http://localhost:8888/swagger-ui.html )查看API 接口  
xxxx    
xxxx  

#参与贡献  

Fork 本项目
新建 Feat_xxx 分支
提交代码
新建 Pull Request
