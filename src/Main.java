import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*List<Ram> list = new LinkedList<>();
        Ram ram = new Ram();
        Ram ram1 = new Ram();
        Ram ram2 = new Ram();
        ram.setId(0);
        ram.setSize(9);
        list.add(ram);
        ram1.setId(1);
        ram1.setSize(8);
        list.add(ram1);
        ram2.setId(3);
        ram2.setSize(7);
        list.add(ram2);
        for (Ram r : list) {
            System.out.println(r.getId());
        }
        if (list.contains()) {
            System.out.println(123);
        } else {
            System.out.println(456);
        }*/

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
                    ctrl.release(ram);
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