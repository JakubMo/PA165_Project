package cz.muni.fi.pa165.project.web.utils;

import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.beans.PropertyEditorSupport;

/**
 * @author Kudolani | mariok@mail.muni.cz | 25.12.2015
 */
@Component
public class EmployeeDTOEditor extends PropertyEditorSupport {

	@Inject
	private EmployeeFacade employeeFacade;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		EmployeeDTO employeeDTO = this.employeeFacade.getById(Long.valueOf(text));

		this.setValue(employeeDTO);
	}
}
