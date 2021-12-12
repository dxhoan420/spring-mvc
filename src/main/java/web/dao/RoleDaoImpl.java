package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public List<Role> getRolesByIds(long[] ids) {
        List<Role> roles = new ArrayList<>();
        for (Long id:ids) {
            roles.add(entityManager.find(Role.class, id));
        }
        return roles;
    }
}
