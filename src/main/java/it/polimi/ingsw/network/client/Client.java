package it.polimi.ingsw.network.client;

import it.polimi.ingsw.utils.message.LoadGameMessage;
import it.polimi.ingsw.utils.message.Message;
import it.polimi.ingsw.utils.message.request.*;
import it.polimi.ingsw.utils.message.response.FirstMatchMessage;
import it.polimi.ingsw.utils.message.response.MatchCreateResponseMessage;
import it.polimi.ingsw.utils.message.response.MatchJoinResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**
 * The Client class represents a client that connects to the server and interacts with it.
 * It provides methods to create or join a match, send messages to the server, and receive messages from the server.
 *
 * @author Vittorio La Rosa
 * @author Marcelo S. Hernandez
 */
public class Client {
    public final ObjectOutputStream outputStream;
    public final ObjectInputStream inputStream;
    private final Socket socket;
    public String username;
    public boolean isFirstMatch;
    public String matchId;

    public String serverAddress;

    /**
     * Initializes a new instance of the Client class.
     *
     * @throws IOException            if an I/O error occurs when creating the socket or streams
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Client() throws IOException, ClassNotFoundException {
        // server discovery
        try {
            InetAddress group = InetAddress.getByName("224.0.0.1"); // Multicast address
            int port = 8888; // Multicast port

            MulticastSocket discoverySocket = new MulticastSocket(port);
            discoverySocket.joinGroup(group);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            System.out.println("Waiting for server discovery...");

            discoverySocket.receive(receivePacket); // Block until receiving a packet

            System.out.println("Server discovery response received.");

            // Extract server information from the received packet
            serverAddress = receivePacket.getAddress().getHostAddress();

            discoverySocket.leaveGroup(group);
            discoverySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // fine server discovery

        this.socket = new Socket(serverAddress != null ? serverAddress : "127.0.0.1", 59090);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeObject(new CheckIfFirstMatchMessage("Check if first match"));
        Message message = (Message) inputStream.readObject();
        if (message instanceof FirstMatchMessage) isFirstMatch = ((FirstMatchMessage) message).isFirstMatch();
    }

    /**
     * Creates a new match.
     *
     * @param matchCreateRequestMessage the match creation request message
     * @return the response message indicating the success or failure of the match creation
     * @throws IOException            if an I/O error occurs when writing to the output stream
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Message createMatch(MatchCreateRequestMessage matchCreateRequestMessage) throws IOException, ClassNotFoundException {

        outputStream.writeObject(matchCreateRequestMessage);
        Message message = (Message) inputStream.readObject();
        if (message instanceof MatchCreateResponseMessage) {
            username = matchCreateRequestMessage.getUsername();
            matchId = ((MatchCreateResponseMessage) message).getMatchId();
        }
        return message;
    }

    /**
     * Joins an existing match.
     *
     * @param matchJoinRequestMessage the match join request message
     * @return the response message indicating the success or failure of the match join
     * @throws IOException            if an I/O error occurs when writing to the output stream
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Message joinMatch(MatchJoinRequestMessage matchJoinRequestMessage) throws IOException, ClassNotFoundException {
        outputStream.writeObject(matchJoinRequestMessage);
        Message message = (Message) inputStream.readObject();
        if (message instanceof MatchJoinResponseMessage) {
            username = matchJoinRequestMessage.getUsername();
            matchId = matchJoinRequestMessage.getMatchId();
        }
        return message;
    }

    /**
     * Sends a new username to the server.
     *
     * @param newUsernameMessage the new username message
     * @return the response message indicating the success or failure of the username change
     * @throws IOException            if an I/O error occurs when writing to the output stream
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Message newUsername(NewUsernameMessage newUsernameMessage) throws IOException, ClassNotFoundException {
        Message message;
        outputStream.writeObject(newUsernameMessage);
        message = (Message) inputStream.readObject();

        if (message instanceof MatchCreateResponseMessage || message instanceof MatchJoinResponseMessage) {
            username = newUsernameMessage.getUsername();
        }

        return message;
    }

    // Client --LoadGame(Game, client.matchId)--> (server può caricare game)
    // se errore <--Message(content=errore)-- Server
    // <--MatchStart(Game)-- Server (posso aggionare la gui)
    // come sempre...

    public Message loadGame(LoadGameMessage loadGameMessage) throws IOException, ClassNotFoundException {
        outputStream.writeObject(loadGameMessage);
        return (Message) inputStream.readObject();
    }

    /**
     * Sends a turn forward request to the server.
     *
     * @param matchTurnRequestMessage the match turn request message
     * @return the response message indicating the success or failure of the turn request
     * @throws IOException            if an I/O error occurs when writing to the output stream
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Message turnForward(MatchTurnRequestMessage matchTurnRequestMessage) throws IOException, ClassNotFoundException {
        outputStream.writeObject(matchTurnRequestMessage);
        return (Message) inputStream.readObject();
    }

    /**
     * Retrieves the next message from the server.
     *
     * @return the next message received from the server
     * @throws IOException            if an I/O error occurs when reading from the input stream
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    public Message nextMessage() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

}
