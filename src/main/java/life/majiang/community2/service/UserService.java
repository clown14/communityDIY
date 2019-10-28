package life.majiang.community2.service;

import life.majiang.community2.mapper.UserMapper;
import life.majiang.community2.model.User;
import life.majiang.community2.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: community2
 * @author: onion
 * @create: 2019-10-17 23:08
 **/

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
//        User dbUser = userMapper.findByAccountId(user.getAccountId());
//        if (dbUser == null) {
//            //插入
//            user.setGmtCreate(System.currentTimeMillis());
//            user.setGmtModified(user.getGmtCreate());
//            userMapper.insert(user);
//        } else {
//            //更新
//            dbUser.setGmtModified(user.getGmtCreate());
//            dbUser.setAvatarUrl(user.getAvatarUrl());
//            dbUser.setName(user.getName());
//            dbUser.setToken(user.getToken());
//            userMapper.update(dbUser);
//        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);


            User updateUser = new User();
            updateUser.setGmtModified(user.getGmtCreate());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());

            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}

    