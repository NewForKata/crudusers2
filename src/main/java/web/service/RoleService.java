package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
public interface RoleService {

    @Transactional
    void save(Role role);

    @Transactional
    void delete(Role role);

    @Transactional(readOnly = true)
    Role getById(Long id);

    @Transactional(readOnly = true)
    Role getRoleByName(String rolename);

    @Transactional
    Role createRoleIfNotFound(String name, long id);

    @Transactional
    Set<Role> getRolesForPage();

    @Transactional
    Set<Role> getByIds(List<String> roles);

}
