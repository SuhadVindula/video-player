import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class AppInitializer extends Application {

    private static Stage stgVideoPlayer;
    private final SimpleBooleanProperty isMute = new SimpleBooleanProperty(false);
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        showVideoPlayerStage();
    }

    private void showVideoPlayerStage() {
        if (stgVideoPlayer != null) return;

        stgVideoPlayer = new Stage();
        stgVideoPlayer.setTitle("Video Player Demo");
        videoPlayerScene();
        stgVideoPlayer.show();
        stgVideoPlayer.centerOnScreen();
    }

    private void videoPlayerScene() {
        Image imgPlay = new Image(getClass().getResource("/video-icon/play-button-arrowhead.png").toString(), 48, 48, true, true);
        Image imgPause = new Image(getClass().getResource("/video-icon/pause.png").toString(), 48, 48, true, true);
        Image imgStop = new Image(getClass().getResource("/video-icon/stop-button.png").toString(), 48, 48, true, true);
        Image imgOpen = new Image(getClass().getResource("/video-icon/folder.png").toString(), 48, 48, true, true);
        Image imgSpeaker = new Image(getClass().getResource("/video-icon/speaker-filled-audio-tool.png").toString(), 48, 48, true, true);
        Image imgMute = new Image(getClass().getResource("/video-icon/mute-speaker.png").toString(), 48, 48, true, true);

        ImageView imgViewPlay = new ImageView(imgPlay);
        ImageView imgViewPause = new ImageView(imgPause);
        ImageView imgViewStop = new ImageView(imgStop);
        ImageView imgViewOpen = new ImageView(imgOpen);
        ImageView imgViewSpeaker = new ImageView(imgSpeaker);
        ImageView imgViewMute = new ImageView(imgMute);

        Label lblPlay = new Label("", imgViewPlay);
        Label lblStop = new Label("", imgViewStop);
        Label lblOpen = new Label("", imgViewOpen);
        Label lblSpeaker = new Label("", imgViewSpeaker);

        lblPlay.setTooltip(new Tooltip("Play"));
        lblStop.setTooltip(new Tooltip("Stop"));
        lblOpen.setTooltip(new Tooltip("Open a video file to play"));
        lblSpeaker.setTooltip(new Tooltip("Mute"));

        Slider sldVolume = new Slider(0, 1, 0.7);

        HBox hBox = new HBox(lblPlay, lblStop, lblOpen, lblSpeaker, sldVolume);

        Image imgBackground = new Image(getClass().getResource("/video-icon/play.png").toString(), 200, 200, true, true);
        ImageView imgViewBackground = new ImageView(imgBackground);
        Label lblBackground = new Label("", imgViewBackground);

        lblBackground.setAlignment(Pos.CENTER);
        lblBackground.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        MediaView mediaView = new MediaView();
        mediaView.setPreserveRatio(true);
        StackPane stackPane = new StackPane(lblBackground, mediaView);
        stackPane.setBackground(Background.fill(Color.BLACK));

        AnchorPane root = new AnchorPane(stackPane, hBox);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);

        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 89.0);

        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20, 10, 20, 10));
        hBox.setBackground(Background.fill(Color.LIGHTBLUE));

        HBox.setHgrow(lblOpen, Priority.ALWAYS);
        lblOpen.setMaxWidth(Double.MAX_VALUE);
        lblOpen.setAlignment(Pos.CENTER);

        for (Node control : hBox.getChildren()) {
            if (control instanceof Label) {
                Label lbl = (Label) control;
                lbl.setCursor(Cursor.HAND);
                lbl.setOnMouseEntered(event -> {
                    lbl.getGraphic().setOpacity(0.8);
                    ScaleTransition st = new ScaleTransition(Duration.millis(100), lbl);
                    st.setFromX(1);
                    st.setFromY(1);
                    st.setToX(1.1);
                    st.setToY(1.1);
                    st.play();
                });
                lbl.setOnMouseExited(event -> {
                    lbl.getGraphic().setOpacity(1);
                    ScaleTransition st = new ScaleTransition(Duration.millis(100), lbl);
                    st.setFromX(1.1);
                    st.setFromY(1.1);
                    st.setToX(1);
                    st.setToY(1);
                    st.play();
                });
            }
        }
        Scene scene = new Scene(root);
        stgVideoPlayer.setScene(scene);
        stgVideoPlayer.setMinWidth(400);
        stgVideoPlayer.setWidth(600);
        stgVideoPlayer.setMinHeight(400);

        mediaView.fitWidthProperty().bind(stackPane.widthProperty());
        mediaView.fitHeightProperty().bind(stackPane.heightProperty());


    }
}
