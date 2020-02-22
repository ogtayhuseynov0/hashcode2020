import java.io.*;
import java.util.*;

public class Main2 {
    public static Integer book;
    public static Integer library;
    public static Integer days;
    public static List<Book> scores = new ArrayList<>();
    public static List<Library> libraries = new ArrayList<>();

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        try {
            File myObj = new File("./src/in.txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(new File("./src/out.txt")));
            Scanner myReader = new Scanner(myObj);
            book = myReader.nextInt();
            library = myReader.nextInt();
            days = myReader.nextInt();
            for (int i = 0; i < book; i++) {
                Book book =  new Book();
                book.setId(i);
                book.setScore(myReader.nextInt());
                scores.add(book);
            }
            for (int i = 0; i < library; i++) {
                Library library = new Library();
                library.setID(i);
                int bookcount= myReader.nextInt();
                library.setBookCount(bookcount);
                library.setSignUp(myReader.nextInt());
                library.setPerDay(myReader.nextInt());
                List<Book> a = new ArrayList<>();
                for (int j = 0; j < library.getBookCount(); j++) {
                    int tm2 = myReader.nextInt();
                    Book temp = new Book();
                    temp.setScore(scores.get(tm2).getScore());
                    temp.setId(tm2);
                    a.add(temp);
                }
                Collections.sort(a, new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getScore().compareTo(o2.getScore());
                    }
                }.reversed());
                library.setBooks(a);
                library.setMaxDays(days);
                library.setTotalScore(scores,days);
                library.setPerSignupDay();
                libraries.add(library);
            }
            List<Library> libraries2 = libraries;
            Comparator<Library> libraryComparator = Comparator.comparingInt(Library::getTotalScore).reversed();
            libraries2.sort(libraryComparator);
            List<Book> books2 = scores;
            Comparator<Book> bookComparator = Comparator.comparingInt(Book::getScore).reversed();
            books2.sort(bookComparator);

            int days2 = days;
            int maxlib = 0;
            for (int i = 0; i <library ; i++) {
                Library temp = libraries2.get(i);
                days2-=temp.getSignUp();
                if (days2<=0){
                    maxlib = i;
                    break;
                }
            }
            br.write(maxlib + "\n");

            for (int i = 0; i <maxlib ; i++) {
                Library temp = libraries2.get(i);
                if (temp.getSignUp()<days){
//                    System.out.print(temp.getID() + " ");
                    br.write(temp.getID() + " ");
                    br.write(temp.getBookCount() + " \n");
//                    System.out.print(temp.getBookCount() + " \n");
                    List<Book> aa = temp.getBooks();
                    for (int j = 0; j <temp.getBookCount() ; j++) {
//                        System.out.print(aa.get(j)+ " ");
                        br.write(aa.get(j).getId()+ " ");
                    }
//                    System.out.println();
                    if (i+1 != maxlib){
                        br.write('\n');
                    }
                }
            }
            myReader.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
