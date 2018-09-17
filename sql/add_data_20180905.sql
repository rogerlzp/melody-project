

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


# 会员管理模块
('a:user',NULL,'用户管理模块',3,0,NULL,NULL),




# 活动banner
insert into `TT_BANNER` (`BANNER_ID`, `BANNER_NAME`, `BANNER_URL`, `LINK_URL`, `BANNER_STATE`)
values('1000001','安全保障','https://www.lingtouniao.com/img/banner/aqbz.jpg',
'https://www.lingtouniao.com/h5/insurance.html','1'),
('1000002','活动一','https://www.lingtouniao.com/img/banner/banner1.jpg',
'https://www.lingtouniao.com/h5/banner-intro.html','1');
