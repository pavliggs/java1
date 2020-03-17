package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SessionManager {
    private ArrayList<UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
        sessions = new ArrayList<>();
    }

    public void add(UserSession userSession) {
        sessions.add(userSession);
    }

    public UserSession find(String userName) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getUserName().equals(userName)) {
                LocalDateTime ldt = sessions.get(i).getLastAccess().plusSeconds(sessionValid);
                if ((LocalDateTime.now().isAfter(sessions.get(i).getLastAccess()) || LocalDateTime.now().equals(sessions.get(i).getLastAccess())) &&
                        LocalDateTime.now().isBefore(ldt)) {
                    sessions.get(i).updateLastAccess();
                    return sessions.get(i);
                }
            }
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSessionHandle() == sessionHandle) {
                LocalDateTime ldt = sessions.get(i).getLastAccess().plusSeconds(sessionValid);
                if ((LocalDateTime.now().isAfter(sessions.get(i).getLastAccess()) || LocalDateTime.now().equals(sessions.get(i).getLastAccess())) &&
                        LocalDateTime.now().isBefore(ldt)) {
                    sessions.get(i).updateLastAccess();
                    return sessions.get(i);
                }
            }
        }
        return null;
    }

    public void delete(int sessionHandle) {
        for (int i = 0; i < sessions.size(); i++) {
            if (sessions.get(i).getSessionHandle() == sessionHandle)
                sessions.remove(i);
        }
    }

    public void deleteExpired() {
        for (int i = sessions.size() - 1; i >= 0; i--) {
            LocalDateTime ldt = sessions.get(i).getLastAccess().plusSeconds(sessionValid);
            if (LocalDateTime.now().isAfter(ldt))
                sessions.remove(i);
        }
    }

    public static void main(String[] args) {
        SessionManager sessionManager = new SessionManager(20);
        System.out.println(sessionManager.find("Pavel"));
        UserSession user1 = new UserSession("Kirill");
        sessionManager.add(user1);
        System.out.println();

        System.out.println(sessionManager.get(user1.getSessionHandle()));
        System.out.println(sessionManager.get(user1.getSessionHandle()));
        System.out.println(sessionManager.get(user1.getSessionHandle()));
        System.out.println(sessionManager.get(user1.getSessionHandle()));
        System.out.println(sessionManager.get(user1.getSessionHandle()));
        System.out.println(sessionManager.get(user1.getSessionHandle()));

//
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
//
        System.out.println(sessionManager.get(user1.getSessionHandle()));

        UserSession user2 = new UserSession("Maria");
        sessionManager.add(user2);
//
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        UserSession user3 = new UserSession("Pavel");
        sessionManager.add(user3);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(sessionManager.sessions);

        sessionManager.deleteExpired();

        System.out.println(sessionManager.sessions);

        sessionManager.delete(user3.getSessionHandle());

        System.out.println(sessionManager.sessions);
    }
}
