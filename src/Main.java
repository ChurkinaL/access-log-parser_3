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
}
}
