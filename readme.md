## Spring boot seed

### mybatis

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