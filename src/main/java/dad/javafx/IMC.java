package dad.javafx;

import javafx.application.Application;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		SimpleDoubleProperty peso=new SimpleDoubleProperty();
		SimpleDoubleProperty altura=new SimpleDoubleProperty();
		SimpleDoubleProperty imc=new SimpleDoubleProperty();
		
		TextField pesoTf=new TextField();
		TextField alturaTf=new TextField();
		Label imcLb=new Label("IMC: ");
		Label imcLb2=new Label("Default");
		Label pesoIn=new Label("Peso:");
		Label pesoFin=new Label("Kg");
		Label alturaIn=new Label("Altura:");
		Label alturaFin=new Label("cm");
		Label imcResult=new Label("Bajo peso | Normal | Sobrepeso | Obeso");
		
		HBox pesoBox=new HBox();
		pesoBox.getChildren().addAll(pesoIn, pesoTf, pesoFin);
		pesoBox.setAlignment(Pos.CENTER);
		pesoBox.setSpacing(5);
		
		HBox alturaBox=new HBox();
		alturaBox.getChildren().addAll(alturaIn, alturaTf, alturaFin);
		alturaBox.setAlignment(Pos.CENTER);
		alturaBox.setSpacing(5);
		
		HBox imcBox=new HBox();
		imcBox.getChildren().addAll(imcLb, imcLb2);
		imcBox.setAlignment(Pos.CENTER);
		
		VBox root=new VBox();
		root.getChildren().addAll(pesoBox, alturaBox, imcLb, imcResult);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);
		
		Scene escene=new Scene(root, 400, 300);
		
		primaryStage.setScene(escene);
		primaryStage.setTitle("IMC");
		primaryStage.show();
		
		Bindings.bindBidirectional(pesoTf.textProperty(), peso, new NumberStringConverter());
		Bindings.bindBidirectional(alturaTf.textProperty(), altura, new NumberStringConverter());
		imc.bind(peso.divide((altura.multiply(altura)).divide(100)).multiply(100));
		imcLb.textProperty().bind(imc.asString());
		
		imc.addListener((o,ov,nv)-> {
			
			if (imc.getValue() < 18.5)
				imcResult.setText("Bajo Peso");
			else if (imc.getValue() >= 18.5 && imc.getValue() < 25)
				imcResult.setText("Normal");
			else if (imc.getValue() >= 25 && imc.getValue() < 30)
				imcResult.setText("Sobrepeso");
			else if (imc.getValue() > 30)
				imcResult.setText("Obeso");
		});
	}
	public static void main(String[] args) {
	launch(args);
	}
}
