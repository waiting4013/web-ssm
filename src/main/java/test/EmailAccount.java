package test;

import lombok.Data;

/**
 * @author zzz
 */
@Data
public class EmailAccount {

    /**
     * 邮箱用户
     */
    private String username;

    // 邮箱密码
    private String password;

    // 邮箱服务器
    private String place;

    private String to;
}
