package co.edu.icesi.academ.client.perfiles.propietario;

import java.util.List;

import co.edu.icesi.academ.bo.TemaBO;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

public class ConsolidadoEvaluacion extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button buttonConfigurarConsolidado;
	@AutoGenerated
	private Button buttonCalcularConsolidado;
	@AutoGenerated
	private Button botonConsolidar;
	@AutoGenerated
	private Table tablaConsolidado;
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ConsolidadoEvaluacion() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		// Define the names and data types of columns.
		this.tablaConsolidado.addContainerProperty("Numeral", String.class, null);
		this.tablaConsolidado.setColumnWidth("Numeral", 60);
		this.tablaConsolidado.addContainerProperty("Tema", String.class, null);
		this.tablaConsolidado.setColumnWidth("Tema", 300);
		this.tablaConsolidado.addContainerProperty("Nivel de Conocimiento Resultante", String.class, null);


		// Allow selecting items from the table.
		tablaConsolidado.setSelectable(true);
		
		// Send changes in selection immediately to server.
		tablaConsolidado.setImmediate(true);

		buttonConfigurarConsolidado.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				configurarConsolidado();
			}
		});
		
		buttonCalcularConsolidado.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				calcularConsolidado();
			}
		});
	}
	
	public void cargarTemas(List<TemaBO> temas) {
		tablaConsolidado.removeAllItems();
		
		for (TemaBO tema : temas) {
			tablaConsolidado.addItem(new Object[] {tema.getId(), tema.getNombre(), ""}, tema);
		}
	}

	protected void configurarConsolidado() {
		// TODO Auto-generated method stub
		
	}

	protected void calcularConsolidado() {
		// TODO Auto-generated method stub
		
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// tablaConsolidado
		tablaConsolidado = new Table();
		tablaConsolidado.setImmediate(false);
		tablaConsolidado.setWidth("100.0%");
		tablaConsolidado.setHeight("100.0%");
		mainLayout.addComponent(tablaConsolidado,
				"top:60.0px;right:10.0px;bottom:10.0px;left:10.0px;");
		
		// botonConsolidar
		botonConsolidar = new Button();
		botonConsolidar.setCaption("Consolidate");
		botonConsolidar.setImmediate(true);
		botonConsolidar.setWidth("-1px");
		botonConsolidar.setHeight("-1px");
		mainLayout.addComponent(botonConsolidar, "top:460.0px;left:906.0px;");
		
		// buttonCalcularConsolidado
		buttonCalcularConsolidado = new Button();
		buttonCalcularConsolidado.setCaption("Calcular Consolidado");
		buttonCalcularConsolidado.setImmediate(true);
		buttonCalcularConsolidado.setWidth("-1px");
		buttonCalcularConsolidado.setHeight("-1px");
		mainLayout.addComponent(buttonCalcularConsolidado,
				"top:20.0px;left:10.0px;");
		
		// buttonConfigurarConsolidado
		buttonConfigurarConsolidado = new Button();
		buttonConfigurarConsolidado.setCaption("Configurar Consolidado");
		buttonConfigurarConsolidado.setImmediate(true);
		buttonConfigurarConsolidado.setWidth("-1px");
		buttonConfigurarConsolidado.setHeight("-1px");
		mainLayout.addComponent(buttonConfigurarConsolidado,
				"top:20.0px;left:180.0px;");
		
		return mainLayout;
	}

}