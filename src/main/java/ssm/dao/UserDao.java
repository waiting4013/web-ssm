package ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import ssm.model.User;

import java.util.List;


@Component
public interface UserDao {
    User selectUserById(@Param("userId") Long userId);

    User selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    List<User> selectAllUser();
}
