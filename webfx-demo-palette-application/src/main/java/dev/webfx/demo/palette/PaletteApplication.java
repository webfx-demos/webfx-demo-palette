package dev.webfx.demo.palette;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Bruno Salmon
 */
public class PaletteApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createPalettePane(), 850, 450));
        primaryStage.setTitle("WebFX Palette");
        primaryStage.show();
    }

    private static Pane createPalettePane() {
        Slider hueSlider = new Slider(0, 360, 180);
        Slider saturationSlider = new Slider(0, 255, 255);
        Slider brightnessSlider = new Slider(0, 255, 255);
        Region[] palettes = { new Region(), new Region(), new Region(), new Region(), new Region() };
        Runnable paletteUpdater = () -> {
            double hue = hueSlider.getValue(), saturation = saturationSlider.getValue() / saturationSlider.getMax(), brightness = brightnessSlider.getValue() / brightnessSlider.getMax();
            for (int i = 0; i < 5; i++)
                setBackground(palettes[i], Color.hsb(hue + (i - 2) * 360 / 5, saturation, brightness));
        };
        paletteUpdater.run();
        hueSlider.valueProperty().addListener(x -> paletteUpdater.run());
        saturationSlider.valueProperty().addListener(x -> paletteUpdater.run());
        brightnessSlider.valueProperty().addListener(x -> paletteUpdater.run());
        return new Pane(hueSlider, saturationSlider, brightnessSlider, palettes[0], palettes[1], palettes[2], palettes[3], palettes[4]) {
            @Override
            protected void layoutChildren() {
                double width = getWidth(), height = getHeight(), sliderHeight = 20, y = 0;
                layoutInArea(hueSlider, 50, y, width - 60, sliderHeight, 0, HPos.LEFT, VPos.TOP);
                layoutInArea(saturationSlider, 50, y += sliderHeight, width - 60, sliderHeight, 0, HPos.LEFT, VPos.TOP);
                layoutInArea(brightnessSlider, 50, y += sliderHeight, width - 60, sliderHeight, 0, HPos.LEFT, VPos.TOP);
                y += sliderHeight;
                for (int i = 0; i < 5; i++)
                    layoutInArea(palettes[i], i * width / 5, y, width / 5, height - y, 0, HPos.LEFT, VPos.TOP);
            }
        };
    }

    private static void setBackground(Region area, Color color) {
        area.setBackground(new Background(new BackgroundFill(color, null, null)));
    }
}
