# bbs
# bbs
 启动应用之前修改host配置文件新增如下配置
 127.0.0.1 instance1
 127.0.0.1 instance2
应用启动顺序为bbsEureka,bbsConfig,后续其他应用可以随便启动，bbsConfig作为配置中心必须要在其他业务应用启动之前运行
