import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer count=0;
        while (true)
        {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            if(file.exists()){
                System.out.println("Путь указан верно. Это файл номер "+(++count));
            }
            else if(file.isDirectory()){
                System.out.println("this is dir");
            }
            else{
                continue;
            }
    }
            Dot dot1= new Dot(1,3);
            Dot dot2= new Dot(1,3);
            Dot dot3= new Dot(5,8);
            System.out.println(dot1.toString());
            System.out.println(dot2.toString());
            System.out.println(dot3.toString());

            System.out.println(dot1.equals(dot2));
            System.out.println(dot2.equals(dot3));



}
}
