import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Ram> list = new LinkedList<>();
        Ctrl ctrl = new Ctrl();
        Ram ram = new Ram();
        Scanner sc = new Scanner(System.in);
        System.out.println("初始分区大小为：");
        int n = sc.nextInt();
        //初始化分区大小
        ram.setSize(n);
        list.add(ram);
        ctrl.show(list);
        while (true) {
            int chose = sc.nextInt();
            switch (chose) {
                case 1:
                    System.out.println("输入作业编号和大小");
                    Ram ram1 = new Ram();
                    ram1.setId(sc.nextInt());
                    ram1.setSize(sc.nextInt());
                    ram1.setFree(1);
                    ctrl.allocate(ram1,list);
                    break;
                case 2:
                    System.out.println("输入作业编号");
                    int no = sc.nextInt();
                    ctrl.release(no,list);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("未知操作");
            }
        }

    }
}