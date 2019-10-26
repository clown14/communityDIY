package life.majiang.community2.mapper;

import life.majiang.community2.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-09-19 19:10
 **/

@Mapper
public interface UserMapper {
//    @Insert("create into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    @Insert("create into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User dbUser);
}

    