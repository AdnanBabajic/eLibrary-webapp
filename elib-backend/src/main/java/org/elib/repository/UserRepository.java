package org.elib.repository;

import org.elib.repository.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class UserRepository extends Repository<User, Integer>{

    public UserRepository() {
        super(User.class);
    }

    public Boolean checkExisting(User user) {
        List<User> userList = findAll();
        if(userList.isEmpty() || userList == null) return false;

        for (User existingUser:
             userList) {
            if(existingUser.getName().contentEquals(user.getName())) {
                return true;
            }
        }

        return false;
    }
}
