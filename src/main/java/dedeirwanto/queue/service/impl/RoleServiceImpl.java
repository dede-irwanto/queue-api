package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.entity.Role;
import dedeirwanto.queue.exception.ResourceNotFoundException;
import dedeirwanto.queue.repository.RoleRepository;
import dedeirwanto.queue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role get(String id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid role!"));
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Invalid role!"));
    }
}
