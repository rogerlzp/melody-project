

insert  into `sys_perm`(`pval`,`parent`,`pname`,`ptype`,`leaf`,`created`,`updated`) values
('a:sys:role',NULL,'系统角色模块',3,0,NULL,NULL),
('m:menu1',NULL,'菜单1',1,1,NULL,NULL),
('m:menu3:1','m:menu3','菜单3-1',1,1,NULL,NULL),
('a:option',NULL,'选项模块',3,1,NULL,NULL),
('a:perm:query','a:sys:perm','查询sys_perm',3,1,NULL,NULL),
('a:perm:update','a:sys:perm','update',3,1,NULL,NULL),



('m:menu5',NULL,'产品管理',1,1,NULL,NULL),
('m:menu5:1','m:menu5','品牌管理',1,1,NULL,NULL),
('m:menu5:2','m:menu5','分类管理',1,1,NULL,NULL),
