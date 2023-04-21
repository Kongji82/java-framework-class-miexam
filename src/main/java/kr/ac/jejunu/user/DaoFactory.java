package kr.ac.jejunu.user;

public class DaoFactory {
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
