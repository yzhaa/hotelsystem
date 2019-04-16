package Storage;
import com.yzh.www.entity.Customer;
import com.yzh.www.entity.User;

public class CustomerStorage implements UserStorage {
    private static Customer customer;
    @Override
    public Customer getUser() {
        return customer;
    }

    @Override
    public void setUser(User customer) {
        CustomerStorage.customer = (Customer) customer;
    }
}
