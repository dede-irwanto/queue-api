package dedeirwanto.queue.service;

import dedeirwanto.queue.entity.Role;

public interface RoleService {
    Role get(String id);

    Role findByName(String name);
}
