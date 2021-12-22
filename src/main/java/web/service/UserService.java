package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

@Transactional
public interface UserService extends UserDetailsService {

    @Transactional(readOnly = true)
    List<User> allUsers();

    @Transactional
    void save(User user, Set<Role> roles);

    @Transactional
    void delete(Long id);

    @Transactional(readOnly = true)
    User getById(Long id);

    @Transactional
    void edit(User user, List<String> role);

}
