package greenCity.data;

import java.util.List;

public final class UserRepository {
    private UserRepository() {
    }

    //ToDo get List Users
    public static User getInvalidUser() {
        return new User("testerforapp2023@gmail.com", "1234", null);
    }

    //ToDo get List Users from csv file
//    public List<User> getUserFromCsv() {
//        return User.getByLists(new CSVReader(filename).getAllCells);
//    }

}
