package cz.muni.fi.pa165.project.web.utils;

import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.beans.PropertyEditorSupport;

/**
 * @author Kudolani | mariok@mail.muni.cz | 25.12.2015
 */
@Component
public class VehicleDTOEditor extends PropertyEditorSupport {

	@Inject
	private VehicleFacade vehicleFacade;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text == null || text.equals("")){
			throw new IllegalArgumentException("invalid vehicle");
		}

		VehicleDTO vehicleDTO = this.vehicleFacade.getById(Long.valueOf(text));
		this.setValue(vehicleDTO);
	}
}
