CREATE DATABASE dbfatfa;
use dbfatfa;

/*******************************************************************
					CREATE TABLE SQL QUERY
*********************************************************************/
CREATE TABLE tb_perfiles(
id_perfil int not null primary key auto_increment,
descripcion varchar(45) not null,
estado boolean not null default true
);

CREATE TABLE tb_usuario (
id_usuario int not null primary key auto_increment,
email varchar(80) not null unique,
password varchar(200) not null,
nombres_apellidos varchar(70) not null,
estado_cuenta boolean not null default true,
estado_cambio_password boolean not null default true,
fecha_cambio_clave date not null,
estado_acceso boolean not null default true,
id_perfil int not null,
fecha_alta datetime,
password_reset_token  varchar(200) not null,
password_reset_token_date datetime,
fecha_modifica datetime,
id_usuario_crea int not null,
foreign key (id_perfil) references tb_perfiles (id_perfil)
);

/*******************************************************************
					CREATE VIEW SQL QUERY
*********************************************************************/
#DATOS DE LA BOLETA A IMPRIMIR
CREATE OR REPLACE VIEW vw_datos_boleta
AS
SELECT em.nombre_fantasia,em.numero_afiliacion,em.razon_social,em.cuit,b.anio,b.mes,b.codigo_barras,b.fecha_primer_vencimiento,sd.nombre_sindicato,
b.fecha_probable_pago,b.importe_total,b.intereces,bc.id_banco,bc.banco,dt.aporte_art46,dt.aporte_art47,dt.aporte_art48,dt.contribucion_extraordinaria,
ap.nombre_aporte, b.id_boleta,b.id_empresa,esp.nombre as estado_pago, (SELECT SUM(sueldo) FROM tb_nominas nm where nm.id_empresa = b.id_empresa and anio = b.anio and mes = b.mes) as total_de_sueldos,
now() as fecha_generacion
FROM tb_boleta b, tb_empresas em, tb_detalle_concepto_boleta dt,
tb_bancos bc, tb_estado_pago esp, tb_aporte_sindical ap, tb_sindicatos sd
WHERE b.id_empresa = em.id_empresa and dt.id_detalle_aporte = dt.id_detalle_aporte
and b.id_banco = bc.id_banco and b.id_estado_pago = esp.id_estado_pago and b.id_aporte = ap.id_aporte
AND em.id_sindicato = sd.id_sindicato;

/*******************************************************************
					CREATE FUNCTION SQL QUERY
*********************************************************************/
#OBTENER COEFICIENTE ANTIGUEDAD
DELIMITER //
CREATE  FUNCTION calcular_coeficiente_antiguedad(_idNomina int, _idempresa int, _anio varchar(4), _mes varchar(4), _sueldo DECIMAL(18,2)) RETURNS DECIMAL(18,2)
BEGIN
  DECLARE calculo  DECIMAL(18,2) DEFAULT 0;
  DECLARE coeficiente_antiguedad  DECIMAL(18,2) DEFAULT 0;
  DECLARE antiguedad int DEFAULT 0;
  DECLARE porcentajeAntiguedad int DEFAULT 0;
  declare fechaBajaTrabajador Date;
  
  #OBTENER FECHA BAJA
   SELECT fechabaja INTO fechaBajaTrabajador FROM tb_nominas WHERE id_nomina = _idNomina and id_empresa = _idempresa and anio = _anio and mes = _mes;
  #BUSCAR ANTIGUEDAD DEL TRABAJADOR 1 2 3
  IF fechaBajaTrabajador IS NULL THEN
	SELECT TIMESTAMPDIFF(YEAR, fechaingreso, now()) INTO antiguedad FROM tb_nominas WHERE id_nomina = _idNomina and id_empresa = _idempresa and anio = _anio and mes = _mes;
  ELSE
	SELECT TIMESTAMPDIFF(YEAR, fechaingreso, fechaBajaTrabajador) INTO antiguedad FROM tb_nominas WHERE id_nomina = _idNomina and id_empresa = _idempresa and anio = _anio and mes = _mes;
  END IF;
    
  #OBTENER EL PORCENTAJE SEGUN SU ANTIGUEDAD
  SELECT porcentaje INTO porcentajeAntiguedad  FROM tb_escala_tiempo_servicio WHERE tiempoServicio = antiguedad;
  
  SET coeficiente_antiguedad = _sueldo * porcentajeAntiguedad / 100;
  set calculo = _sueldo + coeficiente_antiguedad;
  
  RETURN calculo;
END
//

#select calcular_coeficiente_antiguedad(1,3,'2023','05',200000.00) from dual



SELECT TIMESTAMPDIFF(day, "2022-09-01", "2023-06-16") 