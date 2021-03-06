@Override
    public void initialize(URL url, ResourceBundle rb) { /* runs via the Initializable implementation */
        isConnected = false;
        displayState(ConnectionDisplayState.DISCONNECTED);

        sentMessagesData = FXCollections.observableArrayList();
        sentMessagesListView.setItems(sentMessagesData);
        sentMessagesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        sentMessagesListView.setOnMouseClicked((Event event) -> {
            String selectedItem
                    = sentMessagesListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("null")) {
                selectedTextField.setText("Sent: " + selectedItem);
                lastSelectedListView = sentMessagesListView;
            }
        });

        ReceivedMessagesData = FXCollections.observableArrayList();
        ReceivedMessagesListView.setItems(ReceivedMessagesData);
        ReceivedMessagesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ReceivedMessagesListView.setOnMouseClicked((Event event) -> {
            String selectedItem
                    = ReceivedMessagesListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("null")) {
                selectedTextField.setText("Received: " + selectedItem);
                lastSelectedListView = ReceivedMessagesListView;
            }
        });

        Runtime.getRuntime().addShutdownHook(new ShutDownThread()); /* shutdown hook allows the created ShutDownThread 
                                                                    to be run prior to server shutting down (see below ShutDownThread class)*/

        /*
         * Uncomment to have autoConnect enabled at startup
         */
//        autoConnectCheckBox.setSelected(true);
//        displayState(ConnectionDisplayState.WAITING);
//        connect();
    }

class ShutDownThread extends Thread {

        @Override
        public void run() {
            if (socket != null) { 
                if (socket.debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                    LOGGER.info("ShutdownHook: Shutting down Server Socket"); /* adds info to LOGGER (see https://docs.oracle.com/javase/7/docs/api/java/util/logging/Logger.html) */
                }
                socket.shutdown(); /* shuts down server */
            }
        }
    }
