# MyApplication 此App是毕业设计(2018.03.21更新)
1.具有更加完善的UI 

2.数据库使用的是GreenDao数据库（用户登陆）

3.网络框架使用的是OkHttp网络框架（下载和上传功能）

>  服务器是自己搭建的阿里云服务器

## 实现功能
1.在主页点击“+”可进行课程的插入（暂时不支持添加图片封面）

2.对课程插入后可在搜索界面对已插入的课程信息进行搜索

3.使用RecyclerView实现好友列表（需要多次刷新才显示好友列表，问题暂为解决）

4.在“more”界面可点击“下载”和“上传”按钮进行 下载和上传（下载已指定的文件并显示出来；上传也是指定的文件，不支持指定下载和上传自己的文件）

5.在侧拉菜单更新评分功能（暂时不支持提交对老师的评论）

## 更新
1.用RecyclerView实现个人圈子界面 和 消息显示界面

2.点解刷新按钮能显示所有课程

3.上传功能优化，可支持自己上传指定文件（文件管理器路径下的文件），由于服务器原因只支持上传<=2M的文件

4.实现camera跳转功能，能拍照并储存在SD中

5.登陆界面实现记住账号密码功能

## 更新日志
> 2020/04/25 kotlin-mvvm 重写完成基类抽取+登陆(界面+wanandroid接口)+注册(界面+wanandroid接口)
