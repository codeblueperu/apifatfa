package com.fatfa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cuentas_bancarias")
public class CuentasBancariasModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta_bancaria", nullable = false)
	private int idCuentaBancaria;
	
	@Column(name = "nombre_cuenta", nullable = false, length = 50)
    private String nombreCuenta;
	
	@Column(name = "descripcion", nullable = true, columnDefinition = "text")
    private String descripcion ;
    
    
    @Column(name = "numero_cuenta",length = 30, nullable = false)
   private String  numeroCuenta;
    
    @Column(name = "cbu",length = 30 , nullable = false)
    private String cbu ;
    
    @Column(name = "estado", nullable = false, columnDefinition = "BIT default 1")
    private boolean estado ;
    
    @OneToOne()
    @JoinColumn(name = "id_banco")
    private BancosModel banco;

	public CuentasBancariasModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentasBancariasModel(int idCuentaBancaria, String nombreCuenta, String descripcion, String numeroCuenta,
			String cbu, boolean estado, BancosModel banco) {
		super();
		this.idCuentaBancaria = idCuentaBancaria;
		this.nombreCuenta = nombreCuenta;
		this.descripcion = descripcion;
		this.numeroCuenta = numeroCuenta;
		this.cbu = cbu;
		this.estado = estado;
		this.banco = banco;
	}

	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public BancosModel getBanco() {
		return banco;
	}

	public void setBanco(BancosModel banco) {
		this.banco = banco;
	}

	@Override
	public String toString() {
		return "CuentasBancariasModel [idCuentaBancaria=" + idCuentaBancaria + ", nombreCuenta=" + nombreCuenta
				+ ", descripcion=" + descripcion + ", numeroCuenta=" + numeroCuenta + ", cbu=" + cbu + ", estado="
				+ estado + ", banco=" + banco + "]";
	}
}
