import java.util.LinkedList;
import java.util.List;

public class Ctrl {
    //分配内存
    public void allocate(Ram ram, LinkedList<Ram> list) {
        int flag = 0;
        for (Ram r : list) {
            if (r.getId() == ram.getId()) {
                System.out.println("!编号重复!");
                flag = 1;
            }
            if (r.getSize() < ram.getSize() && r.getFree().equals("空闲")) {
                System.out.println("!内存不足！");
                flag = 1;
            } else {
                flag = 0;
            }
        }
        if (flag == 0) {
            //第一个
            if (list.get(0).getFree().equals("空闲") && list.get(0).getSize() == ram.getSize()) {
                list.get(0).setId(ram.getId());
                list.get(0).setFree(1);
            }
            if (list.get(0).getFree().equals("空闲") && list.get(0).getSize() > ram.getSize()) {
                list.addFirst(ram);
                list.get(1).setSize(list.get(1).getSize() - ram.getSize());
                list.get(1).setStart(ram.getSize() + 1);

            } else {
                //第二个及以后
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getFree().equals("空闲") && list.get(i).getSize() == ram.getSize()) {
                        list.get(i).setId(ram.getId());
                        list.get(i).setFree(1);
                        flag = 1;
                    }
                    if (list.get(i).getFree().equals("空闲") && list.get(i).getSize() > ram.getSize()) {
                        list.add(i, ram);
                        list.get(i).setStart(list.get(i + 1).getStart());
                        list.get(i).setFree(1);
                        list.get(i + 1).setSize(list.get(i + 1).getSize() - ram.getSize());
                        list.get(i + 1).setStart(list.get(i + 1).getStart() + ram.getSize() + 1);
                        flag = 1;
                    }
                    if (flag == 1)
                        break;
                }
            }
        }
        show(list);
    }

    //释放内存
    public void release(Ram ram) {
        System.out.println("release ok");
    }

    //显示内存状态
    public void show(List<Ram> list) {
        System.out.println("\t**********内存状态**********");
        System.out.println("|  编号  |  大小  |  起址  |  状态  |");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("|\t" + list.get(i).getId() + "\t" +
                    "|\t" + list.get(i).getSize() + "\t" +
                    "|\t" + list.get(i).getStart() + "\t" +
                    "|\t" + list.get(i).getFree() + "\t" + "|");
        }
        System.out.println("选择操作：1、添加作业 2、释放内存 3、退出");
    }
}
