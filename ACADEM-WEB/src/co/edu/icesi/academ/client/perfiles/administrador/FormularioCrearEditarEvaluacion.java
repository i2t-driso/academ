package co.edu.icesi.academ.client.perfiles.administrador;

import java.util.Date;
import java.util.List;

import co.edu.icesi.academ.bo.EvaluacionBO;
import co.edu.icesi.academ.bo.ProgramaBO;
import co.edu.icesi.academ.bo.UsuarioBO;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.ErrorMessage.ErrorLevel;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;

public class FormularioCrearEditarEvaluacion extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Label labelError;
	@AutoGenerated
	private Button buttonGuardar;
	@AutoGenerated
	private ComboBox comboBoxPropietario;
	@AutoGenerated
	private ComboBox comboBoxPrograma;
	@AutoGenerated
	private Label labelPropietario;
	@AutoGenerated
	private Label labelPrograma;
	@AutoGenerated
	private PopupDateField popupDateFieldFechaFinal;
	@AutoGenerated
	private Label labelFechaFinal;
	@AutoGenerated
	private PopupDateField popupDateFieldFechaInicial;
	@AutoGenerated
	private Label labelFechaInicial;
	private static final long serialVersionUID = 1L;
	private int idEvaluacion;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FormularioCrearEditarEvaluacion() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// User code
		idEvaluacion = -1;
		
		// Validador de la fecha inicial
		popupDateFieldFechaInicial.setRequiredError("Select a start date for the survey.");
		
		// Validador de la fecha final
		popupDateFieldFechaFinal.setRequiredError("Select a finish date for the survey.");
		popupDateFieldFechaFinal.addValidator(new Validator() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void validate(Object value) throws InvalidValueException {
				Date fechaInicial = popupDateFieldFechaInicial.getValue();
				Date fechaFinal = (Date) value;
				if (fechaInicial.after(fechaFinal)) {
					throw new InvalidValueException("The finish date must be after the start date.");
				}
			}
		});
		
		comboBoxPrograma.setNullSelectionAllowed(false);
		comboBoxPrograma.setImmediate(true);
		// Validador del programa
		comboBoxPrograma.setRequiredError("Select the program associated with this survey.");
		
		comboBoxPropietario.setNullSelectionAllowed(false);
		comboBoxPropietario.setImmediate(true);
		// Validador del propietario
		comboBoxPropietario.setRequiredError("Select the owner of this survey.");
		
		buttonGuardar.addClickListener(new ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				guardarEvaluacion();
			}
		});
		
		this.labelError.setValue("");
		this.labelError.setEnabled(false);
	}

	public void cargarProgramas(List<ProgramaBO> programas) {
		comboBoxPrograma.removeAllItems();
		
		for (ProgramaBO programaBO : programas) {
			comboBoxPrograma.addItem(programaBO);
		}
	}
	
	public void cargarUsuarios(List<UsuarioBO> usuarios) {
		comboBoxPropietario.removeAllItems();
		
		for (UsuarioBO usuarioBO : usuarios) {
			comboBoxPropietario.addItem(usuarioBO);
		}
	}

	public void cargarEvaluacion(EvaluacionBO evaluacion) {
		idEvaluacion = evaluacion.getId();
		popupDateFieldFechaInicial.setValue(evaluacion.getFechaInicial());
		popupDateFieldFechaFinal.setValue(evaluacion.getFechaFinal());
		comboBoxPrograma.select(evaluacion.getPrograma());
		comboBoxPropietario.select(evaluacion.getPropietario());
	}

	protected void guardarEvaluacion() {
		try {
			// Validar campos del formulario
			validarCampos();
			
			// Obtener información de la evaluación de los campos del formulario
			Date fechaInicial = popupDateFieldFechaInicial.getValue();
			Date fechaFinal = popupDateFieldFechaFinal.getValue();
			ProgramaBO programa = (ProgramaBO) comboBoxPrograma.getValue();
			UsuarioBO usuario = (UsuarioBO) comboBoxPropietario.getValue();
			
			// Crear evaluación
			EvaluacionBO evaluacion = new EvaluacionBO();
			evaluacion.setFechaInicial(fechaInicial);
			evaluacion.setFechaFinal(fechaFinal);
			evaluacion.setPrograma(programa);
			evaluacion.setPropietario(usuario);
			
			if (idEvaluacion == -1) {
				ControladorAdministrador.getInstance().crearEvaluacion(evaluacion);
			} else {
				evaluacion.setId(idEvaluacion);
				ControladorAdministrador.getInstance().editarEvaluacion(evaluacion);
			}
		} catch (Exception e) {
			
		}
	}
	
	private void validarCampos() throws Exception {
		try {
			popupDateFieldFechaInicial.validate();
		} catch (InvalidValueException e) {
			mostrarErrorValidacion(popupDateFieldFechaInicial, e.getMessage());
			throw e;
		}
		try {
			popupDateFieldFechaFinal.validate();
		} catch (InvalidValueException e) {
			mostrarErrorValidacion(popupDateFieldFechaFinal, e.getMessage());
			throw e;
		}
		try {
			comboBoxPrograma.validate();
		} catch (InvalidValueException e) {
			mostrarErrorValidacion(comboBoxPrograma, e.getMessage());
			throw e;
		}
		try {
			comboBoxPropietario.validate();
		} catch (InvalidValueException e) {
			mostrarErrorValidacion(comboBoxPropietario, e.getMessage());
			throw e;
		}
	}

	private void mostrarErrorValidacion(AbstractComponent componente, String mensaje) {
		Notification.show("Validation Error", mensaje, Type.TRAY_NOTIFICATION);
		this.labelError.setValue(mensaje);
	}

	public void setMensajeError(String mensaje) {
		Notification.show("ACaDeM", mensaje, Type.TRAY_NOTIFICATION);
		this.labelError.setValue(mensaje);
		buttonGuardar.setComponentError(new UserError(mensaje, ContentMode.TEXT, ErrorLevel.WARNING));
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("320px");
		mainLayout.setHeight("260px");
		
		// top-level component properties
		setWidth("320px");
		setHeight("260px");
		
		// labelFechaInicial
		labelFechaInicial = new Label();
		labelFechaInicial.setImmediate(false);
		labelFechaInicial.setWidth("-1px");
		labelFechaInicial.setHeight("-1px");
		labelFechaInicial.setValue("Start Date:");
		mainLayout.addComponent(labelFechaInicial, "top:20.0px;left:20.0px;");
		
		// popupDateFieldFechaInicial
		popupDateFieldFechaInicial = new PopupDateField();
		popupDateFieldFechaInicial.setImmediate(false);
		popupDateFieldFechaInicial.setWidth("-1px");
		popupDateFieldFechaInicial.setHeight("-1px");
		popupDateFieldFechaInicial.setRequired(true);
		mainLayout.addComponent(popupDateFieldFechaInicial,
				"top:20.0px;left:120.0px;");
		
		// labelFechaFinal
		labelFechaFinal = new Label();
		labelFechaFinal.setImmediate(false);
		labelFechaFinal.setWidth("-1px");
		labelFechaFinal.setHeight("-1px");
		labelFechaFinal.setValue("Finish Date:");
		mainLayout.addComponent(labelFechaFinal, "top:60.0px;left:20.0px;");
		
		// popupDateFieldFechaFinal
		popupDateFieldFechaFinal = new PopupDateField();
		popupDateFieldFechaFinal.setImmediate(false);
		popupDateFieldFechaFinal.setWidth("-1px");
		popupDateFieldFechaFinal.setHeight("-1px");
		popupDateFieldFechaFinal.setRequired(true);
		mainLayout.addComponent(popupDateFieldFechaFinal,
				"top:60.0px;left:120.0px;");
		
		// labelPrograma
		labelPrograma = new Label();
		labelPrograma.setImmediate(false);
		labelPrograma.setWidth("-1px");
		labelPrograma.setHeight("-1px");
		labelPrograma.setValue("Program:");
		mainLayout.addComponent(labelPrograma, "top:100.0px;left:20.0px;");
		
		// labelPropietario
		labelPropietario = new Label();
		labelPropietario.setImmediate(false);
		labelPropietario.setWidth("-1px");
		labelPropietario.setHeight("-1px");
		labelPropietario.setValue("Owner:");
		mainLayout.addComponent(labelPropietario, "top:140.0px;left:20.0px;");
		
		// comboBoxPrograma
		comboBoxPrograma = new ComboBox();
		comboBoxPrograma.setImmediate(false);
		comboBoxPrograma.setWidth("175px");
		comboBoxPrograma.setHeight("-1px");
		comboBoxPrograma.setRequired(true);
		mainLayout.addComponent(comboBoxPrograma, "top:100.0px;left:120.0px;");
		
		// comboBoxPropietario
		comboBoxPropietario = new ComboBox();
		comboBoxPropietario.setImmediate(false);
		comboBoxPropietario.setWidth("175px");
		comboBoxPropietario.setHeight("-1px");
		comboBoxPropietario.setRequired(true);
		mainLayout.addComponent(comboBoxPropietario,
				"top:140.0px;left:120.0px;");
		
		// buttonCrearEditarEvaluacion
		buttonGuardar = new Button();
		buttonGuardar.setCaption("Guardar");
		buttonGuardar.setImmediate(true);
		buttonGuardar.setWidth("-1px");
		buttonGuardar.setHeight("-1px");
		mainLayout.addComponent(buttonGuardar,
				"top:180.0px;left:222.0px;");
		
		// labelError
		labelError = new Label();
		labelError.setImmediate(false);
		labelError.setWidth("-1px");
		labelError.setHeight("-1px");
		labelError.setValue("Label");
		mainLayout.addComponent(labelError, "top:220.0px;left:20.0px;");
		
		return mainLayout;
	}

}
