package controller;

import model.GameModel;

import model.cards.Card;
import model.unit.AI;
import model.unit.Player;
import view.GameView;
import view.components.Dice;
import view.components.panels.LifePlanPanel;
import view.windows.Map;
import view.windows.Menu;

public class GameController {
    private final GameModel model;
    private final Map mapView;

    private boolean gameOverFlag = false;

    public GameController(GameModel _model, GameView view) {
        model = _model;
        view.setController(this);
        model.setController(this);
        mapView = view.getMap();
    }

    // read from Menu, write to model
    public void setPlayer(Menu menu) {
        for (int i = 0; i < menu.getHumanPlayerCount(); i++) {
            LifePlanPanel lifePlan = menu.getPlayerPlan(i);
            model.addUnit(new Player(i, "Player"+(i+1), lifePlan.getGender(), lifePlan.getMoneyPlan(), lifePlan.getHappinessPlan(), lifePlan.getKnowledgePlan()));
        }
        for (int i = menu.getHumanPlayerCount(); i < menu.getPlayerCount(); i++) {
            model.addUnit(new AI(i,"Player"+(i+1)));
        }
    }

    // initialize player info (View)
    public void initMapInfo(Map map) {
        map.initPlayersInfo(model.getUnits());
    }

    // Event driven
    public void gameStart() {
        nextPlayer();
    }

    // round start
    public void nextPlayer() {
        // update player info
        if (model.getCurUnit() != null)
            mapView.setUnitHighlight(model.getCurUnit().getID(), false);
        model.nextPlayer();
        mapView.showMsg("src/images/message.png","Message", "\n\nPlayer " + (model.getCurUnit().getID()+1) + "'s turn!");
        mapView.setUnitHighlight(model.getCurUnit().getID(), true);

        // curPlayer can't move -> next player
        if (!model.playerCanMove()) {
            mapView.showMsg("src/images/stop.png", "STOP", "\n\n玩家暫停中，跳過此回合");
            new EventTrigger(3000, e -> nextPlayer());
            return;
        }

        // curPlayer is AI -> roll the dice automatically
        if (model.getCurUnit() instanceof AI) {
            new EventTrigger(1500, e -> mapView.triggerRollDice());
        } else { // wait for rolling dice
            mapView.unlockDice();
        }
    }

    // trigger by diceView
    public void rollDice(Dice diceView) {
        int point = model.rollDice();
        diceView.render(point);

        new EventTrigger(0, e -> updateLocation());
    }

    public void updateLocation() {
        mapView.updatePlayerLocation(model.getUnits(), model.getCurUnit());
        mapView.popupReaction(model.getCurUnit(), model.getCurUnit() instanceof Player);

        Card card = model.getCard();
        if (card != null) { // card location
            mapView.showCardDescription("\n" + card.getDescription());
        }

        // wait for reaction
        if (model.getCurUnit() instanceof AI) {
            new EventTrigger(1500, e -> mapView.triggerReaction());
        }
    }

    // trigger by popup (View)
    public void doReaction(String reaction) {
        if (gameOverFlag) {
            mapView.setVisible(false);
            return ;
        }
        // take effect here
        model.takeLocationEffect(reaction);

        // update View
        updateView();

        if (model.gameOver()) {
            mapView.showMsg("src/images/trophy.png", "The End", "\n\n恭喜 Player" +  (model.getCurUnit().getID()+1) + "成為有錢、快樂、又有智慧的臺大生！");
            mapView.popupReaction(null, true);
            gameOverFlag = true;
        } else {
            new EventTrigger(1500, e -> nextPlayer());
        }
    }

    public void updateView() {
        mapView.updatePlayerLocation(model.getUnits(), model.getCurUnit());
        mapView.updatePlayerInfo(model.getUnits());
    }
}
