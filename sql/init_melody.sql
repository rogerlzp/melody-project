

insert  into `SYS_PERM`(`PVAL`,`PARENT`,`PNAME`,`PTYPE`,`LEAF`,`CREATED`,`UPDATED`) values
('*',NULL,'所有权限',0,NULL,'2018-04-19 18:14:12',NULL),
('a:auth',NULL,'登录模块',3,1,NULL,NULL),
('a:gradleBuild','a:test','构建gradle',3,1,NULL,NULL),
('a:mvn:install','a:test','mvnInstall',3,1,NULL,NULL),
('a:option',NULL,'选项模块',3,1,NULL,NULL),
('a:perm:query','a:sys:perm','查询sys_perm',3,1,NULL,NULL),
('a:perm:update','a:sys:perm','update',3,1,NULL,NULL),
('a:role:query','a:sys:role','query',3,1,NULL,NULL),
('a:role:update','a:sys:role','update',3,1,NULL,NULL),
('a:sys:perm',NULL,'系统权限模块',3,0,NULL,NULL),
('a:sys:role',NULL,'系统角色模块',3,0,NULL,NULL),
('a:sys:接口',NULL,'系统用户模块',3,1,NULL,NULL),
('a:test',NULL,'测试模块模块',3,0,NULL,NULL),
('b:user:add','m:sys:user','添加用户',2,NULL,'2018-06-02 11:00:37',NULL),
('b:user:delete','m:sys:user','删除用户',2,NULL,'2018-06-02 11:00:56',NULL),
('m:menu1',NULL,'菜单1',1,1,NULL,NULL),
('m:menu2',NULL,'菜单2',1,1,NULL,NULL),
('m:menu3',NULL,'菜单3',1,0,NULL,NULL),
('m:menu3:1','m:menu3','菜单3-1',1,1,NULL,NULL),
('m:menu3:2','m:menu3','菜单3-2',1,1,NULL,NULL),
('m:menu3:3','m:menu3','菜单3-3',1,1,NULL,NULL),
('m:menu4',NULL,'菜单4',1,0,NULL,NULL),
('m:menu4:1','m:menu4','菜单4-1',1,0,NULL,NULL),
('m:menu4:1:a','m:menu4:1','菜单4-1-a',1,1,NULL,NULL),
('m:menu4:1:b','m:menu4:1','菜单4-1-b',1,1,NULL,NULL),
('m:menu4:1:c','m:menu4:1','菜单4-1-c',1,1,NULL,NULL),
('m:menu4:2','m:menu4','菜单4-2',1,1,NULL,NULL),
('m:sys',NULL,'系统',1,0,NULL,NULL),
('m:sys:perm','m:sys','权限管理',1,1,NULL,NULL),
('m:sys:role','m:sys','角色管理',1,1,NULL,NULL),
('m:sys:user','m:sys','用户管理',1,1,NULL,NULL);


insert  into `SYS_ROLE`(`RID`,`RNAME`,`RDESC`,`RVAL`,`CREATED`,`UPDATED`) values ('1002748319131680769','普通用户','具有一般的权限，不具备系统菜单权限1','common','2018-06-02 11:06:44','2018-06-02 11:10:57'),('1002806178141937666','财务','拥有财务相关权限','finance','2018-06-02 14:56:39',NULL),('1002806220860923906','仓管','拥有财务相关权限','stock','2018-06-02 14:56:49',NULL),('1002806266750803970','销售','拥有财务相关权限','sale','2018-06-02 14:57:00',NULL),('1002807171923550210','文员','拥有文员相关的权限','stuff','2018-06-02 15:00:36',NULL),('1002807288885911553','啊','asdf','sdf','2018-06-02 15:01:04',NULL),('1002807344665960449','阿斯达','sdfwerty','sgsf','2018-06-02 15:01:17',NULL),('1002807369559154689','阿斯蒂芬','撒旦法GV','asdfgewrgr','2018-06-02 15:01:23',NULL),('1002807394460737537','阿斯蒂芬','颂德歌功','asdgwergreh','2018-06-02 15:01:29',NULL),('1002807427771899906','asddfgswf','阿道夫噶','drgregerg','2018-06-02 15:01:37',NULL),('999999888888777777','超级管理员','具有本系统中最高权限','root','2018-04-19 17:34:33',NULL);

insert  into `SYS_ROLE_PERM`(`ROLE_ID`,`PERM_VAL`,`PERM_TYPE`) values ('1002748319131680769','a:perm:update',3),('1002748319131680769','a:sys:perm',3),('1002748319131680769','b:user:add',2),('1002748319131680769','m:sys',1),('1002748319131680769','m:sys:user',1),('999999888888777777','*',NULL);

insert  into `SYS_USER`(`UID`,`UNAME`,`NICK`,`PWD`,`SALT`,`LOCK`,`CREATED`,`UPDATED`) values ('1002748017179541505','guanyu','关羽','n2Wd7JramFVrHcijY4KW1rNTGKnwyYPJ0RDYvy2BdK0=','aem4EsAFae5rObEdZP4Xlw==',NULL,'2018-06-02 11:05:32','2018-06-02 14:40:01'),('1002748102537822209','zhangfei','张飞','g+aRBmgVTTPkNLNwJfM64D8rwH94WEgDgckQ4fuQp6w=','Sqhvxsnc0HZSQEFKjBB9zQ==',NULL,'2018-06-02 11:05:52',NULL),('986177923098808322','admin','刘备','J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=','wxKYXuTPST5SG0jMQzVPsg==',0,'2018-04-17 17:41:53','2018-04-19 17:08:15');

insert  into `SYS_USER_ROLE`(`USER_ID`,`ROLE_ID`) values ('1002748017179541505','1002748319131680769'),('1002748102537822209','1002748319131680769'),('986177923098808322','999999888888777777');
