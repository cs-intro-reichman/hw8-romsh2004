public class Network {
    private User[] users;
    private int userCount;

    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    public User getUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    public boolean addUser(String name) {
        if (userCount == users.length) {
            return false;
        }
        if (getUser(name) != null) {
            return false;
        }
        users[userCount] = new User(name);
        userCount++;
        return true;
    }

    public boolean addFollowee(String name1, String name2) {
        User u1 = getUser(name1);
        User u2 = getUser(name2);

        if (u1 == null || u2 == null) {
            return false;
        }
        if (name1.equals(name2)) {
            return false;
        }
        return u1.addFollowee(name2);
    }

    public String recommendWhoToFollow(String name) {
        User mostRecommended = null;
        int maxMutuals = 0;
        User subject = getUser(name);

        if (subject == null) {
            return null;
        }

        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                continue;
            }
            int mutuals = users[i].countMutual(subject);
            if (mutuals > maxMutuals) {
                maxMutuals = mutuals;
                mostRecommended = users[i];
            }
        }
        if (mostRecommended != null) {
            return mostRecommended.getName();
        }
        return null;
    }

    public String mostPopularUser() {
        if (userCount == 0) {
            return null;
        }
        String popularName = null;
        int maxFollowers = 0;
        for (int i = 0; i < userCount; i++) {
            String currentName = users[i].getName();
            int currentCount = followeeCount(currentName);
            if (currentCount > maxFollowers) {
                maxFollowers = currentCount;
                popularName = currentName;
            }
        }
        return popularName;
    }

    private int followeeCount(String name) {
        int count = 0;
        for (int i = 0; i < userCount; i++) {
            if (users[i].follows(name)) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        String ans = "Network:";
        for (int i = 0; i < userCount; i++) {
            ans += "\n" + users[i].toString();
        }
        return ans;
    }
}