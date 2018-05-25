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
                break;
            }
            if (r.getSize() < ram.getSize() && r.getFree().equals("空闲")) {
                System.out.println("!内存不足！");
                flag = 1;
                break;
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
    public void release(int no, LinkedList<Ram> list) {
        int count = 0;
        for (Ram r : list) {
            if (no == r.getId())
                count++;
        }
        if (count == 0) {
            System.out.println("作业不存在");
        } else {
            //第一个
            if (no == list.getFirst().getId() && list.size() == 1) {
                list.getFirst().setFree(0);
            } else if (no == list.getFirst().getId()) {
                if (list.get(1).getFree().equals("使用")) {
                    list.getFirst().setFree(0);
                } else {
                    list.get(1).setSize(list.get(1).getSize() + list.getFirst().getSize());
                    list.get(1).setStart(list.getFirst().getStart());
                    list.remove(0);
                }
            }
            //最后一个
            if (no == list.getLast().getId() && list.size() == 2) {
                if (list.getFirst().getFree().equals("使用")) {
                    list.getLast().setFree(0);
                } else {
                    list.getFirst().setSize(list.getFirst().getSize() + list.getLast().getSize());
                    list.getFirst().setStart(0);
                    list.getFirst().setFree(0);
                    list.remove(1);
                }

            } else if (no == list.getLast().getId()) {
                if (list.get(list.size() - 2).getFree().equals("使用")) {
                    list.getLast().setFree(0);
                } else {
                    list.getLast().setSize(list.getLast().getSize() + list.get(list.size() - 2).getSize());
                    list.getLast().setStart(list.get(list.size() - 2).getStart());
                    list.getLast().setFree(0);
                    list.remove(list.size() - 2);
                }
            }
            //有三个及以上
            for (int i = 1; i < list.size() - 1; i++) {
                if (no == list.get(i).getId()) {
                    //上下非空
                    if (list.get(i - 1).getFree().equals("使用") && list.get(i + 1).getFree().equals("使用")) {
                        list.get(i).setFree(0);
                    }
                    //上下空
                    if (list.get(i - 1).getFree().equals("空闲") && list.get(i + 1).getFree().equals("空闲")) {
                        list.get(i - 1).setSize(list.get(i - 1).getSize() + list.get(i).getSize() + list.get(i + 1).getSize());
                        list.remove(i);
                        list.remove(i);
                    }
                    //上空下非
                    if (list.get(i - 1).getFree().equals("空闲") && list.get(i + 1).getFree().equals("使用")) {
                        list.get(i - 1).setSize(list.get(i - 1).getSize() + list.get(i).getSize());
                        list.remove(i);
                    }
                    //下空上非
                    if (list.get(i - 1).getFree().equals("使用") && list.get(i + 1).getFree().equals("空闲")) {
                        list.get(i).setSize(list.get(i).getSize() + list.get(i + 1).getSize());
                        list.get(i).setFree(0);
                        list.remove(i + 1);
                    }
                }
            }
        }
        show(list);
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
