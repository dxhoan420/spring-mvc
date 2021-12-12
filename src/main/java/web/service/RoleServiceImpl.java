package web.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements  RoleService{
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public List<Role> getRolesByIds(long[] ids) {
        return roleDao.getRolesByIds(ids);
    }
}
