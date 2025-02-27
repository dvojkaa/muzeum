package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.mapper.AdminMapper;
import cz.cvut.fel.muzeumSys.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }
}
