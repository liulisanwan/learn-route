# 1.表结构

```
DROP TABLE IF EXISTS area;

create table area
(
    id       int auto_increment
        primary key,
    province varchar(255) null,
    city     varchar(255) null,
    area     varchar(255) null,
    postcode varchar(255) null,
    del      bit
);

DROP TABLE IF EXISTS `user`;

create table `user`
(
    id           int auto_increment
        primary key,
    `pid`        int          not null,
    `name`       varchar(255) not null,
    `json`       varchar(255) not null,
    `address_id` int          not null,
    `address_id2` int          not null,
    sex          tinyint      not null,
    head_img     varchar(255) not null,
    create_time  datetime not null,
    create_by  int not null,
    update_by  int not null,
    del          bit
);

DROP TABLE IF EXISTS address;

create table address
(
    id      int auto_increment
        primary key,
    user_id int null,
    area_id int null,
    tel     varchar(255) null,
    address varchar(255) null,
    del     bit
);

create table user_dto
(
    id        int auto_increment
        primary key,
    user_id   int not null,
    create_by int not null,
    update_by int not null,
    del       bit null
);
```

# 2.数据

```
-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DELETE FROM area;

INSERT INTO area (id, province, city, area, postcode, del) VALUES (10001, '北京市01', '北京01', '朝阳01', '80001', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10002, '北京市02', '北京02', '朝阳02', '80002', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10003, '北京市03', '北京03', '朝阳03', '80003', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10004, '北京市04', '北京04', '朝阳04', '80004', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10005, '北京市05', '北京05', '朝阳05', '80005', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10006, '北京市06', '北京06', '朝阳06', '80006', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10007, '北京市07', '北京07', '朝阳07', '80007', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10008, '北京市08', '北京08', '朝阳08', '80008', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10009, '北京市09', '北京09', '朝阳09', '80009', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10010, '北京市10', '北京10', '朝阳10', '80010', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10011, '北京市11', '北京11', '朝阳11', '80011', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10012, '北京市12', '北京12', '朝阳12', '80012', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10013, '北京市13', '北京13', '朝阳13', '80013', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10014, '北京市14', '北京14', '朝阳14', '80014', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10015, '北京市15', '北京15', '朝阳15', '80015', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10016, '北京市16', '北京16', '朝阳16', '80016', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10017, '北京市17', '北京17', '朝阳17', '80017', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10018, '北京市18', '北京18', '朝阳18', '80018', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10019, '北京市19', '北京19', '朝阳19', '80019', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10020, '北京市20', '北京20', '朝阳20', '80020', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10021, '北京市21', '北京21', '朝阳21', '80021', false);
INSERT INTO area (id, province, city, area, postcode, del) VALUES (10022, '北京市22', '北京22', '朝阳22', '80022', false);

DELETE FROM `user`;

INSERT INTO `user` (id, pid, `name`, `json`, `address_id`, address_id2 , sex, head_img, create_time, create_by, update_by, del) VALUES
( 1, 1, '张三 1', '{"id": 1,"name":"张三 1"}', 1, 2, 1, 'https://url-01', '2022-01-01 12:00:00', 1, 2, false),
( 2, 1, '张三 2', '{"id": 2,"name":"张三 2"}', 1, 2, 0, 'https://url-02', '2022-01-01 12:00:00', 2, 3, false),
( 3, 1, '张三 3', '{"id": 3,"name":"张三 3"}', 1, 2, 0, 'https://url-03', '2022-01-01 12:00:00', 3, 2, false),
( 4, 1, '张三 4', '{"id": 4,"name":"张三 4"}', 1, 2, 0, 'https://url-04', '2022-01-01 12:00:00', 9, 2, false),
( 5, 1, '张三 5', '{"id": 5,"name":"张三 5"}', 1, 2, 0, 'https://url-05', '2022-01-01 12:00:00', 1, 2, false),
( 6, 1, '张三 6', '{"id": 6,"name":"张三 6"}', 1, 2, 0, 'https://url-06', '2022-01-01 12:00:00', 1, 2, false),
( 7, 1, '张三 7', '{"id": 7,"name":"张三 7"}', 1, 2, 0, 'https://url-07', '2022-01-01 12:00:00', 1, 2, false),
( 8, 1, '张三 8', '{"id": 8,"name":"张三 8"}', 1, 2, 0, 'https://url-08', '2022-01-01 12:00:00', 1, 2, false),
( 9, 1, '张三 9', '{"id": 9,"name":"张三 9"}', 1, 2, 0, 'https://url-09', '2022-01-01 12:00:00', 1, 2, false),
(10, 1, '张三10', '{"id":10,"name":"张三10"}', 1, 2, 0, 'https://url-10', '2022-01-01 12:00:00', 1, 2, true ),
(11, 1, '张三11', '{"id":11,"name":"张三11"}', 1, 2, 0, 'https://url-11', '2022-01-01 12:00:00', 1, 2, true ),
(12, 1, '张三12', '{"id":12,"name":"张三12"}', 1, 2, 0, 'https://url-12', '2022-01-01 12:00:00', 1, 2, true ),
(13, 1, '张三13', '{"id":13,"name":"张三13"}', 1, 2, 0, 'https://url-13', '2022-01-01 12:00:00', 1, 2, true ),
(14, 2, '张三14', '{"id":14,"name":"张三14"}', 1, 2, 0, 'https://url-14', '2022-01-01 12:00:00', 1, 2, false),
(15, 2, '张三15', '{"id":15,"name":"张三15"}', 1, 2, 0, 'https://url-15', '2022-01-01 12:00:00', 1, 2, false),
(16, 2, '张三16', '{"id":16,"name":"张三16"}', 1, 2, 0, 'https://url-16', '2022-01-01 12:00:00', 1, 2, false),
(17, 2, '张三17', '{"id":17,"name":"张三17"}', 1, 2, 0, 'https://url-17', '2022-01-01 12:00:00', 1, 2, false),
(18, 2, '张三18', '{"id":18,"name":"张三18"}', 1, 2, 0, 'https://url-18', '2022-01-01 12:00:00', 1, 2, false),
(19, 2, '张三19', '{"id":19,"name":"张三19"}', 1, 2, 0, 'https://url-19', '2022-01-01 12:00:00', 1, 2, true ),
(20, 2, '张三20', '{"id":20,"name":"张三20"}', 1, 2, 0, 'https://url-20', '2022-01-01 12:00:00', 1, 2, true ),
(21, 2, '张三21', '{"id":21,"name":"张三21"}', 1, 2, 0, 'https://url-21', '2022-01-01 12:00:00', 1, 2, true ),
(22, 2, '张三22', '{"id":22,"name":"张三22"}', 1, 2, 0, 'https://url-22', '2022-01-01 12:00:00', 1, 2, true );


DELETE FROM address;

INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 1, 1, 10001, '10000000001', '朝阳01', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 2, 1, 10002, '10000000002', '朝阳02', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 3, 1, 10003, '10000000003', '朝阳03', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 4, 1, 10004, '10000000004', '朝阳04', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 5, 1, 10005, '10000000005', '朝阳05', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 6, 1, 10006, '10000000006', '朝阳06', true );
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 7, 1, 10007, '10000000007', '朝阳07', true );
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 8, 1, 10008, '10000000008', '朝阳08', true );
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES ( 9, 1, 10009, '10000000009', '朝阳09', true );
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (10,10, 10010, '10000000010', '朝阳10', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (11,11, 10011, '10000000011', '朝阳11', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (12,12, 10012, '10000000012', '朝阳12', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (13,13, 10013, '10000000013', '朝阳13', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (14,14, 10014, '10000000014', '朝阳14', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (15,15, 10015, '10000000015', '朝阳15', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (16,16, 10016, '10000000016', '朝阳16', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (17,17, 10017, '10000000017', '朝阳17', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (18,18, 10018, '10000000018', '朝阳18', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (19,19, 10019, '10000000019', '朝阳19', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (20,20, 10020, '10000000020', '朝阳20', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (21,21, 10021, '10000000021', '朝阳21', false);
INSERT INTO address (id, user_id, area_id, tel, address, del) VALUES (22,22, 10022, '10000000022', '朝阳22', false);

DELETE FROM user_dto;
INSERT INTO user_dto (id, user_id, create_by, update_by, del) VALUES
(1,1, 2, 3, false),
(2,1, 2, 3, false),
(3,1, 2, 3, false),
(4,1, 2, 3, false),
(5,1, 2, 3, false),
(6,1, 2, 3, false),
(7,1, 2, 3, false),
(8,1, 2, 3, false);
```

# 3.lambda表达式查询

## 3.1.基本数据查询

```
ThreadLocalUtils.set("SELECT t.id\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address t1 ON (t1.user_id = t.id)\n" +
                "         LEFT JOIN area t2 ON (t2.id = t1.area_id)\n" +
                "WHERE t.del = false\n" +
                "  AND t1.del = false\n" +
                "  AND t2.del = false");
        //基本数据类型 和 String
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .select(UserDO::getId)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId);
        List<Integer> list = userMapper.selectJoinList(Integer.class, wrapper);

        assert list.get(0) != null;
        System.out.println(list);


        ThreadLocalUtils.set("SELECT t.create_time\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address t1 ON (t1.user_id = t.id)\n" +
                "         LEFT JOIN area t2 ON (t2.id = t1.area_id)\n" +
                "WHERE t.del = false\n" +
                "  AND t1.del = false\n" +
                "  AND t2.del = false");
        //java.sql包下的类
        MPJLambdaWrapper<UserDO> wrapper1 = new MPJLambdaWrapper<UserDO>()
                .select(UserDO::getCreateTime)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId);
        List<Timestamp> list1 = userMapper.selectJoinList(Timestamp.class, wrapper1);

        assert list1.get(0) != null;
        System.out.println(list);
```

## 3.2.多属性设置查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by\n" +
                "FROM `user` t\n" +
                "WHERE t.id = ?\n" +
                "  AND t.del = false\n" +
                "  AND (t.id <= ?)\n" +
                "ORDER BY t.id ASC, t.name ASC");
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .selectAll(UserDO.class)
                .setEntity(new UserDO() {{
                    setId(1);
                }})
                .le(UserDO::getId, 100)
                .orderByAsc(UserDO::getId, UserDO::getName);

        List<UserDO> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
```

## 3.3.别名与多条件判断查询

```
ThreadLocalUtils.set("SELECT tt.id,\n" +
                "       tt.user_id,\n" +
                "       tt.create_by,\n" +
                "       tt.update_by,\n" +
                "       ua.`name` AS userName,\n" +
                "       ub.`name` AS createName,\n" +
                "       uc.`name` AS updateName\n" +
                "FROM user_dto tt\n" +
                "         LEFT JOIN `user` ua ON (ua.id = tt.user_id)\n" +
                "         LEFT JOIN `user` ub ON (ub.id = tt.create_by)\n" +
                "         LEFT JOIN `user` uc ON (uc.id = tt.update_by)\n" +
                "WHERE ua.del = false\n" +
                "  AND ub.del = false\n" +
                "  AND uc.del = false\n" +
                "  AND (ua.id <= ? AND ub.id >= ?)");
        MPJLambdaWrapper<UserDto> wrapper = new MPJLambdaWrapper<UserDto>("tt")
                .selectAll(UserDto.class)
                .leftJoin(UserDO.class, "ua", UserDO::getId, UserDto::getUserId, ext -> ext
                        .selectAs(UserDO::getName, UserDto::getUserName)
                        .le(UserDO::getId, 100))
                .leftJoin(UserDO.class, "ub", UserDO::getId, UserDto::getCreateBy, ext -> ext
                        .selectAs(UserDO::getName, UserDto::getCreateName)
                        .ge(UserDO::getId, 0))
                .leftJoin(UserDO.class, "uc", UserDO::getId, UserDto::getUpdateBy, ext -> ext
                        .selectAs(UserDO::getName, UserDto::getUpdateName));
        List<UserDto> userDtos = userDTOMapper.selectJoinList(UserDto.class, wrapper);
        assert StringUtils.isNotBlank(userDtos.get(0).getUserName());
        assert StringUtils.isNotBlank(userDtos.get(0).getCreateName());
        assert StringUtils.isNotBlank(userDtos.get(0).getUpdateName());
```

## 3.4.多表连接内嵌多条件查询

```
ThreadLocalUtils.set("SELECT tt.id,\n" +
                "       tt.pid,\n" +
                "       tt.`name`,\n" +
                "       tt.`json`,\n" +
                "       tt.sex,\n" +
                "       tt.head_img,\n" +
                "       tt.create_time,\n" +
                "       tt.address_id,\n" +
                "       tt.address_id2,\n" +
                "       tt.del,\n" +
                "       tt.create_by,\n" +
                "       tt.update_by,\n" +
                "       ua.id,\n" +
                "       ub.head_img\n" +
                "FROM `user` tt\n" +
                "         LEFT JOIN `user` ua ON (ua.id = tt.pid)\n" +
                "         LEFT JOIN `user` ub ON (ub.id = tt.create_by)\n" +
                "         LEFT JOIN `user` uc ON (uc.id = tt.update_by)\n" +
                "WHERE tt.del = false\n" +
                "  AND ua.del = false\n" +
                "  AND ub.del = false\n" +
                "  AND uc.del = false\n" +
                "  AND (ua.head_img = tt.name AND tt.id = ua.id)");
        MPJLambdaWrapper<UserDO> w = new MPJLambdaWrapper<UserDO>("tt")
                .selectAll(UserDO.class)
                .leftJoin(UserDO.class, "ua", UserDO::getId, UserDO::getPid, ext -> ext
                        .select(UserDO::getId)
                        .eq(UserDO::getImg, UserDO::getName))
                .leftJoin(UserDO.class, "ub", UserDO::getId, UserDO::getCreateBy, ext -> ext
                        .select(UserDO::getImg))
                .leftJoin(UserDO.class, "uc", UserDO::getId, UserDO::getUpdateBy)
                .eq(UserDO::getId, UserDO::getId);
        userMapper.selectJoinList(UserDO.class, w);
        System.out.println(1);
```

## 3.5.自连接查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.id          AS joina_id,\n" +
                "       t1.pid         AS joina_pid,\n" +
                "       t1.`name`      AS joina_name,\n" +
                "       t1.`json`      AS joina_json,\n" +
                "       t1.sex         AS joina_sex,\n" +
                "       t1.head_img    AS joina_head_img,\n" +
                "       t1.create_time AS joina_create_time,\n" +
                "       t1.address_id  AS joina_address_id,\n" +
                "       t1.address_id2 AS joina_address_id2,\n" +
                "       t1.del         AS joina_del,\n" +
                "       t1.create_by   AS joina_create_by,\n" +
                "       t1.update_by   AS joina_update_by\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN `user` t1 ON (t1.pid = t.id)\n" +
                "WHERE t.del = false\n" +
                "  AND (t.id > ?)");
        //自连接
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .disableSubLogicDel()//关闭副表逻辑删除
                .selectAll(UserDO.class)
                .selectCollection(UserDO.class, UserDO::getChildren)
                .leftJoin(UserDO.class, UserDO::getPid, UserDO::getId)
                .gt(UserDO::getId, 0);
        List<UserDO> list = userMapper.selectJoinList(UserDO.class, wrapper);
        System.out.println(list);
```

## 3.6.多次自连接查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.`name` AS createName,\n" +
                "       t2.`name` AS updateName\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN `user` t1 ON (t1.id = t.create_by)\n" +
                "         LEFT JOIN `user` t2 ON (t2.id = t.update_by)\n" +
                "WHERE (t2.id = t.update_by AND t.id = t1.id)");
        //关联一张表多次
        MPJLambdaWrapper<UserDO> w = new MPJLambdaWrapper<UserDO>()
                .disableLogicDel()
                .disableSubLogicDel()
                .selectAll(UserDO.class)
                .leftJoin(UserDO.class, UserDO::getId, UserDO::getCreateBy, ext -> ext
                        .selectAs(UserDO::getName, UserDO::getCreateName))
                .leftJoin(UserDO.class, (on, ext) -> {
                    on.eq(UserDO::getId, UserDO::getUpdateBy);
                    ext.selectAs(UserDO::getName, UserDO::getUpdateName)
                            .eq(UserDO::getId, UserDO::getUpdateBy);
                })
                .eq(UserDO::getId, UserDO::getId);
        List<UserDO> dos = userMapper.selectJoinList(UserDO.class, w);
        assert dos.get(0).getCreateName() != null && dos.get(0).getUpdateName() != null;
```

## 3.7.自连接与集合查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.`name`      AS alias,\n" +
                "       t1.id          AS joina_id,\n" +
                "       t1.pid         AS joina_pid,\n" +
                "       t1.`name`      AS joina_name,\n" +
                "       t1.`json`      AS joina_json,\n" +
                "       t1.sex         AS joina_sex,\n" +
                "       t1.head_img    AS joina_head_img,\n" +
                "       t1.create_time AS joina_create_time,\n" +
                "       t1.address_id  AS joina_address_id,\n" +
                "       t1.address_id2 AS joina_address_id2,\n" +
                "       t1.del         AS joina_del,\n" +
                "       t1.create_by   AS joina_create_by,\n" +
                "       t1.update_by   AS joina_update_by,\n" +
                "       t2.id          AS joinb_id,\n" +
                "       t2.pid         AS joinb_pid,\n" +
                "       t2.`name`      AS joinb_name,\n" +
                "       t2.`json`      AS joinb_json,\n" +
                "       t2.sex         AS joinb_sex,\n" +
                "       t2.head_img    AS joinb_head_img,\n" +
                "       t2.create_time AS joinb_create_time,\n" +
                "       t2.address_id  AS joinb_address_id,\n" +
                "       t2.address_id2 AS joinb_address_id2,\n" +
                "       t2.del         AS joinb_del,\n" +
                "       t2.create_by   AS joinb_create_by,\n" +
                "       t2.update_by   AS joinb_update_by\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN `user` t1 ON (t1.pid = t.id)\n" +
                "         LEFT JOIN `user` t2 ON (t2.pid = t1.id)\n" +
                "WHERE t.del = false\n" +
                "  AND (t1.id <= ? AND t.id <= ?)");
        MPJLambdaWrapper<UserDO> wrapper1 = new MPJLambdaWrapper<UserDO>()
                .disableSubLogicDel()
                .selectAll(UserDO.class)
                .selectCollection("t1", UserDO.class, UserDO::getChildren, c -> c
                        .collection("t2", UserDO.class, UserDO::getChildren))
                .leftJoin(UserDO.class, UserDO::getPid, UserDO::getId, ext -> ext
                        .selectAs(UserDO::getName, UserDO::getAlias)
                        .leftJoin(UserDO.class, UserDO::getPid, UserDO::getId)
                        .le(UserDO::getId, 5))
                .le(UserDO::getId, 4);
        List<UserDO> list1 = userMapper.selectJoinList(UserDO.class, wrapper1);
        System.out.println(list1);
```

## 3.7.别名测试

```
        ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       aa.id,\n" +
                "       aa.user_id,\n" +
                "       aa.area_id,\n" +
                "       aa.tel,\n" +
                "       aa.address,\n" +
                "       aa.del\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address aa ON (aa.user_id = t.id)\n" +
                "WHERE t.del = false\n" +
                "  AND aa.del = false");
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
//                .disableLogicDel()//关闭主表逻辑删除
                .selectAll(UserDO.class)
                .selectAll(AddressDO.class, "aa")
//                .selectCollection(UserDO.class, UserDO::getChildren)
                .leftJoin(AddressDO.class, "aa", AddressDO::getUserId, UserDO::getId);
        List<UserDO> list = userMapper.selectJoinList(UserDO.class, wrapper);
```

## 3.8.自识别别名查询

```
SELECT
	t.id,
	t.pid,
	t.`name`,
	t.`json`,
	t.sex,
	t.head_img,
	t.create_time,
	t.address_id,
	t.address_id2,
	t.del,
	t.create_by,
	t.update_by,
	t1.id AS joina_id,
	t1.user_id,
	t1.area_id,
	t1.tel,
	t1.address,
	t1.del AS joina_del,
	t2.id AS joinb_id,
	t2.user_id AS joina_user_id,
	t2.area_id AS joina_area_id,
	t2.tel AS joina_tel,
	t2.address AS joina_address,
	t2.del AS joinb_del 
FROM
	`user` t
	LEFT JOIN address t1 ON ( t1.id = t.address_id )
	LEFT JOIN address t2 ON ( t2.id = t.address_id2 ) 
WHERE
	t.del = FALSE

MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .disableSubLogicDel()
                .selectAll(UserDO.class)
                .selectCollection("t1", AddressDO.class, UserDO::getAddressList)
                .selectCollection("t2", AddressDO.class, UserDO::getAddressList2)
                .leftJoin(AddressDO.class, AddressDO::getId, UserDO::getAddressId)
                .leftJoin(AddressDO.class, AddressDO::getId, UserDO::getAddressId2);
        List<UserDO> list = userMapper.selectJoinList(UserDO.class, wrapper);

        assert list.get(0).getAddressList().get(0).getAddress() != null;
        assert list.get(0).getAddressList2().get(0).getAddress() != null;
        System.out.println(list);

```

## 3.9.简单分页查询

```
Page<UserDTO> page = new Page<>(1, 10);
        page.setSearchCount(false);
        page = userMapper.selectJoinPage(page, UserDTO.class,
                MPJWrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(AddressDO::getAddress)
                        .select(AreaDO::getProvince)
                        .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                        .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId));
        page.getRecords().forEach(System.out::println);
```

## 3.10.on上多条件分页查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.address\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address t1 ON (t.id = t1.user_id AND t.id = t1.user_id)\n" +
                "WHERE t.del = false\n" +
                "  AND t1.del = false\n" +
                "  AND (t.id = ? AND (t.head_img = ? OR t1.user_id = ?) AND t.id = ?)\n" +
                "LIMIT ?");
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class,
                MPJWrappers.<UserDO>lambdaJoin()
                        .selectAll(UserDO.class)
                        .select(AddressDO::getAddress)
                        .leftJoin(AddressDO.class, on -> on
                                .eq(UserDO::getId, AddressDO::getUserId)
                                .eq(UserDO::getId, AddressDO::getUserId))
                        .eq(UserDO::getId, 1)
                        .and(i -> i.eq(UserDO::getImg, "er")
                                .or()
                                .eq(AddressDO::getUserId, 1))
                        .eq(UserDO::getId, 1));
        page.getRecords().forEach(System.out::println);
```

## 3.11.函数查询

```
SELECT
	SUM( t.id ) AS id,
	MAX( t.id ) AS headImg 
FROM
	`user` t
	LEFT JOIN address t1 ON ( t1.user_id = t.id ) 
WHERE
	t.del = FALSE 
	AND t1.del = FALSE

 UserDTO one = userMapper.selectJoinOne(UserDTO.class, MPJWrappers.<UserDO>lambdaJoin()
                .selectSum(UserDO::getId)
                .selectMax(UserDO::getId, UserDTO::getHeadImg)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId));
        System.out.println(one);
```

## 3.11.忽略个别字段查询

```
SELECT
	t.id,
	t.pid,
	t.`name`,
	t.`json`,
	t.sex,
	t.head_img,
	t.create_time,
	t.address_id,
	t.address_id2,
	t.del,
	t.create_by,
	t.update_by,
	t1.user_id,
	t1.area_id,
	t1.tel,
	t1.address,
	t1.del 
FROM
	`user` t
	LEFT JOIN address t1 ON ( t1.user_id = t.id ) 
WHERE
	t.del = FALSE 
	AND t1.del = FALSE 
	AND ( t.id = 1 ) 
	LIMIT 10
    MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
    .selectAll(UserDO.class)
    .select(AddressDO.class, p -> true)
    .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
    .eq(UserDO::getId, 1);
    Page<UserDTO> page = userMapper.selectJoinPage(new Page<>(1, 10), UserDTO.class, wrapper);
    assert page.getRecords().get(0).getAddress() != null;
    page.getRecords().forEach(System.out::println);
```

## 3.12.关联查询返回map集合

```
 List<Map<String, Object>> list = userMapper.selectJoinMaps(MPJWrappers.<UserDO>lambdaJoin()
                .selectAll(UserDO.class)
                .select(AddressDO::getAddress)
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId));
        assert list.get(0).get("ADDRESS") != null;
        list.forEach(System.out::println);
```

## 3.13.多表返回dto对象查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.id  AS joina_id,\n" +
                "       t1.user_id,\n" +
                "       t1.area_id,\n" +
                "       t1.tel,\n" +
                "       t1.address,\n" +
                "       t1.del AS joina_del,\n" +
                "       t2.id  AS joinb_id,\n" +
                "       t2.province,\n" +
                "       t2.city,\n" +
                "       t2.area,\n" +
                "       t2.postcode,\n" +
                "       t2.del AS joinb_del\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address t1 ON (t1.user_id = t.id)\n" +
                "         LEFT JOIN area t2 ON (t2.id = t1.area_id)\n" +
                "WHERE t.del = false\n" +
                "  AND t1.del = false\n" +
                "  AND t2.del = false\n" +
                "  AND (t.id <= ?)\n" +
                "ORDER BY t.id DESC");

        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .selectAll(UserDO.class)
                .selectCollection(AddressDO.class, UserDTO::getAddressList, addr -> addr
                        .association(AreaDO.class, AddressDTO::getArea))
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId)
                .le(UserDO::getId, 10000)
                .orderByDesc(UserDO::getId);
        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class, wrapper);

        assert list.get(0).getAddressList() != null && list.get(0).getAddressList().get(0).getId() != null;
        list.forEach(System.out::println);
```

## 3.12.多表集合对象查询

```
ThreadLocalUtils.set("SELECT t.id,\n" +
                "       t.pid,\n" +
                "       t.`name`,\n" +
                "       t.`json`,\n" +
                "       t.sex,\n" +
                "       t.head_img,\n" +
                "       t.create_time,\n" +
                "       t.address_id,\n" +
                "       t.address_id2,\n" +
                "       t.del,\n" +
                "       t.create_by,\n" +
                "       t.update_by,\n" +
                "       t1.id  AS joina_id,\n" +
                "       t1.user_id,\n" +
                "       t1.area_id,\n" +
                "       t1.tel,\n" +
                "       t1.address,\n" +
                "       t1.del AS joina_del,\n" +
                "       t2.id  AS joinb_id,\n" +
                "       t2.province,\n" +
                "       t2.city,\n" +
                "       t2.area,\n" +
                "       t2.postcode,\n" +
                "       t2.del AS joinb_del\n" +
                "FROM `user` t\n" +
                "         LEFT JOIN address t1 ON (t1.user_id = t.id)\n" +
                "         LEFT JOIN area t2 ON (t2.id = t1.area_id)\n" +
                "WHERE t.del = false\n" +
                "  AND t1.del = false\n" +
                "  AND t2.del = false\n" +
                "ORDER BY t.id DESC");
        MPJLambdaWrapper<UserDO> wrapper = new MPJLambdaWrapper<UserDO>()
                .selectAll(UserDO.class)
                .selectCollection(AddressDO.class, UserDTO::getAddressList, addr -> addr
                        .association(AreaDO.class, AddressDTO::getArea))
                .leftJoin(AddressDO.class, AddressDO::getUserId, UserDO::getId)
                .leftJoin(AreaDO.class, AreaDO::getId, AddressDO::getAreaId)
                .orderByDesc(UserDO::getId);
        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class, wrapper);

        assert list.get(0).getAddressList().get(0).getId() != null;
        list.forEach(System.out::println);
```

