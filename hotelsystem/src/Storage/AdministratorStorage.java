package Storage;

import com.yzh.www.entity.Administrator;
import com.yzh.www.entity.User;

public class AdministratorStorage implements UserStorage {

    private static Administrator administrator;
    @Override
    public Administrator getUser() {
        return administrator;
    }

    @Override
    public void setUser(User user) {
        AdministratorStorage.administrator = (Administrator) user;
    }
}
