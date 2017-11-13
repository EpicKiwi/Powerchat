package fr.epickiwi.powerchat.client.model;

public class Model {

    private ConnectionModel connectionModel;

    public Model() {
        this.connectionModel = new ConnectionModel();
    }

    public ConnectionModel getConnectionModel() {
        return connectionModel;
    }

    public void setConnectionModel(ConnectionModel connectionModel) {
        this.connectionModel = connectionModel;
    }
}
