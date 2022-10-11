## Spring boot seed

### DTO、DO\Entity
<https://www.jdon.com/55804>
1. DTO是用于将后台的数据结构（javaBean）转换为对用户友好的表现方式的数据结构，同时也能防止后台数据直接传送到前台而存在的潜在危险。
2. 可以时候要哪个springbot框架提供的转换器接口： org.springframework.core.convert.converter.Converter，来实现实体类和DTO的转换
3. DTO是合理的分配应该是：一个entity对应两个DTO，  01. list列表页对应的DTO仅仅用于显示， 02新增编辑的页面对应的DTO拥有较为完整的和entity类似的数据结构用于存储前台数据
4. 当前页面需要使用别的entity时， 要首先使用别人的已有的DTO
5. DTO的conveter应该包括2个， DTO - entity ， entity - DTO

### mybatis

##### 动态查询
<https://www.cnblogs.com/zwwhnly/p/11150265.html>

* if
```
List<SysUser> selectByUser(SysUser sysUser);
<select id="selectByUser" resultType="com.zwwhnly.mybatisaction.model.SysUser">
    SELECT  id,
            user_name,
            user_password,
            user_email,
            create_time
    FROM sys_user
    WHERE 1 = 1
    <if test="userName != null and userName != ''">
        AND user_name LIKE CONCAT('%',#{userName},'%')
    </if>
    <if test="userEmail != null and userEmail != ''">
        AND user_email = #{userEmail}
    </if>
</select>

int insertSelective(SysUser sysUser);
<insert id="insertSelective" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO sys_user(user_name, user_password,
    <if test="userEmail != null and userEmail != ''">
        user_email,
    </if>
    user_info, head_img, create_time)
    VALUES (#{userName},#{userPassword},
    <if test="userEmail != null and userEmail != ''">
        #{userEmail},
    </if>
    #{userInfo},#{headImg,jdbcType=BLOB},#{createTime,jdbcType=TIMESTAMP})
</insert>
```

* choose
```
SysUser selectByIdOrUserName(SysUser sysUser);
<select id="selectByIdOrUserName" resultType="com.zwwhnly.mybatisaction.model.SysUser">
    SELECT  id,
            user_name,
            user_password,
            user_email,
            create_time
    FROM sys_user
    WHERE 1 = 1
    <choose>
        <when test="id != null">
            AND id = #{id}
        </when>
        <when test="userName != null and userName != ''">
            AND user_name = #{userName}
        </when>
        <otherwise>
            AND 1 = 2
        </otherwise>
    </choose>
</select>
```

* foreach
```
List<SysUser> selectByIdList(@Param("idList") List<Long> idList);
<select id="selectByIdList" resultType="com.zwwhnly.mybatisaction.model.SysUser">
    SELECT id,
    user_name,
    user_password,
    user_email,
    create_time
    FROM sys_user
    WHERE id IN
    <foreach collection="idList" open="(" close=")" separator=","
             item="id" index="i">
        #{id}
    </foreach>
</select>


```

##### 级联查询
<https://blog.csdn.net/weixin_45920385/article/details/122284405>

* 一对一
```
<!-- public Employee getEmployee(int id); -->

<select id="getEmployee" resultMap="emp2">
	select e.*, d.id did, d.department_name
	from employee e,
		department d
	where e.d_id = d.id
	and e.id = #{id}
</select>

<resultMap id="emp2" type="employee">
	<id column="id" property="id"/>
	<result column="last_name" property="lastName"/>
	<result column="email" property="email"/>
	<result column="gender" property="gender"/>
	<association property="dept" javaType="department">
		<id column="did" property="id"/>
		<result column="department_name" property="departmentName"/>
	</association>
</resultMap>
```

* 一对多
```
<!--   public Department getDepartment(int id); -->
<select id="getDepartment" resultMap="dep1">
	select d.*, e.id eid, e.last_name, e.email, e.gender
	from department d
		left join employee e on d.id = e.d_id
	where d.id = #{id}
</select>
<resultMap id="dep1" type="department">
	<id column="id" property="id"/>
	<result column="department_name" property="departmentName"/>
	<collection property="employees" ofType="employee">
		<id column="eid" property="id"/>
		<result column="last_name" property="lastName"/>
		<result column="email" property="email"/>
		<result column="gender" property="gender"/>
	</collection>
</resultMap>

```