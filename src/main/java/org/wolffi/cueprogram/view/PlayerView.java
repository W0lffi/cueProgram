package org.wolffi.cueprogram.view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.wolffi.cueprogram.model.cue.Cue;


public class PlayerView {

    private final Scene mainScene;
    private final AnchorPane root;
    private final ListView<Cue> cueView;
    private final Button btnAddCue;
    private final Button btnStopCue;
    private final Button btnPlayCue;
    private final Button btnFadeOutCue;
    private final Button btnPauseCue;

    public PlayerView(Stage stage) {
        cueView = new ListView<>();
        cueView.setCellFactory(new CueCellFactory());
        AnchorPane.setLeftAnchor(cueView, 500.0d);
        AnchorPane.setRightAnchor(cueView, 500.0d);
        AnchorPane.setTopAnchor(cueView, 50.0d);
        AnchorPane.setBottomAnchor(cueView, 10.0d);

        btnAddCue = new Button("Add cue");
        btnAddCue.setMinSize(100.0d, 100.0d);
        btnAddCue.setMaxSize(100.0d, 100.0d);
        AnchorPane.setLeftAnchor(btnAddCue, 1500.0d);
        AnchorPane.setRightAnchor(btnAddCue, 300.0d);
        AnchorPane.setTopAnchor(btnAddCue, 50.0d);
        AnchorPane.setBottomAnchor(btnAddCue, 1000.0d);

        btnPlayCue = new Button("Play");
        btnPlayCue.setMinSize(100.0d, 100.0d);
        btnPlayCue.setMaxSize(100.0d, 100.0d);
        AnchorPane.setLeftAnchor(btnPlayCue, 1700.0d);
        AnchorPane.setRightAnchor(btnPlayCue, 25.0d);
        AnchorPane.setTopAnchor(btnPlayCue, 50.0d);
        AnchorPane.setBottomAnchor(btnPlayCue, 1000.0d);

        btnStopCue = new Button("Stop");
        btnStopCue.setMinSize(100.0d, 100.0d);
        btnStopCue.setMaxSize(100.0d, 100.0d);
        AnchorPane.setLeftAnchor(btnStopCue, 1700.0d);
        AnchorPane.setRightAnchor(btnStopCue, 25.0d);
        AnchorPane.setTopAnchor(btnStopCue, 100.0d);
        AnchorPane.setBottomAnchor(btnStopCue, 950.0d);

        btnPauseCue = new Button("Pause");
        btnPauseCue.setMinSize(100.0d, 100.0d);
        btnPauseCue.setMaxSize(100.0d, 100.0d);
        AnchorPane.setLeftAnchor(btnPauseCue, 1700.0d);
        AnchorPane.setRightAnchor(btnPauseCue, 25.0d);
        AnchorPane.setTopAnchor(btnPauseCue, 150.0d);
        AnchorPane.setBottomAnchor(btnPauseCue, 900.0d);

        btnFadeOutCue = new Button("Fade out");
        btnFadeOutCue.setMinSize(100.0d, 100.0d);
        btnFadeOutCue.setMaxSize(100.0d, 100.0d);
        AnchorPane.setLeftAnchor(btnFadeOutCue, 1500.0d);
        AnchorPane.setRightAnchor(btnFadeOutCue, 300.0d);
        AnchorPane.setTopAnchor(btnFadeOutCue, 100.0d);
        AnchorPane.setBottomAnchor(btnFadeOutCue, 950.0d);

        root = new AnchorPane(cueView, btnAddCue, btnPlayCue, btnStopCue, btnFadeOutCue, btnPauseCue);
        root.setMinSize(500.0d,500.0d);
        mainScene = new Scene(root, 1920, 1080, Color.GRAY);
        stage.setTitle("Cue player");
        stage.setScene(mainScene);
        stage.show();
    }

    public ListView<Cue> getCueView() { return this.cueView; }
    public int getSelectedCueIndex() { return this.cueView.getSelectionModel().getSelectedIndex(); }
    public Button getBtnAddCue() { return this.btnAddCue; }
    public Button getBtnPlayCue() { return this.btnPlayCue; }
    public Button getBtnStopCue() { return this.btnStopCue; }
    public Button getBtnPauseCue() { return this.btnPauseCue; }
    public Button getBtnFadeOutCue() { return this.btnFadeOutCue; }
}
