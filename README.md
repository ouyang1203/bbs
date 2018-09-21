# bbs

## 代码更新后是master分支，需要创建其他分支进行修改代码，通过页面发起pull request 进行代码的合并。  
### git 使用示例：  
git checkout master  
git pull origin master  
git checkout -b bug-2577  
#### 修改文件  
vi a.txt  
git add a.txt  
git commit -m "fix bug 2577"  
#### 从master库中获取最新代码  
git fetch origin master:master  
git rebase master  
#### 把自己的代码合并进dev  
git checkout dev  
git pull origin dev  
git merge --no-ff bug-2577  
#### 从本地dev提交本地dev到远端  
git push origin dev  
#### 删除本地分支  
git branch -d bug-2577  
## 修改README文件需要换行时，在当前行末尾添加两个空格即可  
## 启动应用之前修改host配置文件新增如下配置  
127.0.0.1 instance1  
127.0.0.1 instance2  
## 应用启动顺序为bbsEureka,bbsConfig,后续其他应用可以随便启动，bbsConfig作为配置中心必须要在其他业务应用启动之前运行  

