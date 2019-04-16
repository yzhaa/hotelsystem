package Storage;

import com.yzh.www.entity.User;

/**
 * 用于登入之后，对登陆用户的暂存
 */
public interface UserStorage {
    
    public  User getUser();
    
    public void setUser(User user);
    
}
