private void connect() {
        socket = new FxSocketServer(new FxSocketListener(), /* creates a new listener that the server will run) */
                Integer.valueOf(portTextField.getText()), /* declares the port that the server shall run on (see https://www.cloudflare.com/learning/network-layer/what-is-a-computer-port/) */
                Constants.instance().DEBUG_NONE); /* debug flags, irrelevant to our use case */
        socket.connect(); /* attempts to connect the socket using given port */
    }
