import java.util.List;

public class Library {
    private Integer ID;
    private List<Book> books;
    private Integer signUp;
    private Integer perDay;
    private Integer bookCount;
    private Integer totalScore = 0;
    private Integer maxDays;
    private double perSignupDay;
    public Library () {

    }
    public Library(List<Book> books, Integer signUp, Integer perDay, Integer id, Integer bookCount) {
        this.books = books;
        this.signUp = signUp;
        this.perDay = perDay;
        this.ID= id;
        this.bookCount = bookCount;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getSignUp() {
        return signUp;
    }

    public void setSignUp(Integer signUp) {
        this.signUp = signUp;
    }

    public Integer getPerDay() {
        return perDay;
    }

    public void setPerDay(Integer perDay) {
        this.perDay = perDay;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(List<Book> scores, Integer day) {
        int daysLeft = day-this.signUp-1;
        int maxBookCount = daysLeft * this.perDay;
        this.setBookCount(Math.min(maxBookCount, this.bookCount));
        int maxTemp = 0;
        for (int i = 0; i <Math.min(maxBookCount, this.bookCount) ; i++) {
            maxTemp += this.books.get(i).getScore();
        }
        this.totalScore = maxTemp;
    }
    public void setMaxDays(Integer day){
        if (this.signUp<day) {
            int daysLeft = day - this.signUp - 1;
            int maxBookCount = daysLeft * this.perDay;
            this.maxDays = (int) Math.ceil(Math.min(maxBookCount, this.bookCount) / this.perDay) + signUp;
        }else {
            this.maxDays = this.signUp;
        }
    }

    public Integer getMaxDays() {
        return maxDays;
    }

    public double getPerSignupDay() {
        return perSignupDay;
    }

    public void setPerSignupDay() {

        this.perSignupDay = this.totalScore/this.perSignupDay;
    }
}
