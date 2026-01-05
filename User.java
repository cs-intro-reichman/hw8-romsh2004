public class User {
    static int maxfCount = 10;
    private String name;
    private String[] follows;
    private int fCount;

    public User(String name) {
        this.name = name;
        follows = new String[maxfCount];
        fCount = 0;
    }

    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    public String getName() {
        return name;
    }

    public String[] getfFollows() {
        return follows;
    }

    public int getfCount() {
        return fCount;
    }

    public boolean follows(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addFollowee(String name) {
        if (fCount == maxfCount) {
            return false;
        }
        if (follows(name)) {
            return false;
        }
        follows[fCount] = name;
        fCount++;
        return true;
    }

    public boolean removeFollowee(String name) {
        int index = -1;
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equals(name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        for (int i = index; i < fCount - 1; i++) {
            follows[i] = follows[i + 1];
        }
        follows[fCount - 1] = null;
        fCount--;
        return true;
    }

    public int countMutual(User other) {
        int mutualCount = 0;
        for (int i = 0; i < fCount; i++) {
            if (other.follows(follows[i])) {
                mutualCount++;
            }
        }
        return mutualCount;
    }

    public boolean isFriendOf(User other) {
        boolean iFollowHim = this.follows(other.getName());
        boolean heFollowsMe = other.follows(this.name);
        return iFollowHim && heFollowsMe;
    }

    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}