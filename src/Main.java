import controller.GameController;
import model.GameModel;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);
        view.launch();
    }
}
