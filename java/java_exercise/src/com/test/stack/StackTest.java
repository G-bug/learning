package com.test.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 用栈结构 优化 递归操作, 完成 树形 的构建
 *
 * @author g-bug
 * @date 2020/7/20 上午9:06
 */
public class StackTest {

    public static void main(String[] args) {

        List<Node> nodeList = new ArrayList<Node>() {{
            add(new Node(1, 0, new ArrayList<>()));
            add(new Node(2, 3, new ArrayList<>()));
            add(new Node(3, 5, new ArrayList<>()));
            add(new Node(4, 3, new ArrayList<>()));
            add(new Node(5, 1, new ArrayList<>()));
        }};

        List<Node> list1 = nodeList.stream().collect(
                Collectors.groupingBy(o -> o.parentId, Collectors.toList())
        ).get(0);

        Stack<List<Node>> listStack = new Stack<>();
        listStack.push(list1);
        while (!listStack.isEmpty()) {
            List<Node> popList = listStack.pop();
            for (Node node : popList) {
                List<Node> children = nodeList.stream().collect(
                        // 按父类分组，返回对象列表
                        Collectors.groupingBy(o -> o.parentId, Collectors.toList())
                        // 获取分组中父类id为node.id的
                ).get(node.id);
                if (Objects.isNull(children)) {
                    continue;
                }
                node.children = children;
                // 下级放入 栈
                listStack.push(children);
            }
        }
    }

}

class Node {

    public int id;

    public int parentId;

    List<Node> children;

    public Node(int id, int parentId, List<Node> children) {
        this.id = id;
        this.parentId = parentId;
        this.children = children;
    }
}
