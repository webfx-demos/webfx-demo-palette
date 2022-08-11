// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.palette.application {

    // Direct dependencies modules
    requires javafx.controls;
    requires javafx.graphics;

    // Exported packages
    exports dev.webfx.demo.palette;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.palette.PaletteApplication;

}