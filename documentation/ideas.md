
# Parent Child Architecture
**Description**
During the development of this app I had the idea to create a parent child architecture where there is one main client that handles the actual logic and one or more child clients that get information from the parent. This way, 
in the case of this app, the wheel could be spun. And all the child clients would see the result and the "winner" but would not be able to influence this. It would essentially be the same as watching a livestream.

## Build steps
To expand the app with both user and admin versions that can trigger events simultaneously across all user/client versions, we'll need to implement a client-server architecture with real-time communication. 

The following steps are a high-level overview of how this could be achieved:
1. **Server Backend**:
    - Set up a server backend that acts as an intermediary for communication between admin and user clients.
    - Implement authentication mechanisms to differentiate between admin and user clients.

2. **Real-time Messaging**:
    - Use a real-time messaging system like WebSockets to establish a persistent connection between the server and all connected clients.
    - When a user or admin client connects to the server, they should join a relevant channel or room. Admins join the admin channel, while users join user channels.

3. **Admin App**:
    - Create the admin version of the app. This app has functionalities for the admin to trigger events.
    - When the admin triggers an event, send a WebSocket message to the server with the event data. The server will then broadcast this event to all connected user clients.

4. **User App**:
    - Create the user version of the app. This app has the necessary functionality to receive and respond to events triggered by the admin.
    - When a user client receives an event message from the server via WebSocket, it should process the event and update its UI or perform the required action.

5. **WebSocket Integration**:
    - Use a WebSocket library for Kotlin/Android to establish and manage WebSocket connections in the apps (e.g. OkHttp or Scarlet).

6. **Authentication and Authorization**:
    - Ensure that the server backend has proper authentication and authorization mechanisms to prevent unauthorized access to admin functionalities.

7. **Testing and Deployment**:
    - Test the admin and user apps to ensure they work as expected.
    - Deploy the server backend to a hosting provider or server that can handle WebSocket connections.

8. **Error Handling and Resilience**:
    - Implement error handling and retries in the Android apps to handle network issues and ensure the app can reconnect to the server if the connection is lost.

9. **Security**:
    - Ensure that the WebSocket communication is secure by using secure WebSocket connections (wss://) and following best practices for data validation and sanitization.

10. **Scalability**:
    - Consider implementing load balancing and scaling strategies for the server backend.