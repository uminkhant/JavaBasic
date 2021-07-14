package mkt.lib.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mkt.lib.MKLogin;

public class LoginImpl implements MKLogin{

	private  Label lb;
	private  String front_color, back_color;
	private  SVGPath svg;
	private TextField tf;
	private PasswordField pf;
	private Label message;
	private Button enter;
	private Button cancel;
	
	private static final String user_svg = "M18 22.082v-1.649c2.203-1.241 4-4.337 4-7.432 0-4.971 0-9-6-9s-6 4.029-6 9c0 3.096 1.797 6.191 4 7.432v1.649c-6.784 0.555-12 3.888-12 7.918h28c0-4.030-5.216-7.364-12-7.918z";
	private static final String pass_svg = "M18.5 14h-0.5v-6c0-3.308-2.692-6-6-6h-4c-3.308 0-6 2.692-6 6v6h-0.5c-0.825 0-1.5 0.675-1.5 1.5v15c0 0.825 0.675 1.5 1.5 1.5h17c0.825 0 1.5-0.675 1.5-1.5v-15c0-0.825-0.675-1.5-1.5-1.5zM6 8c0-1.103 0.897-2 2-2h4c1.103 0 2 0.897 2 2v6h-8v-6z";
	private static final String open_svg = "M16 6c-6.979 0-13.028 4.064-16 10 2.972 5.936 9.021 10 16 10s13.027-4.064 16-10c-2.972-5.936-9.021-10-16-10zM23.889 11.303c1.88 1.199 3.473 2.805 4.67 4.697-1.197 1.891-2.79 3.498-4.67 4.697-2.362 1.507-5.090 2.303-7.889 2.303s-5.527-0.796-7.889-2.303c-1.88-1.199-3.473-2.805-4.67-4.697 1.197-1.891 2.79-3.498 4.67-4.697 0.122-0.078 0.246-0.154 0.371-0.228-0.311 0.854-0.482 1.776-0.482 2.737 0 4.418 3.582 8 8 8s8-3.582 8-8c0-0.962-0.17-1.883-0.482-2.737 0.124 0.074 0.248 0.15 0.371 0.228v0zM16 13c0 1.657-1.343 3-3 3s-3-1.343-3-3 1.343-3 3-3 3 1.343 3 3z";
	private static final String closed_svg = "M29.561 0.439c-0.586-0.586-1.535-0.586-2.121 0l-6.318 6.318c-1.623-0.492-3.342-0.757-5.122-0.757-6.979 0-13.028 4.064-16 10 1.285 2.566 3.145 4.782 5.407 6.472l-4.968 4.968c-0.586 0.586-0.586 1.535 0 2.121 0.293 0.293 0.677 0.439 1.061 0.439s0.768-0.146 1.061-0.439l27-27c0.586-0.586 0.586-1.536 0-2.121zM13 10c1.32 0 2.44 0.853 2.841 2.037l-3.804 3.804c-1.184-0.401-2.037-1.521-2.037-2.841 0-1.657 1.343-3 3-3zM3.441 16c1.197-1.891 2.79-3.498 4.67-4.697 0.122-0.078 0.246-0.154 0.371-0.228-0.311 0.854-0.482 1.776-0.482 2.737 0 1.715 0.54 3.304 1.459 4.607l-1.904 1.904c-1.639-1.151-3.038-2.621-4.114-4.323z M24 13.813c0-0.849-0.133-1.667-0.378-2.434l-10.056 10.056c0.768 0.245 1.586 0.378 2.435 0.378 4.418 0 8-3.582 8-8z M25.938 9.062l-2.168 2.168c0.040 0.025 0.079 0.049 0.118 0.074 1.88 1.199 3.473 2.805 4.67 4.697-1.197 1.891-2.79 3.498-4.67 4.697-2.362 1.507-5.090 2.303-7.889 2.303-1.208 0-2.403-0.149-3.561-0.439l-2.403 2.403c1.866 0.671 3.873 1.036 5.964 1.036 6.978 0 13.027-4.064 16-10-1.407-2.81-3.504-5.2-6.062-6.938z";

	public LoginImpl(String lb, String front_color,
			String back_color, String path) {

		this.lb=new Label(lb);
		this.front_color=front_color;
		this.back_color=back_color;
		this.tf = new TextField();
		this.pf = new PasswordField();
		this.message = new Label();
		this.enter = new Button("Enter");
		this.cancel = new Button("Cancel");
		
		svg = new SVGPath();
		svg.setStyle("-fx-fill:" + front_color);
		svg.setScaleX(5);
		svg.setScaleY(5);
		svg.setContent(path != null ? path
				: "M3.073 25.42c0.301 0.013 0.605 0.053 0.889 0.16v-0.004l0.043 0.016 3.747-3.653 0.136 0.183c0.195 0.267 0.4 0.507 0.619 0.747l0.080 0.091c0.057 0.057 0.103 0.115 0.16 0.173l0.435 0.433 0.103 0.093 0.093 0.080c1.74 1.476 3.916 2.312 6.207 2.369 1.889 0.045 3.733-0.435 5.347-1.387l0.288 0.299c0.333 0.355 0.676 0.711 1.008 1.067-1.993 1.28-4.307 1.923-6.689 1.867-2.68-0.071-5.235-1.032-7.284-2.751l0.013-0.021-2.404 2.369c0.089 0.28 0.124 0.573 0.128 0.867 0 1.656-1.341 2.997-2.996 2.997s-2.995-1.345-2.995-3.001c0-1.653 1.34-2.995 2.995-2.995zM25.973 9.969c1.18 1.936 1.764 4.169 1.707 6.437-0.067 2.773-1.111 5.417-2.944 7.491l0.013 0.013 1.476 1.568c0.595-0.080 1.189 0.125 1.603 0.56 0.699 0.744 0.676 1.913-0.069 2.623-0.744 0.699-1.913 0.676-2.623-0.069-0.413-0.435-0.584-1.043-0.469-1.627-0.685-0.733-1.9-2.027-2.6-2.76l-0.147-0.16 0.171-0.137c0.447-0.355 0.859-0.744 1.237-1.18 1.569-1.764 2.451-4.008 2.507-6.368 0.047-1.787-0.377-3.537-1.236-5.084zM5.104 2.968c0.527-0.008 1.020 0.2 1.387 0.576 0.413 0.435 0.587 1.043 0.471 1.627l2.839 2.976-0.171 0.139c-0.447 0.355-0.859 0.744-1.237 1.18-1.569 1.763-2.451 4.007-2.52 6.367-0.045 1.787 0.377 3.539 1.237 5.096l-1.363 1.328c-1.191-1.947-1.787-4.18-1.717-6.471 0.059-2.757 1.133-5.421 2.939-7.496l-1.564-1.631c-0.596 0.080-1.191-0.127-1.604-0.56-0.699-0.747-0.676-1.915 0.069-2.624 0.289-0.272 0.663-0.447 1.059-0.493 0.059-0.008 0.12-0.009 0.176-0.013zM29.004 0.589c1.653 0 2.996 1.341 2.996 2.997s-1.341 2.995-2.996 2.995c-0.32 0.004-0.632-0.053-0.933-0.149l-0.003 0.003-0.044-0.016-4.067 3.86-0.136-0.184c-0.217-0.287-0.459-0.573-0.711-0.836l-0.045-0.057-0.355-0.355-0.16-0.137-0.103-0.093-0.080-0.067-0.103-0.093c-1.729-1.453-3.893-2.28-6.16-2.336-1.891-0.044-3.733 0.427-5.339 1.387l-1.307-1.363c1.995-1.283 4.307-1.924 6.689-1.867 2.68 0.069 5.236 1.031 7.285 2.76h0.023l2.693-2.556c-0.093-0.288-0.133-0.591-0.137-0.893 0-1.656 1.341-2.997 2.991-2.999zM15.947 7.277c0.339 0 0.676 0.027 1.013 0.067 4.832 0.607 8.256 5.016 7.661 9.849-0.607 4.833-5.016 8.257-9.849 7.663-4.835-0.607-8.259-5.016-7.664-9.849 0.576-4.484 4.344-7.713 8.839-7.729zM15.813 11.064c-1.955 0.155-3.371 1.635-3.421 3.6 0.036 1.404 0.753 2.587 2.007 3.233l-0.683 3.2h4.564l-0.68-3.2c1.247-0.676 1.987-1.795 2.007-3.231-0.027-2.029-1.597-3.557-3.608-3.608zM15.883 11.043h-0.013z");

	}
	

	private String getUnderlineStyle(String front, String back) {
		return "-fx-background-color:" + front + "," + back + "," + back + ";\n"
				+ "	-fx-background-insets: 0.0 0.0 -2.0 0.0, 0.0 0.0 0.0 0.0, 0.0 -1.0 3.0 -1.0;";

	}

	private String getBtnStyle(Button btn, String c1, String c2) {
		return "-fx-background-color: linear-gradient(" + c1 + " 0%," + c2 + " 25%," + c1 + " 55%," + c2
				+ " 100%); -fx-background-insets: 0;-fx-background-radius:15px;-fx-border-radius:15px;-fx-text-fill:"
				+ c2;

	}

	/* ............................login label */
	private Label label(Label lb, String c1, String c2) {
		lb.setStyle("-fx-font-size: 42px;-fx-font-family:'Arial Black';-fx-text-fill:" + c1
				+ ";-fx-effect: dropshadow( three-pass-box ,  rgba(0,0,0,0.6) , 9, 0.0 , 0 , 5 );");

		return lb;
	}

	/* ................................. textfield style */
	private HBox textfield() {
		tf.setAlignment(Pos.CENTER_LEFT);
		tf.setStyle("-fx-text-fill:" + front_color + ";-fx-background-color:" + back_color);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(getSvg(user_svg, front_color), tf);
		hBox.setStyle(getUnderlineStyle(front_color, back_color));

		return hBox;
	}

	/* button style */
	private Button getButton(Button btn) {
		btn.setPrefSize(100, 50);
		Font font = Font.font("Arial Black", FontWeight.BOLD, 18);
		btn.setFont(font);
		btn.setStyle(getBtnStyle(btn, back_color, front_color));
		btn.setOnMousePressed(e -> btn.setLayoutY(btn.getLayoutY() - 15));
		btn.setOnMouseReleased(e -> btn.setLayoutY(btn.getLayoutY() + 15));
		btn.setOnMouseEntered(e -> btn.setStyle(getBtnStyle(btn, front_color, back_color)));
		btn.setOnMouseExited(e -> btn.setStyle(getBtnStyle(btn, back_color, front_color)));
		return btn;

	}

	private SVGPath getSvg(String path, String c1) {
		SVGPath svg = new SVGPath();
		svg.setStyle("-fx-fill:" + c1 + ";");
		svg.setScaleX(0.6);
		svg.setScaleY(0.6);
		svg.setContent(path);
		return svg;
	}

	/* ........................binding show and hide password */
	@Override
	public HBox password() {
		HBox hBox = new HBox();
		pf.setAlignment(Pos.CENTER_LEFT);
		pf.setStyle("-fx-text-fill:" + front_color + ";-fx-background-color:" + back_color);

		TextField tf = new TextField();
		tf.setAlignment(Pos.CENTER_LEFT);
		tf.setStyle("-fx-text-fill:" + front_color + ";-fx-background-color:" + back_color);

		HBox hb = new HBox();

		SVGPath svg = getSvg(closed_svg, front_color);
		svg.setOnMouseEntered(e -> svg.setContent(open_svg));
		svg.setOnMouseExited(e -> svg.setContent(closed_svg));
		hb.setAlignment(Pos.CENTER_RIGHT);
		HBox.setHgrow(hb, Priority.ALWAYS);
		hb.getChildren().add(svg);
		tf.setManaged(false);
		tf.setVisible(false);
		tf.managedProperty().bind(svg.hoverProperty());
		tf.visibleProperty().bind(svg.hoverProperty());
		pf.managedProperty().bind(svg.hoverProperty().not());
		pf.visibleProperty().bind(svg.hoverProperty().not());

		hBox.getChildren().addAll(getSvg(pass_svg, front_color), pf, tf, hb);
		hBox.setStyle(getUnderlineStyle(front_color, back_color));
		tf.textProperty().bindBidirectional(pf.textProperty());

		return hBox;
	}

	/* ......................................login form */
	@Override
	public HBox Login() {

		Label logo = label(lb, front_color, back_color);
		HBox hbUser = textfield();

		message.setPadding(new Insets(0, 0, 0, 5));

		HBox hbPass = password();
		HBox hbtn = new HBox();
		hbtn.setSpacing(3);
		hbtn.getChildren().addAll(getButton(enter), getButton(cancel));

		VBox vb = new VBox();
		vb.setSpacing(20);
		vb.setTranslateX(35);
		vb.setAlignment(Pos.CENTER_LEFT);
		vb.getChildren().addAll(logo, hbUser, hbPass, hbtn, message);
		HBox hbForm = new HBox();

		hbForm.setPrefSize(500, 350);
		hbForm.setStyle("-fx-background-color:" + back_color);
		hbForm.setAlignment(Pos.CENTER);
		hbForm.setSpacing(15);
		hbForm.getChildren().addAll(svg, vb);
		return hbForm;
	}
	
	


	@Override
	public TextField getTextField() {
		return tf;
	}


	@Override
	public PasswordField getPasswordField() {
		return pf;
	}


	@Override
	public Label getMessage() {
		return message;
	}


	@Override
	public Button getEnter() {
		return enter;
	}


	@Override
	public Button getCancel() {
		return cancel;
	}

}
