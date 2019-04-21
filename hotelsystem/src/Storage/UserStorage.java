package Storage;

import com.yzh.www.entity.User;

/**
 * 用于登入之后，对登陆用户的暂存
 */
public interface UserStorage {

    User getUser();

    void setUser(User user);
    
}
