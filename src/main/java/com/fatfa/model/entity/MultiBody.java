package com.fatfa.model.entity;

import java.util.List;

public class MultiBody {

	private EmpresasModel datoEmpresa;
	
	private List<DomicilioModel> datosDomicilio;
	
	private ResponsableDDJJModel responsableDDJJ;
	
	private ResponsableRRHHModel responsableRRHH;

	public EmpresasModel getDatoEmpresa() {
		return datoEmpresa;
	}

	public void setDatoEmpresa(EmpresasModel datoEmpresa) {
		this.datoEmpresa = datoEmpresa;
	}

	public List<DomicilioModel> getDatosDomicilio() {
		return datosDomicilio;
	}

	public void setDatosDomicilio(List<DomicilioModel> datosDomicilio) {
		this.datosDomicilio = datosDomicilio;
	}

	public ResponsableDDJJModel getResponsableDDJJ() {
		return responsableDDJJ;
	}

	public void setResponsableDDJJ(ResponsableDDJJModel responsableDDJJ) {
		this.responsableDDJJ = responsableDDJJ;
	}

	public ResponsableRRHHModel getResponsableRRHH() {
		return responsableRRHH;
	}

	public void setResponsableRRHH(ResponsableRRHHModel responsableRRHH) {
		this.responsableRRHH = responsableRRHH;
	}	
}
