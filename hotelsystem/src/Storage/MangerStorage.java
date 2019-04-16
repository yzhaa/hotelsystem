package Storage;

import com.yzh.www.entity.Manager;
import com.yzh.www.entity.User;

public class MangerStorage implements UserStorage {
    private static Manager manager;

    @Override
    public Manager getUser() {
        return manager;
    }

    @Override
    public void setUser(User manager) {
        MangerStorage.manager = (Manager) manager;
    }
}
