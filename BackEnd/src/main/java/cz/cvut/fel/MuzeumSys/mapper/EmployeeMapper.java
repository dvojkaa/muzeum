package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.MuzeumSys.bo.EmployeeBO;
import cz.cvut.fel.MuzeumSys.dto.EmployeeDTO;
import cz.cvut.fel.MuzeumSys.model.Employee;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Employee}, {@link EmployeeBO} a {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeBO employeeToEmployeeBO(Employee employee);

    EmployeeDTO employeeBoToEmployeeDto(EmployeeBO employeeBO);

    EmployeeBO employeeDtoToEmployeeBo(EmployeeDTO employeeDTO);

    Employee employeeBoToEmployee(EmployeeBO employeeBO);
}
