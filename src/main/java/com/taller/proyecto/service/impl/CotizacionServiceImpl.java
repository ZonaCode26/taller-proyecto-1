package com.taller.proyecto.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.proyecto.MsTallerProyectoApplication;
import com.taller.proyecto.exception.ModeloNotFoundException;
import com.taller.proyecto.model.Archivo;
import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.DetalleCotizacion;
import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.model.api.ReequestCreateCotizacion;
import com.taller.proyecto.model.dto.ReporteDto;
import com.taller.proyecto.repository.IArchivoRepo;
import com.taller.proyecto.repository.ICotizacionRepo;
import com.taller.proyecto.repository.IDetalleCotizacionRepo;
import com.taller.proyecto.repository.IProductoRepo;
import com.taller.proyecto.security.GlobalUsuarioSession;
import com.taller.proyecto.service.ICotizacionService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CotizacionServiceImpl implements ICotizacionService{
	
	@Value("${empresa.razonsocial}")
	private String razonsocial;
	
	@Value("${empresa.ruc}")
	private String ruc;
	
	@Value("${empresa.email}")
	private String email;
	
	@Autowired
	private ICotizacionRepo repo;	
	
	@Autowired
	private IProductoRepo repoProd;	
	
	@Autowired
	private IArchivoRepo repoArch;	
	
	@Autowired
	private IDetalleCotizacionRepo repoDetCot;	

	
	@Override
	public Cotizacion findFirstById(Integer id) {
		// TODO Auto-generated method stub
		Cotizacion entity =  repo.findFirstById(id);
		
		if (entity==null) {
			throw new ModeloNotFoundException(String.format("Usuario no existe", id ));
		}
		entity.getUsuario().setPassword(null);
		entity.getUsuario().setEmail(null);		
		return entity;
	}

	@Override
	public List<Cotizacion> findAll() {
		List<Cotizacion> response = repo.findAll();
		
		
		response.forEach(x->{
			x.getUsuario().setPassword(null);
			x.getUsuario().setEmail(null);
		});
		
		 
		 return response;
	}

	@Transactional // (propagation = )
	@Override
	public Cotizacion createCotizacion(ReequestCreateCotizacion createCotizacion, GlobalUsuarioSession globalSession) {

		String codCotizacion = "COT-"+globalSession.getUserId()+ RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		codCotizacion =codCotizacion.substring(0,20);
		
		
		
		
		Cotizacion cotizacion = Cotizacion.builder().estado("ACTIVO").fechaRegistro(LocalDate.now())
				.ruc(globalSession.getRuc()).usuario(Usuario.builder().idUsuario(globalSession.getUserId()).build())
				.codCotizacion(codCotizacion)
				.build();

		repo.save(cotizacion);

		List<Integer> listIds = new ArrayList<>();

		HashMap<Integer, Integer> datosParam = new HashMap<>();

		createCotizacion.getProductos().forEach(x -> {
			datosParam.put(x.getId(), x.getCantidad());
			listIds.add(x.getId());
		});

		List<DetalleCotizacion> listCotizacions = new ArrayList<>();

		List<Producto> getListProd = repoProd.findAllById(listIds);
		for (Producto producto : getListProd) {

			
			listCotizacions.add(DetalleCotizacion.builder()
					.cantidad(datosParam.get(producto.getId()))
					.precioUnidad(producto.getPrecio())
					.producto(producto)
					.precioTotal(datosParam.get(producto.getId()) * producto.getPrecio())
					.cotizacion(cotizacion)
					.fechaRegistro(LocalDate.now()).build());

		}

		repoDetCot.saveAll(listCotizacions);

		cotizacion.getUsuario().setPassword(null);
		cotizacion.getUsuario().setEmail(null);

		return cotizacion;
	}

	@Override
	public Cotizacion findFirstByCode(String code) {
		Cotizacion entity =  repo.findFirstByCodCotizacion(code);
		
		if (entity==null) {
			throw new ModeloNotFoundException(String.format("Cotizacion no existe", code ));
		}
		entity.getUsuario().setPassword(null);
		entity.getUsuario().setEmail(null);		
		return entity;
	}

	@Override
	public byte[] generarPdfCotizacion(Integer id, GlobalUsuarioSession globalSession) {
		byte[] data = null;
		
		Cotizacion cotizacion = repo.findFirstById(id);
		List<ReporteDto> reporteList =  new ArrayList<ReporteDto>();
		
		double total_precio = 0;
//		cotizacion.getDetalleConsulta().forEach(x->{
//			ReporteDto item = new ReporteDto();
//			item.setId(x.getId());
//			item.setCantidad(x.getCantidad());
//			item.setDescripcion(x.getProducto().getDescripcion());
//			item.setNombre(x.getProducto().getNombre());
//			item.setPrecio(x.getProducto().getPrecio());
//			item.setPreciototal( item.getCantidad() *item.getPrecio());
//			reporteList.add(item);
//
//		});
		
		for (DetalleCotizacion x : cotizacion.getDetalleConsulta()) {
			ReporteDto item = new ReporteDto();
			item.setId(x.getId());
			item.setCantidad(x.getCantidad());
			item.setDescripcion(x.getProducto().getDescripcion());
			item.setNombre(x.getProducto().getNombre());
			item.setPrecio(x.getProducto().getPrecio());
			item.setPreciototal( item.getCantidad() *item.getPrecio());
			total_precio +=item.getPreciototal();
			reporteList.add(item);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
		String date = formatter.format(new Date() );
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("preciototal", total_precio);
		parametros.put("igv",total_precio * 0.18);
		parametros.put("total", total_precio+(total_precio * 0.18));
		
		parametros.put("razonsocial", razonsocial );
		parametros.put("ruc",ruc );
		parametros.put("email",email );
		parametros.put("numcotizacion", cotizacion.getId());
		parametros.put("fecha","Lima, "+date);
		
		
		parametros.put("benruc", globalSession.getRuc());
		parametros.put("bendireccion", globalSession.getDireccion());
		parametros.put("bencentral", globalSession.getCelular());
		parametros.put("benemail",globalSession.getEmail() );
		
		
		try{
			
//			ClassPathResource classPathResource = new ClassPathResource("reports/reporte-cotizacion.jasper");
//
//			InputStream inputStream = classPathResource.getInputStream();
//			File somethingFile = File.createTempFile("test", ".txt");
			
			
			//FileSystemResource aa = new FileSystemResource(null, date)
			
			
			//File file = new ClassPathResource("reports/reporte-cotizacion.jasper").getFile();
			
			JasperPrint print = JasperFillManager.fillReport(MsTallerProyectoApplication.class.getResourceAsStream("/reports/reporte-cotizacion.jasper"), parametros, new JRBeanCollectionDataSource(reporteList));

			//JasperPrint print = JasperFillManager.fillReport(file.getPath(), parametros, new JRBeanCollectionDataSource(reporteList));
			data = JasperExportManager.exportReportToPdf(print);
			guardarReporteCotizacion(cotizacion,data);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	void guardarReporteCotizacion(Cotizacion cotizacion, byte[] data ) {
		
		Archivo file = new Archivo();
		file.setFilename(cotizacion.getCodCotizacion());
		file.setIdCotizacion(cotizacion.getId());
		file.setValue(data);
		file.setFiletype("pdf");
		repoArch.save(file);
	}

	@Override
	public Cotizacion createPedido(Cotizacion cotizacion, GlobalUsuarioSession globalSession) {
		Cotizacion cot = repo.findFirstById(cotizacion.getId());
		cot.setEstado("Pedido");
		return repo.save(cot);
	}

	

}
