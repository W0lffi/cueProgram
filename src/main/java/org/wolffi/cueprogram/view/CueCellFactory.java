package org.wolffi.cueprogram.view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.wolffi.cueprogram.model.Cue;

public class CueCellFactory implements Callback<ListView<Cue>, ListCell<Cue>> {

    public CueCellFactory() {
        super();
    }

    @Override
    public ListCell<Cue> call(ListView<Cue> cueView) {
        System.out.println("Size: " + cueView.getItems().size());
        return new ListCell<>() {
            @Override
            protected void updateItem(Cue cue, boolean empty) {
                super.updateItem(cue, empty);
                System.out.println("Update list view");
                if (!empty && cue != null) {
                    setText(cue.getName());
                }
            }
        };
    }

}
